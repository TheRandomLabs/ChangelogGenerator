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
import java.util.TreeSet;

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
 * An implementation of {@link ChangelogProvider} for Biomes O' Plenty.
 */
public final class BiomesOPlentyProvider implements ChangelogProvider {
	/**
	 * The singleton instance of {@link BiomesOPlentyProvider}.
	 */
	public static final BiomesOPlentyProvider instance = new BiomesOPlentyProvider();

	private static final Splitter LINE_SEPARATOR_SPLITTER = Splitter.on(System.lineSeparator());
	private static final Splitter FULL_STOP_SPLITTER = Splitter.on('.');

	private BiomesOPlentyProvider() {}

	/**
	 * {@inheritDoc}
	 */
	@Nullable
	@Override
	public SortedSet<ChangelogEntry> getChangelog(
			CurseFileChange<? extends BasicCurseFile> fileChange,
			CurseGameVersionGroup<MCVersion> fallbackVersionGroup
	) throws CurseException {
		if (fileChange.projectID() != 220318) {
			return null;
		}

		final CurseFiles<CurseFile> files = ChangelogProvider.getFilesBetweenInclusive(fileChange);
		final CurseFile newFile = files.first();
		final String fullChangelog = newFile.changelogPlainText();

		if (!fullChangelog.startsWith("Build: ")) {
			return null;
		}

		final int oldVersion = getVersionInt(files.last().displayName());

		final SortedSet<ChangelogEntry> changelog = new TreeSet<>();

		int index = 0;
		String currentVersion = "";

		Element currentEntry = new Element("div");
		Element currentList = null;

		for (String line : LINE_SEPARATOR_SPLITTER.split(fullChangelog)) {
			if (line.startsWith("Build: ")) {
				currentVersion = getVersion(line);

				if (getVersionInt(line) <= oldVersion) {
					break;
				}

				continue;
			}

			if (!line.startsWith("=")) {
				if (line.isEmpty()) {
					currentEntry.append("<br/>");
					continue;
				}

				if (line.endsWith(":")) {
					currentEntry.appendText(line);
				} else {
					if (currentList == null) {
						currentList = new Element("ul");
						currentEntry.appendChild(currentList);
					}

					currentList.appendChild(new Element("li").text(line.trim()));
				}

				continue;
			}

			changelog.add(new ChangelogEntry(
					index++, "Biomes O' Plenty " + currentVersion, newFile.url(), currentEntry
			));
			currentEntry = new Element("div");
			currentList = null;
		}

		return changelog;
	}

	private static String getVersion(String string) {
		return string.replaceAll(".*((\\d+\\.){2}\\d+-(\\d+\\.){3}\\d+).*", "$1");
	}

	private static int getVersionInt(String string) {
		return Integer.parseInt(FULL_STOP_SPLITTER.splitToList(getVersion(string)).get(5));
	}
}
