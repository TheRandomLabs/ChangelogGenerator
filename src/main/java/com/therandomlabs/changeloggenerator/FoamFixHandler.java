package com.therandomlabs.changeloggenerator;

import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileList;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModSpecificChangelogHandler;
import com.therandomlabs.curseapi.project.CurseProject;

public final class FoamFixHandler implements ModSpecificChangelogHandler {
	public static final FoamFixHandler INSTANCE = new FoamFixHandler();

	private FoamFixHandler() {}

	@Override
	public boolean handlesMod(CurseProject project) {
		return project.id() == 278494;
	}

	@Override
	public void filterFileList(CurseFileList files, CurseFile oldFile, CurseFile newFile)
			throws CurseException {
		final String LAWFUL = "Lawful";

		if(oldFile.name().contains(LAWFUL)) {
			files.removeIf(file -> !file.name().contains(LAWFUL));
		} else {
			files.removeIf(file -> file.name().contains(LAWFUL));
		}
	}
}
