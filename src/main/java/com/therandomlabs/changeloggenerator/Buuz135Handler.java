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
import com.therandomlabs.utils.io.IOUtils;
import com.therandomlabs.utils.misc.StringUtils;

public final class Buuz135Handler extends ModSpecificChangelogHandler {
	public static final Buuz135Handler INSTANCE = new Buuz135Handler();

	private Buuz135Handler() {}

	@Override
	public boolean handlesMod(CurseProject project) {
		return "Buuz135".equals(project.ownerUsername());
	}

	@Override
	public boolean isFullChangelogInNewFile(CurseProject project) {
		return true;
	}

	@SuppressWarnings("Duplicates")
	@Override
	public Map<String, String> getChangelogs(Object cacheKey, CurseFile oldFile, CurseFile newFile,
			boolean urls) throws CurseException, IOException {
		final Map<String, String> changelog = new LinkedHashMap<>();

		if(urls) {
			changelog.put(
					ModListComparer.VIEW_CHANGELOG_AT,
					ModListComparer.getChangelogURLString(newFile, true)
			);
			return changelog;
		}

		String[] split = oldFile.name().split("-");
		final String oldVersion = ArrayUtils.fromLast(split, 1);

		split = newFile.name().split("-");
		final String newVersion = ArrayUtils.fromLast(split, 1);

		final String[] lines =
				ArrayUtils.subArray(StringUtils.splitNewline(newFile.changelog(true)), 1);

		final StringBuilder entry = new StringBuilder();

		String version = newVersion;

		for(String line : lines) {
			if(line.trim().isEmpty()) {
				continue;
			}

			if(!line.startsWith(" * ")) {
				final String currentVersion = StringUtils.split(line, ' ')[0];

				changelog.put(version, entry.toString());
				entry.setLength(0);

				if(oldVersion.equals(newVersion)) {
					break;
				}

				version = currentVersion;
				continue;
			}

			if(version.equals(oldVersion) && !oldVersion.equals(newVersion)) {
				break;
			}

			entry.append(line.substring(1)).append(IOUtils.LINE_SEPARATOR);
		}

		return changelog;
	}
}
