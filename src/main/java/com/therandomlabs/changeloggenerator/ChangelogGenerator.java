package com.therandomlabs.changeloggenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.google.gson.JsonSyntaxException;
import com.therandomlabs.curseapi.CurseAPI;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.CurseForgeSite;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileList;
import com.therandomlabs.curseapi.minecraft.Mod;
import com.therandomlabs.curseapi.minecraft.mpmanifest.CompareResults;
import com.therandomlabs.curseapi.minecraft.mpmanifest.ExtendedMPManifest;
import com.therandomlabs.curseapi.minecraft.mpmanifest.ManifestComparer;
import com.therandomlabs.curseapi.minecraft.mpmanifest.VersionChange;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.utils.collection.TRLList;
import com.therandomlabs.utils.io.IOConstants;
import com.therandomlabs.utils.io.NIOUtils;
import com.therandomlabs.utils.io.NetUtils;
import com.therandomlabs.utils.misc.Assertions;
import com.therandomlabs.utils.misc.StringUtils;
import com.therandomlabs.utils.misc.ThreadUtils;
import static com.therandomlabs.utils.logging.Logging.getLogger;

public final class ChangelogGenerator {
	public static final String VERSION = "1.10.1";

	private static final String NEWLINE = IOConstants.LINE_SEPARATOR;

	static {
		ManifestComparer.registerModSpecificHandler(CGModSpecificHandler.INSTANCE);
	}

	private ChangelogGenerator() {}

	public static void main(String[] args) throws Exception {
		NetUtils.setUserAgent("Mozilla (https://github.com/TheRandomLabs/ChangelogGenerator)");
		run(args);
	}

	public static void run(String[] args) throws CurseException, IOException {
		getLogger().disableDebug();
		CurseAPI.setCurseMetaEnabled(false);

		if(args.length == 1) {
			CurseProject project;

			try {
				project = CurseProject.fromID(args[0]);
			} catch(NumberFormatException ex) {
				project = CurseProject.fromSlug(CurseForgeSite.MINECRAFT, args[0]);
			}

			NIOUtils.write(Paths.get("changeloghistory.txt"), getChangelogHistory(project));

			return;
		}

		final String oldFile = args.length > 1 ? args[0] : "old.json";
		final String newFile = args.length > 1 ? args[1] : "new.json";

		final Path oldPath = getPath(oldFile);
		final Path newPath = getPath(newFile);

		Assertions.fileExists(oldPath);
		Assertions.fileExists(newPath);

		final ExtendedMPManifest oldManifest = getManifest(oldPath);
		final ExtendedMPManifest manifest = getManifest(newPath);

		final CompareResults results = ManifestComparer.compare(oldManifest, manifest);

		NIOUtils.write(Paths.get("changelog.txt"), getChangelog(results, false));
		NIOUtils.write(Paths.get("shortchangelog.txt"), getChangelog(results, true));
	}

	public static void testJEI() throws CurseException, IOException {
		final CurseProject jei = CurseProject.fromID(238222);

		final ExtendedMPManifest oldManifest =
				ExtendedMPManifest.ofFiles("jei_old", new CurseFileList(jei.files().get(2)));
		final ExtendedMPManifest manifest =
				ExtendedMPManifest.ofFiles("jei_new", new CurseFileList(jei.latestFile()));

		final CompareResults results = ManifestComparer.compare(oldManifest, manifest);

		NIOUtils.write(Paths.get("jei_changelog.txt"), getChangelog(results, false));
		NIOUtils.write(Paths.get("jei_shortchangelog.txt"), getChangelog(results, true));
	}

	public static String getChangelogHistory(CurseProject project) throws CurseException {
		final CurseFileList files = project.files();
		final CurseFile latest = files.latest();
		final CurseFile oldest = files.get(files.size() - 1);

		final StringBuilder string = new StringBuilder();

		string.append(oldest.name()).append(" to ").append(latest.name()).append(NEWLINE);

		final Map<CurseFile, String> changelogs = new ConcurrentHashMap<>();

		ThreadUtils.splitWorkload(CurseAPI.getMaximumThreads(), files.size(), index -> {
			final CurseFile file = files.get(index);
			getLogger().info("Downloading changelog for file: " + file.name());
			changelogs.put(file, file.changelog(true));
		});

		final TRLList<Map.Entry<CurseFile, String>> entries = new TRLList<>(changelogs.entrySet());
		entries.sort(Comparator.comparing(Map.Entry::getKey));

		for(int i = entries.size() - 1; i >= 0; i--) {
			final Map.Entry<CurseFile, String> entry = entries.get(i);
			final CurseFile file = entry.getKey();
			final String changelog = entry.getValue();

			string.append(NEWLINE).append(file.name()).append(':');

			final String[] lines = StringUtils.NEWLINE.split(changelog);

			for(String line : lines) {
				string.append(NEWLINE);

				if(!line.trim().isEmpty()) {
					string.append("\t").append(StringUtils.trimTrailing(line));
				}
			}

			string.append(NEWLINE);
		}

		string.append(NEWLINE).
				append("* Generated using https://github.com/TheRandomLabs/ChangelogGenerator").
				append(" (").append(VERSION).append(")").append(NEWLINE);

		return string.toString();
	}

