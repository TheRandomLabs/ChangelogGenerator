/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019-2021 TheRandomLabs
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

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;
import com.therandomlabs.changeloggenerator.ChangelogEntry;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.file.CurseFiles;
import com.therandomlabs.curseapi.game.CurseGameVersionGroup;
import com.therandomlabs.curseapi.minecraft.MCVersion;
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

	private static final String VERSION_REGEX = "[a-z-_]+[\\d.]+-([\\d]+\\.[\\d]+\\.[\\d]+).*";

	private static final Splitter lineSeparatorSplitter = Splitter.on(System.lineSeparator()).
			omitEmptyStrings().
			trimResults();
	private static final Pattern versionHeading = Pattern.compile("^v[\\d.]+$");

	private MezzProvider() {}

	/**
	 * {@inheritDoc}
	 */
	@Nullable
	@Override
	public SortedSet<ChangelogEntry> getChangelog(
			CurseFileChange<? extends BasicCurseFile> fileChange,
			CurseGameVersionGroup<MCVersion> fallbackVersionGroup
	) throws CurseException {
		if (fileChange.projectID() != 59751 && fileChange.projectID() != 223525 &&
				fileChange.projectID() != 238222) {
			return null;
		}

		final String projectName = fileChange.get(file -> file.project().name());
		final CurseFiles<CurseFile> files =
				ChangelogProvider.getFilesBetweenInclusive(fileChange, fallbackVersionGroup);
		final CurseFile oldFile = files.last();
		final CurseFile newFile = files.first();

		List<String> oldChangelog = lineSeparatorSplitter.splitToList(oldFile.changelogPlainText());
		final List<String> cleanOldChangelog = oldChangelog.stream().
				map(line -> line.replaceAll("^\\*\\s+", "")).
				collect(Collectors.toList());

		List<String> newChangelog = lineSeparatorSplitter.splitToList(newFile.changelogPlainText());
		//Remove all lines that are in the old changelog.
		newChangelog = newChangelog.stream().
				map(line -> line.replaceAll("^\\*\\s+", "")).
				filter(line -> !cleanOldChangelog.contains(line)).
				collect(Collectors.toList());
		String cleanNewChangelog = String.join(System.lineSeparator(), newChangelog);

		final String oldVersion = oldFile.displayName().replaceAll(VERSION_REGEX, "$1");
		final String newVersion = newFile.displayName().replaceAll(VERSION_REGEX, "$1");

		final SortedSet<ChangelogEntry> changelog = new TreeSet<>();

		int index = 0;
		String version = "";
		Element entry = new Element("div");
		Element list = null;

		for (String line : lineSeparatorSplitter.split(cleanNewChangelog)) {
			if (line.startsWith("Current release ")) {
				version = newVersion;
				continue;
			}

			if (!versionHeading.matcher(line).matches()) {
				if (version.isEmpty()) {
					continue;
				}

				if (list == null) {
					list = new Element("ul");
					entry.appendChild(list);
				}

				list.appendChild(new Element("li").text(line));
				continue;
			}

			final String nextVersion = line.substring(1);

			if (version.isEmpty()) {
				version = nextVersion;
				continue;
			}

			if (version.equals(nextVersion)) {
				continue;
			}

			changelog.add(new ChangelogEntry(
					index++, projectName + ' ' + version, newFile.url(), entry
			));

			if (nextVersion.equals(oldVersion) || newVersion.equals(oldVersion)) {
				break;
			}

			version = nextVersion;
			entry = new Element("div");
			list = null;
		}

		if (list != null) {
			changelog.add(new ChangelogEntry(
					index, projectName + ' ' + version, newFile.url(), entry
			));
		}

		return changelog;
	}
}
