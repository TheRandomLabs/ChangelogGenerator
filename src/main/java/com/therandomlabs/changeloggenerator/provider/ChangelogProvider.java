/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019-2020 TheRandomLabs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.therandomlabs.changeloggenerator.provider;

import java.util.SortedSet;

import com.therandomlabs.changeloggenerator.ChangelogEntry;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.file.CurseFileFilter;
import com.therandomlabs.curseapi.file.CurseFiles;
import com.therandomlabs.curseapi.game.CurseGameVersionGroup;
import com.therandomlabs.curseapi.minecraft.MCVersion;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jsoup.nodes.Element;

/**
 * Provides changelogs for {@link CurseFileChange}s.
 */
public interface ChangelogProvider {
	/**
	 * Returns the changelog for the specified {@link CurseFileChange}.
	 *
	 * @param fileChange a {@link CurseFileChange}.
	 * @param fallbackVersionGroup the {@link CurseGameVersionGroup} to use if a game version group
	 * is necessary and cannot be determined.
	 * @return a {@link SortedSet} of {@link ChangelogEntry} instances that represent
	 * the changelog for the specified {@link CurseFileChange}. If the {@link CurseFileChange}
	 * is not supported by this implementation of {@link ChangelogProvider},
	 * {@code null} may be returned.
	 * @throws CurseException if an error occurs.
	 */
	@Nullable
	default SortedSet<ChangelogEntry> getChangelog(
			CurseFileChange<? extends BasicCurseFile> fileChange,
			CurseGameVersionGroup<MCVersion> fallbackVersionGroup
	) throws CurseException {
		return null;
	}

	/**
	 * Performs modifications on a retrieved changelog.
	 *
	 * @param fileChange a {@link CurseFileChange}.
	 * @param changelog an {@link Element} that contains a changelog.
	 * @return the modified changelog.
	 * @throws CurseException if an error occurs.
	 */
	default Element processChangelog(
			CurseFileChange<? extends BasicCurseFile> fileChange, Element changelog
	) throws CurseException {
		return changelog;
	}

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
	@SuppressWarnings("NullAway")
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
