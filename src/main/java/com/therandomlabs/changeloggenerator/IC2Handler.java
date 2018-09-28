package com.therandomlabs.changeloggenerator;

import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.minecraft.comparison.ModSpecificChangelogHandler;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.utils.io.IOUtils;
import com.therandomlabs.utils.misc.StringUtils;

public final class IC2Handler implements ModSpecificChangelogHandler {
	public static final IC2Handler INSTANCE = new IC2Handler();

	private IC2Handler() {}

	@Override
	public boolean handlesMod(CurseProject project) {
		return project.id() == 242638;
	}

	@Override
	public String getChangelog(CurseFile file) throws CurseException {
		final String[] lines = StringUtils.splitNewline(file.changelog(true));
		final StringBuilder changelog = new StringBuilder();
		boolean versionFound = false;

		for(String line : lines) {
			if(line.contains("jenkins-IC2_")) {
				if(versionFound) {
					break;
				}

				versionFound = true;
				continue;
			}

			changelog.append(line.substring(1)).append(IOUtils.LINE_SEPARATOR);
		}

		return changelog.toString();
	}
}
