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
