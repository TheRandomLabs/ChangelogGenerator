package com.therandomlabs.changeloggenerator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModListComparer;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModSpecificChangelogHandler;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.utils.collection.ArrayUtils;
import com.therandomlabs.utils.collection.ImmutableList;
import com.therandomlabs.utils.io.IOUtils;
import com.therandomlabs.utils.misc.StringUtils;

public final class MezzHandler extends ModSpecificChangelogHandler {
	public static final MezzHandler INSTANCE = new MezzHandler();
	public static final ImmutableList<Integer> IDS = new ImmutableList<>(
			59751,
			223525,
			238222
	);

	private MezzHandler() {}

	@Override
	public boolean handlesMod(CurseProject project) {
		return IDS.contains(project.id());
	}

	@Override
	public boolean isFullChangelogInNewFile(CurseProject project) {
		return true;
	}

	@Override
	public Map<String, String> getChangelogs(CurseFile oldFile, CurseFile newFile, boolean urls)
			throws CurseException, IOException {
		final Map<String, String> changelog = new LinkedHashMap<>();

		if(urls) {
			changelog.put(
					ModListComparer.VIEW_CHANGELOG_AT,
					ModListComparer.getChangelogURLString(newFile, true)
			);
			return changelog;
		}

		final String oldVersion = getModVersion(oldFile.name());
		final String newVersion = getModVersion(newFile.name());

		final String[] lines = StringUtils.splitNewline(newFile.changelog(true));

		final StringBuilder entry = new StringBuilder();

		String version = null;

		for(String line : lines) {
			if(line.startsWith("Current release ")) {
				version = newVersion;
				continue;
			}

			if(line.startsWith("v")) {
				if(version == null) {
					version = newVersion;
					continue;
				}

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

			entry.append(line.substring(1)).append(IOUtils.LINE_SEPARATOR);
		}

		return changelog;
	}

	public static String getModVersion(String version) {
		final String oldName = StringUtils.removeLastChars(version, 4);
		String[] split = StringUtils.split(oldName, '_');

		String oldVersion;

		if(split.length == 1) {
			split = StringUtils.split(oldName, '-');
			oldVersion = split[split.length - 1];
		} else {
			oldVersion = StringUtils.split(split[1], '-')[1];
		}

		split = StringUtils.split(oldVersion, '.');

		if(split.length == 5) {
			oldVersion = StringUtils.removeLastChars(oldVersion, split[3].length() + 1);
		}

		return StringUtils.removeLastChars(oldVersion, ArrayUtils.last(split).length() + 1);
	}
}
