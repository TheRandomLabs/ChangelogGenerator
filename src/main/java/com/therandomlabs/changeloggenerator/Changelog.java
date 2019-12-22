package com.therandomlabs.changeloggenerator;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;

/**
 * Represents a set of changelog entries for a {@link CurseFileChange}.
 */
public class Changelog {
	private final CurseFileChange<CurseFile> fileChange;
	@SuppressWarnings("PMD.LooseCoupling")
	private final TreeSet<ChangelogEntry> entries;

	/**
	 * Constructs a {@link Changelog} instance with the specified {@link CurseFileChange}
	 * and {@link Collection} of {@link ChangelogEntry} instances.
	 *
	 * @param fileChange a {@link CurseFileChange}.
	 * @param entries a {@link SortedSet} of {@link ChangelogEntry} instances.
	 */
	@SuppressWarnings("unchecked")
	public Changelog(
			CurseFileChange<? extends CurseFile> fileChange,
			Collection<? extends ChangelogEntry> entries
	) {
		this.fileChange = (CurseFileChange<CurseFile>) fileChange;
		this.entries = new TreeSet<>(entries);
	}

	/**
	 * Returns this {@link Changelog}'s {@link CurseFileChange}.
	 *
	 * @return this {@link Changelog}'s {@link CurseFileChange}.
	 */
	public CurseFileChange<CurseFile> fileChange() {
		return fileChange;
	}

	/**
	 * Returns this {@link Changelog}'s {@link ChangelogEntry} instances.
	 *
	 * @return a mutable {@link SortedSet} of this {@link Changelog}'s {@link ChangelogEntry}
	 * instances.
	 */
	@SuppressWarnings("unchecked")
	public SortedSet<ChangelogEntry> entries() {
		return (SortedSet<ChangelogEntry>) entries.clone();
	}
}
