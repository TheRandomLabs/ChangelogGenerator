package com.therandomlabs.changeloggenerator;

import com.therandomlabs.curseapi.file.CurseFile;
import org.jsoup.nodes.Element;

public final class ChangelogEntry implements Comparable<ChangelogEntry> {
	private final CurseFile file;
	private final String displayName;
	private final Element entry;

	public ChangelogEntry(CurseFile file, String displayName, Element entry) {
		this.file = file;
		this.displayName = displayName;
		this.entry = entry;
	}

	@Override
	public int hashCode() {
		return file.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		return this == object ||
				(object instanceof ChangelogEntry && file.equals(((ChangelogEntry) object).file));
	}

	@Override
	public int compareTo(ChangelogEntry changelogEntry) {
		return file.compareTo(changelogEntry.file);
	}

	public CurseFile file() {
		return file;
	}

	public String displayName() {
		return displayName;
	}

	public Element entry() {
		return entry;
	}
}
