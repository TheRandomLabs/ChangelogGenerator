package com.therandomlabs.changeloggenerator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModListComparer;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModSpecificChangelogHandler;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.curseapi.util.Documents;
import com.therandomlabs.utils.collection.ImmutableList;
import com.therandomlabs.utils.io.IOUtils;
import com.therandomlabs.utils.misc.StringUtils;

public final class ActuallyAdditionsHandler implements ModSpecificChangelogHandler {
	public static final ActuallyAdditionsHandler INSTANCE = new ActuallyAdditionsHandler();
	public static final String CHANGELOG_URL = "https://raw.githubusercontent.com/Ellpeck/" +
			"ActuallyAdditions/master/update/changelog.md";

	private ActuallyAdditionsHandler() {}

	@Override
	public boolean handlesMod(CurseProject project) {
		return project.id() == 228404;
	}

	@Override
	public List<String> getURLsToPreload(CurseFile oldFile, CurseFile newFile)
			throws CurseException {
		return new ImmutableList<>(CHANGELOG_URL);
	}

	@Override
	public Map<String, String> getChangelogs(CurseFile oldFile, CurseFile newFile, boolean urls)
			throws CurseException, IOException {
		final Map<String, String> changelog = new LinkedHashMap<>();

		if(urls) {
			changelog.put(ModListComparer.VIEW_CHANGELOG_AT, CHANGELOG_URL);
			return changelog;
		}

		String[] split = oldFile.name().split("-");
		String oldVersion = split[1] + '-' + split[2];
		oldVersion = StringUtils.removeLastChars(oldVersion, 4);

		split = newFile.name().split("-");
		String newVersion = split[1] + '-' + split[2];
		newVersion = StringUtils.removeLastChars(newVersion, 4);

		final String[] lines =
				StringUtils.splitNewline(Documents.read(CHANGELOG_URL));
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
				entry.append(line).append(IOUtils.LINE_SEPARATOR);
			}
		}

		return changelog;
	}
}
