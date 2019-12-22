package com.therandomlabs.changeloggenerator;

import org.jsoup.nodes.Element;

public final class ChangelogEntry implements Comparable<ChangelogEntry> {
	private final Comparable<?> comparable;
	private final String title;
	private final Element entry;

	public ChangelogEntry(Comparable<?> comparable, String title, Element entry) {
		this.comparable = comparable;
		this.title = title;
		this.entry = entry;
	}

	@Override
	public int hashCode() {
		return comparable.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		return this == object || (object instanceof ChangelogEntry &&
				comparable.equals(((ChangelogEntry) object).comparable));
	}

	@SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public int compareTo(ChangelogEntry changelogEntry) {
		return ((Comparable) comparable).compareTo(changelogEntry.comparable);
	}

	public Comparable<?> comparable() {
		return comparable;
	}

	public String title() {
		return title;
	}

	public Element entry() {
		return entry;
	}
}
