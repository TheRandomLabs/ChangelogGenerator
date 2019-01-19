package com.therandomlabs.changeloggenerator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModListComparer;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModSpecificChangelogHandler;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.curseapi.util.Documents;
import com.therandomlabs.utils.collection.ArrayUtils;
import com.therandomlabs.utils.collection.ImmutableList;
import com.therandomlabs.utils.io.IOUtils;
import com.therandomlabs.utils.misc.StringUtils;

public final class CoFHHandler extends ModSpecificChangelogHandler {
	public static final CoFHHandler INSTANCE = new CoFHHandler();

	private CoFHHandler() {}

	@Override
	public boolean handlesMod(CurseProject project) {
		return "TeamCoFH".equals(project.ownerUsername());
	}

	@Override
	public List<String> getURLsToPreload(CurseFile oldFile, CurseFile newFile)
			throws CurseException {
		final String url = getChangelogURL(newFile);
		return url != null ? new ImmutableList<>(url) : null;
	}

	@Override
	public Map<String, String> getChangelogs(Object cacheKey, CurseFile oldFile, CurseFile newFile,
			boolean urls) throws CurseException, IOException {
		final String changelogURL = getChangelogURL(newFile);

		if(changelogURL == null) {
			return null;
		}

		final Map<String, String> changelog = new LinkedHashMap<>();

		if(urls) {
			changelog.put(ModListComparer.VIEW_CHANGELOG_AT, changelogURL);
			return changelog;
		}

		String oldVersion = oldFile.name().split("-")[2];
		int lengthToRemove = ArrayUtils.last(oldVersion.split("\\.")).length() + 1;
		oldVersion = oldVersion.substring(0, oldVersion.length() - lengthToRemove);

		String newVersion = newFile.name().split("-")[2];
		lengthToRemove = ArrayUtils.last(newVersion.split("\\.")).length() + 1;
		newVersion = newVersion.substring(0, newVersion.length() - lengthToRemove);

		final String[] lines = ArrayUtils.subArray(
				StringUtils.splitNewline(Documents.readWithCache(changelogURL, cacheKey)), 4
		);

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
					entryString = StringUtils.removeLastChars(entryString,
							IOUtils.LINE_SEPARATOR.length());

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
				entry.append(line).append(IOUtils.LINE_SEPARATOR);
			}
		}

		return changelog;
	}

	public static String getChangelogURL(CurseFile file) throws CurseException {
		String url = file.changelog(true).trim();
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
}
