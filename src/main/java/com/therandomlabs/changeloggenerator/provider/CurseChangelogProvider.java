package com.therandomlabs.changeloggenerator.provider;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.therandomlabs.changeloggenerator.ChangelogEntry;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.file.CurseFileFilter;
import com.therandomlabs.curseapi.file.CurseFiles;

public final class CurseChangelogProvider implements ChangelogProvider {
	public static final CurseChangelogProvider instance = new CurseChangelogProvider();

	private CurseChangelogProvider() {}

	@Override
	public SortedSet<ChangelogEntry> getChangelog(
			CurseFileChange<? extends BasicCurseFile> fileChange
	) throws CurseException {
		final CurseFiles<CurseFile> files = fileChange.filesBetween();
		new CurseFileFilter().
				gameVersionGroups(fileChange.oldCurseFile().gameVersionGroups()).
				apply(files);
		return files.parallelMap(
				file -> new ChangelogEntry(file, file.displayName(), file.changelog()),
				Collectors.toCollection(TreeSet::new)
		);
	}
}
