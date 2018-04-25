package com.therandomlabs.changeloggenerator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileList;
import com.therandomlabs.curseapi.minecraft.cmanifest.ManifestComparer;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.curseapi.util.DocumentUtils;
import com.therandomlabs.utils.collection.ArrayUtils;
import com.therandomlabs.utils.collection.ImmutableList;
import com.therandomlabs.utils.io.IOConstants;
import com.therandomlabs.utils.misc.StringUtils;
import com.therandomlabs.utils.throwable.ThrowableHandling;

public final class CGModSpecificHandler implements ManifestComparer.ModSpecificHandler {
	public static final CGModSpecificHandler INSTANCE = new CGModSpecificHandler();

	private static final String NEWLINE = IOConstants.LINE_SEPARATOR;
	private static final String VIEW_CHANGELOG_AT = ManifestComparer.VIEW_CHANGELOG_AT;

	private static final int SERVEROBSERVER_ID = 279375;
	private static final int FOAMFIX_ID = 278494;
	private static final int BIOMES_O_PLENTY_ID = 220318;
	private static final int ACTUALLY_ADDITIONS_ID = 228404;

	private static final String ACTUALLY_ADDITIONS_CHANGELOG = "https://raw." +
			"githubusercontent.com/Ellpeck/ActuallyAdditions/master/update/changelog.md";

	private CGModSpecificHandler() {}

	@Override
	public boolean shouldPreloadOnlyNewFile(int projectID, CurseProject project) {
		final String owner = project == null ? null : project.owner().username();
		return projectID == BIOMES_O_PLENTY_ID || "bre2el".equals(owner) || "mezz".equals(owner);
	}

	@Override
	public List<String> getURLsToPreload(int projectID, CurseFile oldFile, CurseFile newFile)
			throws CurseException {
		final CurseProject project = oldFile.project();

		if(projectID == BIOMES_O_PLENTY_ID) {
			return new ImmutableList<>(ManifestComparer.getChangelogURLString(newFile));
		}

		if(projectID == ACTUALLY_ADDITIONS_ID) {
			return new ImmutableList<>(ACTUALLY_ADDITIONS_CHANGELOG);
		}

		final String owner = project == null ? null : project.owner().username();

		if("TeamCoFH".equals(owner)) {
			final String url = getCoFHURL(newFile);
			if(url != null) {
				return new ImmutableList<>(url);
			}
		}

		return null;
	}

	@Override
	public void filterFileList(int projectID, CurseFileList files, CurseFile oldFile,
			CurseFile newFile) {
		switch(projectID) {
			case SERVEROBSERVER_ID:
				final String UNIVERSAL = " Universal";

				if(newFile.name().endsWith(UNIVERSAL)) {
					files.removeIf(file -> !file.name().endsWith(UNIVERSAL));
				} else {
					files.removeIf(file -> file.name().endsWith(UNIVERSAL));
				}

				break;

			case FOAMFIX_ID:
				final String LAWFUL = "Lawful";

				if(oldFile.name().contains(LAWFUL)) {
					files.removeIf(file -> !file.name().contains(LAWFUL));
				} else {
					files.removeIf(file -> file.name().contains(LAWFUL));
				}

				break;
		}
	}

	@Override
	public Map<String, String> getChangelogs(int projectID, CurseFile oldFile, CurseFile newFile,
			boolean urls) throws CurseException, IOException {
		final CurseProject project = oldFile.project();

		if(projectID == BIOMES_O_PLENTY_ID) {
			return getBoPChangelog(oldFile, newFile, urls);
		}

		if(projectID == ACTUALLY_ADDITIONS_ID) {
			return getAAChangelog(oldFile, newFile, urls);
		}

		final String owner = project == null ? null : project.owner().username();

		try {
			if("TeamCoFH".equals(owner)) {
				final Map<String, String> changelog = getCoFHChangelog(oldFile, newFile, urls);
				if(changelog != null) {
					return changelog;
				}
			}

			if("bre2el".equals(owner)) {
				return getBre2elChangelog(oldFile, newFile, urls);
			}

			if("mezz".equals(newFile.uploader())) {
				return getMezzChangelog(oldFile, newFile, urls);
			}
		} catch(IndexOutOfBoundsException | NullPointerException | NumberFormatException ex) {
			ThrowableHandling.handleWithoutExit(ex);
		}

		return null;
	}

