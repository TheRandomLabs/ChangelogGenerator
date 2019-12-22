package com.therandomlabs.changeloggenerator.provider;

import java.util.SortedSet;

import com.therandomlabs.changeloggenerator.ChangelogEntry;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;

public interface ChangelogProvider {
	SortedSet<ChangelogEntry> getChangelog(CurseFileChange<? extends BasicCurseFile> fileChange)
			throws CurseException;
}
