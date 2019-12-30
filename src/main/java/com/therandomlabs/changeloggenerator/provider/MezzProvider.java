package com.therandomlabs.changeloggenerator.provider;

import java.util.SortedSet;

import com.google.common.collect.ImmutableSortedSet;
import com.therandomlabs.changeloggenerator.ChangelogEntry;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import org.jsoup.nodes.Element;

/**
 * An implementation of {@link ChangelogProvider} for mezz's mods.
 */
public final class MezzProvider implements ChangelogProvider {
	/**
	 * The singleton instance of {@link MezzProvider}.
	 */
	public static final MezzProvider instance = new MezzProvider();

	private MezzProvider() {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SortedSet<ChangelogEntry> getChangelog(
			CurseFileChange<? extends BasicCurseFile> fileChange
	) {
		if (fileChange.projectID() != 59751 && fileChange.projectID() != 223525 &&
				fileChange.projectID() != 238222) {
			return null;
		}

		/*
		TODO
		final CurseFiles<CurseFile> files = ChangelogProvider.getFilesBetweenInclusive(fileChange);
		final String fullChangelog = files.first().changelogPlainText();

		final SortedSet<ChangelogEntry> changelog = new TreeSet<>();


		for (String line : fullChangelog.split(System.lineSeparator())) {
			if(line.startsWith("Current release ")) {
				version = newVersion;
				continue;
			}

			if(line.startsWith("v")) {
				if(version == null) {
					version = newVersion;
					continue;
				}

				final String currentVersion = line.substring(1);

				if(version.equals(currentVersion)) {
					continue;
				}

				changelog.put(version, entry.toString());
				entry.setLength(0);

				if(oldVersion.equals(newVersion)) {
					break;
				}

				version = currentVersion;
				continue;
			}

			if(version == null || line.isEmpty()) {
				continue;
			}

			if(version.equals(oldVersion) && !oldVersion.equals(newVersion)) {
				break;
			}

			entry.append(line.substring(1)).append(System.lineSeparator());
		}


		return changelog;
		*/

		return ImmutableSortedSet.of(new ChangelogEntry(0, "Placeholder", new Element("p")));
	}
}
