package com.therandomlabs.changeloggenerator.provider;

import java.util.SortedSet;

import com.therandomlabs.changeloggenerator.ChangelogEntry;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;

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
}
