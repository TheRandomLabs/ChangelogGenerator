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

package com.therandomlabs.changeloggenerator;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.common.base.MoreObjects;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;

/**
 * Represents a set of changelog entries for a {@link CurseFileChange}.
 */
public class ChangelogEntries {
	private final CurseFileChange<BasicCurseFile> fileChange;
	@SuppressWarnings("PMD.LooseCoupling")
	private final TreeSet<ChangelogEntry> entries;

	/**
	 * Constructs a {@link ChangelogEntries} instance with the specified {@link CurseFileChange}
	 * and {@link Collection} of {@link ChangelogEntry} instances.
	 *
	 * @param fileChange a {@link CurseFileChange}.
	 * @param entries a {@link SortedSet} of {@link ChangelogEntry} instances.
	 */
	@SuppressWarnings("unchecked")
	public ChangelogEntries(
			CurseFileChange<? extends BasicCurseFile> fileChange,
			Collection<? extends ChangelogEntry> entries
	) {
		this.fileChange = (CurseFileChange<BasicCurseFile>) fileChange;
		this.entries = new TreeSet<>(entries);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).
				add("fileChange", fileChange).
				add("entries", entries).
				toString();
	}

	/**
	 * Returns this {@link ChangelogEntries}' {@link CurseFileChange}.
	 *
	 * @return this {@link ChangelogEntries}' {@link CurseFileChange}.
	 */
	public CurseFileChange<BasicCurseFile> fileChange() {
		return fileChange;
	}

	/**
	 * Returns this {@link ChangelogEntries}' {@link ChangelogEntry} instances.
	 *
	 * @return a mutable {@link SortedSet} of this {@link ChangelogEntries}' {@link ChangelogEntry}
	 * instances.
	 */
	@SuppressWarnings("unchecked")
	public SortedSet<ChangelogEntry> entries() {
		return (SortedSet<ChangelogEntry>) entries.clone();
	}
}
