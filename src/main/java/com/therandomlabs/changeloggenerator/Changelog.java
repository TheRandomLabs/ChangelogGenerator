package com.therandomlabs.changeloggenerator;

import java.util.SortedSet;
import java.util.TreeSet;

import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;

public class Changelog {
	private final CurseFileChange<CurseFile> fileChange;
	private final SortedSet<ChangelogEntry> entries;

	public Changelog(CurseFileChange<CurseFile> fileChange, SortedSet<ChangelogEntry> entries) {
		this.fileChange = fileChange;
		this.entries = entries;
	}

	public CurseFileChange<CurseFile> fileChange() {
		return fileChange;
	}

	public SortedSet<ChangelogEntry> entries() {
		return new TreeSet<>(entries);
	}
}
