package com.therandomlabs.changeloggenerator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModListComparer;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModSpecificChangelogHandler;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.utils.io.IOUtils;
import com.therandomlabs.utils.misc.StringUtils;

public final class SpazleyHandler extends ModSpecificChangelogHandler {
	public static final SpazleyHandler INSTANCE = new SpazleyHandler();

	private SpazleyHandler() {}

	@Override
	public boolean handlesMod(CurseProject project) {
		return "Spazley".equals(project.ownerUsername());
	}

	@Override
	public boolean isFullChangelogInNewFile(CurseProject project) {
		return true;
	}

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

		final String oldFileName = oldFile.name();
		final String oldVersion =
				oldFileName.substring(StringUtils.split(oldFileName, '-')[0].length() + 1);

		final String[] lines = StringUtils.splitNewline(newFile.changelog(true));
		final StringBuilder entry = new StringBuilder();
		String version = null;

		for(String line : lines) {
			if(line.isEmpty()) {
				continue;
			}

			if(!line.startsWith(" *")) {
				if(version != null) {
					changelog.put(version, entry.toString());
					entry.setLength(0);
				}

				version = line;

				if(version.endsWith(":")) {
					version = StringUtils.removeLastChar(version);
				}

				if(version.equals(oldVersion)) {
					break;
				}

				continue;
			}

			entry.append(line).append(IOUtils.LINE_SEPARATOR);
		}

		return changelog;
	}
}
