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

import com.google.common.collect.ImmutableSortedSet;
import com.therandomlabs.changeloggenerator.ChangelogEntry;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import org.checkerframework.checker.nullness.qual.Nullable;
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
	@Nullable
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