	@Override
	public String modifyChangelog(int projectID, CurseFile oldFile, CurseFile newFile,
			String changelog) throws CurseException {
		final CurseProject project = oldFile.project();

		if(project == null) {
			return changelog;
		}

		if("McJty".equals(project.owner().username())) {
			//McJty's changelogs' first two lines are not needed
			final String[] lines = StringUtils.splitNewline(changelog);
			return ArrayUtils.join(ArrayUtils.subArray(lines, 2), NEWLINE);
		}

		return changelog;
	}

	static Map<String, String> getCoFHChangelog(CurseFile oldFile, CurseFile newFile,
			boolean url) throws CurseException, IOException {
		final String changelogURL = getCoFHURL(newFile);

		if(changelogURL == null) {
			return null;
		}

		final Map<String, String> changelog = new LinkedHashMap<>();

		if(url) {
			changelog.put(VIEW_CHANGELOG_AT, changelogURL);
			return changelog;
		}

		String oldVersion = oldFile.name().split("-")[2];
		int lengthToRemove = ArrayUtils.last(oldVersion.split("\\.")).length() + 1;
		oldVersion = oldVersion.substring(0, oldVersion.length() - lengthToRemove);

		String newVersion = newFile.name().split("-")[2];
		lengthToRemove = ArrayUtils.last(newVersion.split("\\.")).length() + 1;
		newVersion = newVersion.substring(0, newVersion.length() - lengthToRemove);

		final String[] lines =
				ArrayUtils.subArray(StringUtils.splitNewline(DocumentUtils.read(changelogURL)), 4);
		final StringBuilder entry = new StringBuilder();
		String version = null;

		boolean checkVersion = true;
		boolean changelogStarted = false;

		for(String line : lines) {
			if(checkVersion) {
				checkVersion = false;

				if(line.isEmpty()) {
					continue;
				}

				if(changelogStarted) {
					String entryString = entry.toString();
					entryString = StringUtils.removeLastChars(entryString, NEWLINE.length());

					changelog.put(version, entryString);

					entry.setLength(0);
				}

				version = StringUtils.removeLastChar(line);

				if(changelogStarted) {
					if(version.equals(oldVersion)) {
						break;
					}
				} else if(version.equals(newVersion)) {
					changelogStarted = true;
				}

				continue;
			}

			if(line.startsWith("------")) {
				checkVersion = true;
				continue;
			}

			if(changelogStarted) {
				entry.append(line).append(NEWLINE);
			}
		}

		return changelog;
	}

	static String getCoFHURL(CurseFile file) throws CurseException {
		String url = file.changelog().trim();
		url = url.split("]")[0].substring(1);
		url = url.replace("/blob", "");
		url = url.replace("github", "raw.githubusercontent");

		try {
			new URL(url);
		} catch(MalformedURLException ex) {
			return null;
		}

		return url;
	}

	static Map<String, String> getBoPChangelog(CurseFile oldFile, CurseFile newFile,
			boolean url) throws CurseException {
		final Map<String, String> changelog = new LinkedHashMap<>();

		if(url) {
			changelog.put(VIEW_CHANGELOG_AT, ManifestComparer.getChangelogURLString(newFile));
			return changelog;
		}

		final String[] split = oldFile.name().split("-");
		final String oldVersion = split[1] + '-' + split[2];

		final String[] lines = StringUtils.splitNewline(newFile.changelog());

		final StringBuilder entry = new StringBuilder();

		String version = null;

		for(int i = 1; i < lines.length; i++) {
			final String line = lines[i];

			if(line.startsWith("Build ")) {
				version = StringUtils.removeLastChar(line.split(" ")[1]);
				continue;
			}

			if(version == null) {
				continue;
			}

			if(version.equals(oldVersion)) {
				break;
			}

			if(line.isEmpty()) {
				changelog.put(version, entry.toString());
				entry.setLength(0);
				version = null;
			}

			if(version != null) {
				entry.append(line.substring(1)).append(NEWLINE);
			}
		}

		return changelog;
	}

