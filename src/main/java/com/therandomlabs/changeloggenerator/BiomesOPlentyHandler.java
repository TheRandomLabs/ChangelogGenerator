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

public final class BiomesOPlentyHandler extends ModSpecificChangelogHandler {
	public static final BiomesOPlentyHandler INSTANCE = new BiomesOPlentyHandler();

	private BiomesOPlentyHandler() {}

	@Override
	public boolean handlesMod(CurseProject project) {
		return project.id() == 220318;
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

		final String[] split = oldFile.name().split("-");
		final String oldVersion = split[1] + '-' + split[2];

		final String[] lines = StringUtils.splitNewline(newFile.changelog(true));

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
				entry.append(line.substring(1)).append(IOUtils.LINE_SEPARATOR);
			}
		}

		return changelog;
	}
}
