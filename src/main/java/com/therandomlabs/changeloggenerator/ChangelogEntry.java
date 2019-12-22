package com.therandomlabs.changeloggenerator;

import org.jsoup.nodes.Element;

public final class ChangelogEntry implements Comparable<ChangelogEntry> {
	private final Comparable<?> comparable;
	private final String displayName;
	private final Element entry;

	public ChangelogEntry(Comparable<?> comparable, String displayName, Element entry) {
		this.comparable = comparable;
		this.displayName = displayName;
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

	public String displayName() {
		return displayName;
	}

	public Element entry() {
		return entry;
	}
}
