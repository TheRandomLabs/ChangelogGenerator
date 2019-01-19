package com.therandomlabs.changeloggenerator;

import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModSpecificChangelogHandler;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.utils.collection.ArrayUtils;
import com.therandomlabs.utils.io.IOUtils;
import com.therandomlabs.utils.misc.StringUtils;

public final class McJtyHandler extends ModSpecificChangelogHandler {
	public static final McJtyHandler INSTANCE = new McJtyHandler();

	private McJtyHandler() {}

	@Override
	public boolean handlesMod(CurseProject project) {
		return "McJty".equals(project.ownerUsername());
	}

	@Override
	public String modifyChangelog(CurseFile oldFile, CurseFile newFile, String changelog)
			throws CurseException {
		//McJty's changelogs' first two lines are not needed
		final String[] lines = StringUtils.splitNewline(changelog);
		return ArrayUtils.join(ArrayUtils.subArray(lines, 2), IOUtils.LINE_SEPARATOR);
	}
}
