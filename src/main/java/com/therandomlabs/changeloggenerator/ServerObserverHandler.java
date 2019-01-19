package com.therandomlabs.changeloggenerator;

import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileList;
import com.therandomlabs.curseapi.minecraft.modpack.comparison.ModSpecificChangelogHandler;
import com.therandomlabs.curseapi.project.CurseProject;

public final class ServerObserverHandler extends ModSpecificChangelogHandler {
	public static final ServerObserverHandler INSTANCE = new ServerObserverHandler();

	private ServerObserverHandler() {}

	@Override
	public boolean handlesMod(CurseProject project) {
		return project.id() == 279375;
	}

	@Override
	public void filterFileList(CurseFileList files, CurseFile oldFile, CurseFile newFile)
			throws CurseException {
		final String UNIVERSAL = " Universal";

		if(newFile.name().endsWith(UNIVERSAL)) {
			files.removeIf(file -> !file.name().endsWith(UNIVERSAL));
		} else {
			files.removeIf(file -> file.name().endsWith(UNIVERSAL));
		}
	}
}
