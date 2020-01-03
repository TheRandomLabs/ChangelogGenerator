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

import org.jsoup.nodes.Element;

/**
 * Represents a changelog entry.
 */
public final class ChangelogEntry implements Comparable<ChangelogEntry> {
	private final Comparable<?> comparable;
	private final String title;
	private Element entry;

	/**
	 * Constructs a {@link ChangelogEntry} instance with the specified title and entry.
	 *
	 * @param comparable a {@link Comparable} for use in sorting. Changelog entries for newer
	 * files should be sorted first.
	 * @param title a title.
	 * @param entry a changelog entry.
	 */
	public ChangelogEntry(Comparable<?> comparable, String title, Element entry) {
		this.comparable = comparable;
		this.title = title;
		this.entry = entry;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return comparable.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		return this == object || (object instanceof ChangelogEntry &&
				comparable.equals(((ChangelogEntry) object).comparable));
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public int compareTo(ChangelogEntry changelogEntry) {
		return ((Comparable) comparable).compareTo(changelogEntry.comparable);
	}

	/**
	 * Returns this {@link ChangelogEntry}'s title.
	 *
	 * @return this {@link ChangelogEntry}'s title.
	 */
	public String title() {
		return title;
	}

	/**
	 * Returns this {@link ChangelogEntry}'s changelog entry.
	 *
	 * @return this {@link ChangelogEntry}'s changelog entry.
	 */
	public Element entry() {
		return entry;
	}

	//This is called by ChangelogGenerator#getChangelog(CurseFileChange).
	void setEntry(Element entry) {
		this.entry = entry;
	}
}
