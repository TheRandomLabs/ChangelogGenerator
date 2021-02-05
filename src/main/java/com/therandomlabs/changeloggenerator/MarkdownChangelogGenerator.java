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

package com.therandomlabs.changeloggenerator;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.file.CurseFiles;
import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.curseapi.util.JsoupUtils;

/**
 * A variant of {@link BasicChangelogGenerator} that generates a Markdown changelog.
 */
public class MarkdownChangelogGenerator extends BasicChangelogGenerator {
	/**
	 * The singleton instance of {@link MarkdownChangelogGenerator}.
	 */
	public static final MarkdownChangelogGenerator instance = new MarkdownChangelogGenerator();

	/**
	 * Constructs a {@link MarkdownChangelogGenerator}.
	 */
	protected MarkdownChangelogGenerator() {
		//Default constructor.
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void appendTitle(StringBuilder builder, String title) {
		builder.append("## ").append(title).append(System.lineSeparator());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void appendModpackVersions(
			StringBuilder builder, CurseModpack oldModpack, CurseModpack newModpack
	) throws CurseException {
		builder.append("# ").append(oldModpack.name()).append(' ').append(oldModpack.version()).
				append("⟶").append(newModpack.name()).append(' ').append(newModpack.version());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void appendFiles(
			StringBuilder builder, String title, CurseFiles<BasicCurseFile> files
	) throws CurseException {
		appendTitle(builder, title);

		final Set<String> projectNames = files.parallelMap(
				file -> Optional.ofNullable(file.project()).
						map(project -> "[" + project.name() + "](" + project.url() + ")").
						orElse("Deleted project"),
				Collectors.toCollection(TreeSet::new)
		);

		for (String projectName : projectNames) {
			builder.append(System.lineSeparator()).append("* ").append(projectName);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("Duplicates")
	@Override
	protected void appendChangelogEntries(
			StringBuilder builder, String projectName, ChangelogEntries changelogEntries
	) throws CurseException {
		final CurseFileChange<BasicCurseFile> fileChange = changelogEntries.fileChange();

		final CurseFile oldFile = fileChange.oldCurseFile();
		final String oldDisplayName;

		if (oldFile == null) {
			oldDisplayName = "Archived file";
		} else {
			oldDisplayName = "[" + oldFile.displayName() + "](" + oldFile.url() + ")";
		}

		final CurseFile newFile = fileChange.newCurseFile();
		final String newDisplayName;

		if (newFile == null) {
			newDisplayName = "Archived file";
		} else {
			newDisplayName = "[" + newFile.displayName() + "](" + newFile.url() + ")";
		}

		builder.append("### ").append(
				Optional.ofNullable(fileChange.project()).
						map(project -> "[" + project.name() + "](" + project.url() + ")").
						orElse("Deleted project")
		).append(" (").append(oldDisplayName).append("⟶").append(newDisplayName).append(")");

		if (changelogEntries.entries().isEmpty()) {
			builder.append(System.lineSeparator());
			return;
		}

		for (ChangelogEntry entry : changelogEntries.entries()) {
			builder.append(System.lineSeparator()).append(System.lineSeparator()).append("#### [").
					append(entry.title()).append("](").append(entry.url()).append(")").
					append(System.lineSeparator());

			Iterable<String> entryLines;

			if (JsoupUtils.isEmpty(entry.entry())) {
				entryLines = Collections.singleton("No changelog available.");
			} else {
				entryLines = LINE_SEPARATOR_SPLITTER.split(JsoupUtils.getPlainText(entry.entry()));
			}

			for (String line : entryLines) {
				builder.append(System.lineSeparator());

				if (!line.isEmpty()) {
					//For consistency, we change "- " and "+ " to "* " at line beginnings.
					if ((line.startsWith("- ") || line.startsWith("+ ")) && line.length() > 2) {
						builder.append("* ").append(line.substring(2));
					} else {
						builder.append(line);
					}
				}
			}

			builder.append(System.lineSeparator());
		}
	}
}
