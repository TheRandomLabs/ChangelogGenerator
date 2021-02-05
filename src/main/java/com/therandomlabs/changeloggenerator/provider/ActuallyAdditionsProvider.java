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
import com.therandomlabs.changeloggenerator.CommonMarkUtils;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.file.CurseFiles;
import com.therandomlabs.curseapi.util.OkHttpUtils;
import okhttp3.HttpUrl;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jsoup.nodes.Element;

/**
 * An implementation of {@link ChangelogProvider} for Actually Additions.
 */
public final class ActuallyAdditionsProvider implements ChangelogProvider {
	/**
	 * The singleton instance of {@link ActuallyAdditionsProvider}.
	 */
	public static final ActuallyAdditionsProvider instance = new ActuallyAdditionsProvider();

	private static final Splitter HYPHEN_SPLITTER = Splitter.on('-');

	private static final HttpUrl CHANGELOG_URL = HttpUrl.get(
			"https://raw.githubusercontent.com/Ellpeck/ActuallyAdditions/main/update/changelog.md"
	);

	private ActuallyAdditionsProvider() {}

	/**
	 * {@inheritDoc}
	 */
	@Nullable
	@Override
	public SortedSet<ChangelogEntry> getChangelog(
			CurseFileChange<? extends BasicCurseFile> fileChange
	) throws CurseException {
		if (fileChange.projectID() != 228404) {
			return null;
		}

		final CurseFiles<CurseFile> files = ChangelogProvider.getFilesBetweenInclusive(fileChange);
		final int oldVersion = getVersion(files.last());
		final int newVersion = getVersion(files.first());

		final Element fullChangelog = CommonMarkUtils.parseElement(OkHttpUtils.read(CHANGELOG_URL));
		final SortedSet<ChangelogEntry> changelog = new TreeSet<>();

		boolean newVersionFound = false;
		int index = 0;
		String currentVersion = "";

		for (Element element : fullChangelog.children()) {
			if ("h1".equals(element.tagName())) {
				currentVersion = element.text();

				final int currentVersionInt = getVersion(currentVersion);

				if (newVersionFound) {
					if (currentVersionInt <= oldVersion) {
						break;
					}
				} else if (currentVersionInt <= newVersion) {
					newVersionFound = true;
				}

				continue;
			}

			if (newVersionFound && "ul".equals(element.tagName())) {
				changelog.add(new ChangelogEntry(
						index++, "Actually Additions " + currentVersion, CHANGELOG_URL, element
				));
			}
		}

		return changelog;
	}

	private static int getVersion(CurseFile file) {
		return getVersion(
				file.displayName().replaceAll("ActuallyAdditions-([\\d.]+-r\\d+).*", "$1")
		);
	}

	private static int getVersion(String version) {
		return Integer.parseInt(HYPHEN_SPLITTER.splitToList(version).get(1).substring(1));
	}
}
