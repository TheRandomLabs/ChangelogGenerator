package com.therandomlabs.changeloggenerator.provider;

import java.util.SortedSet;

import com.therandomlabs.changeloggenerator.ChangelogEntry;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.file.CurseFileFilter;
import com.therandomlabs.curseapi.file.CurseFiles;

/**
 * Provides changelogs for {@link CurseFileChange}s.
 */
public interface ChangelogProvider {
	/**
	 * Returns the changelog for the specified {@link CurseFileChange}.
	 *
	 * @param fileChange a {@link CurseFileChange}.
	 * @return a {@link SortedSet} of {@link ChangelogEntry} instances that represent
	 * the changelog for the specified {@link CurseFileChange}. If the {@link CurseFileChange}
	 * is not supported by this implementation of {@link ChangelogProvider},
	 * {@code null} may be returned.
	 * @throws CurseException if an error occurs.
	 */
	SortedSet<ChangelogEntry> getChangelog(CurseFileChange<? extends BasicCurseFile> fileChange)
			throws CurseException;

	/**
	 * Calls {@link CurseFileChange#filesBetweenInclusive()} on the specified
	 * {@link CurseFileChange}, then filters the returned {@link CurseFiles} to only
	 * include files from the relevant game version groups.
	 *
	 * @param fileChange a {@link CurseFileChange}.
	 * @return {@link CurseFileChange#filesBetweenInclusive()} filtered to only include
	 * files from relevant game version groups.
	 * @throws CurseException if an error occurs.
	 */
	static CurseFiles<CurseFile> getFilesBetweenInclusive(
			CurseFileChange<? extends BasicCurseFile> fileChange
	) throws CurseException {
		final CurseFiles<CurseFile> files = fileChange.filesBetweenInclusive();
		new CurseFileFilter().
				gameVersionGroups(fileChange.get(CurseFile::gameVersionGroups)).
				apply(files);
		return files;
	}
}