	static Map<String, String> getAAChangelog(CurseFile oldFile, CurseFile newFile,
			boolean url) throws CurseException, IOException {
		final Map<String, String> changelog = new LinkedHashMap<>();

		if(url) {
			changelog.put(VIEW_CHANGELOG_AT, ACTUALLY_ADDITIONS_CHANGELOG);
			return changelog;
		}

		String[] split = oldFile.name().split("-");
		String oldVersion = split[1] + '-' + split[2];
		oldVersion = StringUtils.removeLastChars(oldVersion, 4);

		split = newFile.name().split("-");
		String newVersion = split[1] + '-' + split[2];
		newVersion = StringUtils.removeLastChars(newVersion, 4);

		final String[] lines =
				StringUtils.splitNewline(DocumentUtils.read(ACTUALLY_ADDITIONS_CHANGELOG));
		final StringBuilder entry = new StringBuilder();
		String version = null;

		boolean changelogStarted = false;

		for(String line : lines) {
			if(line.startsWith("# ")) {
				if(changelogStarted) {
					changelog.put(version, entry.toString());
					entry.setLength(0);
				}

				version = line.substring(2);

				if(!changelogStarted && version.equals(newVersion)) {
					changelogStarted = true;
				}

				if(version.equals(oldVersion)) {
					break;
				}

				continue;
			}

			if(line.isEmpty()) {
				continue;
			}

			if(changelogStarted) {
				entry.append(line).append(NEWLINE);
			}
		}

		return changelog;
	}

	static Map<String, String> getBre2elChangelog(CurseFile oldFile, CurseFile newFile,
			boolean url) throws CurseException {
		final Map<String, String> changelog = new LinkedHashMap<>();

		if(url) {
			changelog.put(VIEW_CHANGELOG_AT, ManifestComparer.getChangelogURLString(newFile));
			return changelog;
		}

		final String oldVersion = oldFile.name().split("-")[2].replaceAll("\\.jar", "");

		final String[] lines = StringUtils.splitNewline(newFile.changelog());
		final StringBuilder entry = new StringBuilder();
		String version = null;

		for(String line : lines) {
			if(line.startsWith("v")) {
				if(version != null) {
					changelog.put(version, entry.toString());
					entry.setLength(0);
				}

				version = line.substring(1);

				if(version.equals(oldVersion)) {
					break;
				}

				continue;
			}

			if(line.isEmpty()) {
				continue;
			}

			entry.append(line).append(NEWLINE);
		}

		return changelog;
	}

	static String getMezzModVersion(String version) {
		final String oldName = StringUtils.removeLastChars(version, 4);
		String[] split = StringUtils.split(oldName, '_');

		final String oldVersion;

		if(split.length == 1) {
			split = StringUtils.split(oldName, '-');
			oldVersion = split[split.length - 1];
		} else {
			oldVersion = StringUtils.split(split[1], '-')[1];
		}

		split = StringUtils.split(oldVersion, '.');
		return StringUtils.removeLastChars(oldVersion, ArrayUtils.last(split).length() + 1);
	}

	//TODO do by replacement instead?
	//Jesus Christ this was the hardest one to write
	//Don't ask me what it does
	static Map<String, String> getMezzChangelog(CurseFile oldFile, CurseFile newFile, boolean url)
			throws CurseException {
		final Map<String, String> changelog = new LinkedHashMap<>();

		if(url) {
			changelog.put(VIEW_CHANGELOG_AT, ManifestComparer.getChangelogURLString(newFile));
			return changelog;
		}

		final String oldVersion = getMezzModVersion(oldFile.name());
		final String newVersion = getMezzModVersion(newFile.name());

		final String[] lines = StringUtils.splitNewline(newFile.changelog());

		final StringBuilder entry = new StringBuilder();

		String version = null;

		for(String line : lines) {
			if(line.startsWith("Current release ")) {
				version = newVersion;
				continue;
			}

			if(line.startsWith("v")) {
				final String currentVersion = line.substring(1);

				if(version.equals(currentVersion)) {
					continue;
				}

				changelog.put(version, entry.toString());
				entry.setLength(0);

				if(oldVersion.equals(newVersion)) {
					break;
				}

				version = currentVersion;
				continue;
			}

			if(version == null || line.isEmpty()) {
				continue;
			}

			if(version.equals(oldVersion) && !oldVersion.equals(newVersion)) {
				break;
			}

			entry.append(line.substring(1)).append(NEWLINE);
		}

		return changelog;
	}
}