	public static String getChangelog(CompareResults results, boolean urls)
			throws CurseException, IOException {
		final StringBuilder string = new StringBuilder();

		string.append(results.getOldManifest().name).append(' ').
				append(results.getOldManifest().version).append(" to ").
				append(results.getNewManifest().name).append(' ').
				append(results.getNewManifest().version).append(NEWLINE).append(NEWLINE);

		if(!results.loadAndGetAdded().isEmpty()) {
			string.append("Added:");

			for(Mod added : results.getAdded()) {
				string.append(NEWLINE).append("\t").append("- ").append(added.title());
			}

			string.append(NEWLINE).append(NEWLINE);
		}

		if(!results.getUpdated().isEmpty()) {
			string.append("Updated:");

			final Map<VersionChange, Map<String, String>> changelogs =
					results.getUpdatedChangelogs(urls, true);

			appendChangelogs(string, changelogs);
		}

		if(!results.getDowngraded().isEmpty()) {
			string.append("Downgraded:");

			final Map<VersionChange, Map<String, String>> changelogs =
					results.getDowngradedChangelogs(urls, true);

			appendChangelogs(string, changelogs);
		}

		if(!results.loadAndGetRemoved().isEmpty()) {
			string.append("Removed:");

			for(Mod removed : results.getRemoved()) {
				String title = removed.title();

				if(title.equals(CurseProject.UNKNOWN_TITLE)) {
					title = "Deleted project";
				}

				string.append(NEWLINE).append("\t").append("- ").append(title);
			}

			string.append(NEWLINE).append(NEWLINE);
		}

		string.append("* Generated using https://github.com/TheRandomLabs/ChangelogGenerator").
				append(" (").append(VERSION).append(")").append(NEWLINE);

		return string.toString();
	}

	private static Path getPath(String stringPath) {
		Path path = null;

		try {
			path = Paths.get(stringPath);
			if(!Files.exists(path) || Files.isDirectory(path)) {
				getLogger().error("Invalid path: " + stringPath);
				System.exit(1);
			}
		} catch(InvalidPathException ex) {
			getLogger().error("Invalid path: " + stringPath);
			System.exit(1);
		}

		return path;
	}

	private static ExtendedMPManifest getManifest(Path path) throws IOException {
		ExtendedMPManifest manifest = null;

		try {
			manifest = ExtendedMPManifest.from(path);
		} catch(JsonSyntaxException ex) {
			getLogger().error("Invalid JSON: " + path);
			System.exit(1);
		}

		return manifest;
	}

	private static void appendChangelogs(StringBuilder string,
			Map<VersionChange, Map<String, String>> changelogs) throws CurseException {
		for(Map.Entry<VersionChange, Map<String, String>> changelog : changelogs.entrySet()) {
			final VersionChange vc = changelog.getKey();

			string.append(NEWLINE).append("\t").append(vc.getModTitle()).
					append(" (went from ").append(vc.getOldFileName()).append(" to ").
					append(vc.getNewFileName()).append("):");

			for(Map.Entry<String, String> modChangelog : changelog.getValue().entrySet()) {
				string.append(NEWLINE).append("\t\t").append(modChangelog.getKey()).
						append(':');

				final String[] lines = StringUtils.NEWLINE.split(modChangelog.getValue());
				for(String line : lines) {
					//Remove unneeded whitespace at the end of the line
					string.append(NEWLINE);

					if(!line.trim().isEmpty()) {
						string.append("\t\t\t").append(StringUtils.trimTrailing(line));
					}
				}
			}
			string.append(NEWLINE);
		}

		string.append(NEWLINE);
	}
}
