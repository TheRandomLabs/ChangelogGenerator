package com.therandomlabs.changeloggenerator;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.minecraft.Mod;
import com.therandomlabs.curseapi.minecraft.modpack.manifest.ExtendedCurseManifest;
import com.therandomlabs.curseapi.minecraft.modpack.manifest.ManifestComparer;
import com.therandomlabs.curseapi.minecraft.modpack.manifest.ManifestComparer.VersionChange;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.utils.io.IOConstants;
import com.therandomlabs.utils.io.NIOUtils;
import com.therandomlabs.utils.misc.StringUtils;

public final class ChangelogGenerator {
	public static final String VERSION = "1.5";

	private static final String NEWLINE = IOConstants.LINE_SEPARATOR;

	//TODO command line
	public static void main(String[] args) throws Exception {
		ManifestComparer.registerModSpecificHandler(CGModSpecificHandler.INSTANCE);

		final String oldFile = args.length > 0 ? args[0] : "old.json";
		final String newFile = args.length > 1 ? args[1] : "new.json";

		final ExtendedCurseManifest oldManifest = ExtendedCurseManifest.from(oldFile);
		final ExtendedCurseManifest manifest = ExtendedCurseManifest.from(newFile);

		final ManifestComparer.Results results = ManifestComparer.compare(oldManifest, manifest);

		NIOUtils.write(Paths.get("changelog.txt"), getChangelog(results, false));
		NIOUtils.write(Paths.get("shortchangelog.txt"), getChangelog(results, true));
	}

	public static String getChangelog(ManifestComparer.Results results,
			boolean urls) throws CurseException, IOException {
		final StringBuilder string = new StringBuilder();

		string.append(results.getOldManifest().name).append(' ').
				append(results.getOldManifest().version).append(" to ").
				append(results.getNewManifest().name).append(' ').
				append(results.getNewManifest().version).append(NEWLINE).append(NEWLINE);

		if(!results.getAdded().isEmpty()) {
			string.append("Added:");

			for(Mod added : results.getAdded()) {
				string.append(NEWLINE).append("\t").append("- ").append(added.title());
			}

			string.append(NEWLINE).append(NEWLINE);
		}

		if(!results.getUpdated().isEmpty()) {
			string.append("Updated:");

			final Map<VersionChange, Map<String, String>> changelogs =
					results.getUpdatedChangelogs(urls);

			appendChangelogs(string, changelogs);
		}

		if(!results.getDowngraded().isEmpty()) {
			string.append("Downgraded:");

			final Map<VersionChange, Map<String, String>> changelogs =
					results.getDowngradedChangelogs(urls);

			appendChangelogs(string, changelogs);
		}

		if(!results.getRemoved().isEmpty()) {
			string.append("Removed:");

			for(Mod removed : results.getRemoved()) {
				string.append(NEWLINE).append("\t").append("- ").append(removed.title());
			}

			string.append(NEWLINE).append(NEWLINE);
		}

		string.append("* Generated using https://github.com/TheRandomLabs/ChangelogGenerator").
				append(" (").append(VERSION).append(")").append(NEWLINE);

		final String toString = string.toString();
		if(toString.endsWith(NEWLINE + NEWLINE)) {
			return toString.substring(0, toString.length() - NEWLINE.length());
		}
		return toString;
	}

	private static void appendChangelogs(StringBuilder string,
			Map<VersionChange, Map<String, String>> changelogs) throws CurseException {
		for(Map.Entry<VersionChange, Map<String, String>> changelog : changelogs.entrySet()) {
			final VersionChange vc = changelog.getKey();

			//TODO date support
			string.append(NEWLINE).append("\t").append(vc.getModTitle()).
					append(" (went from ").append(vc.getOldFileName()).append(" to ").
					append(vc.getNewFileName()).append("):");

			for(Map.Entry<String, String> modChangelog : changelog.getValue().entrySet()) {
				string.append(NEWLINE).append("\t\t").append(modChangelog.getKey()).
						append(':');

				final String[] lines = StringUtils.NEWLINE.split(modChangelog.getValue());
				for(String line : lines) {
					string.append(NEWLINE);

					if(!line.trim().isEmpty()) {
						string.append("\t\t\t").append(line);
					}
				}
			}

			string.append(NEWLINE);
		}

		string.append(NEWLINE);
	}
}
