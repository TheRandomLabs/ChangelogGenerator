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

package com.therandomlabs.changeloggenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.therandomlabs.curseapi.CurseAPI;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.file.CurseFiles;
import com.therandomlabs.curseapi.file.CurseFilesComparison;
import com.therandomlabs.curseapi.game.CurseGameVersionGroup;
import com.therandomlabs.curseapi.minecraft.MCVersion;
import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;
import com.therandomlabs.curseapi.project.CurseProject;
import com.therandomlabs.curseapi.util.JsoupUtils;

/**
 * A basic implementation of {@link ChangelogGenerator} that generates a plaintext changelog.
 */
public class BasicChangelogGenerator extends ChangelogGenerator {
	/**
	 * A {@link Splitter} that works on line separators.
	 * Empty strings are omitted, and results are trimmed.
	 */
	protected static final Splitter LINE_SEPARATOR_SPLITTER = Splitter.on(System.lineSeparator()).
			omitEmptyStrings().
			trimResults();

	private final ChangelogGeneratorOptions options;

	/**
	 * Constructs a {@link BasicChangelogGenerator}.
	 */
	public BasicChangelogGenerator() {
		this(new ChangelogGeneratorOptions());
	}

	/**
	 * Constructs a {@link BasicChangelogGenerator}.
	 *
	 * @param options ChangelogGenerator options.
	 */
	public BasicChangelogGenerator(ChangelogGeneratorOptions options) {
		this.options = options;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String generate(CurseModpack oldModpack, CurseModpack newModpack) throws CurseException {
		final CurseFilesComparison<BasicCurseFile> comparison =
				CurseFilesComparison.of(oldModpack.basicFiles(), newModpack.basicFiles());
		final CurseGameVersionGroup<MCVersion> versionGroup = newModpack.mcVersion().versionGroup();
		final StringBuilder builder = new StringBuilder();

		appendModpackVersions(builder, oldModpack, newModpack);
		separateSections(builder);

		if (!comparison.added().isEmpty()) {
			appendFiles(builder, "Added", comparison.added());
			separateSections(builder);
		}

		if (!comparison.updated().isEmpty()) {
			appendChangelogEntries(builder, "Updated", comparison.updated(), versionGroup);
			separateSections(builder);
		}

		if (!comparison.downgraded().isEmpty()) {
			appendChangelogEntries(builder, "Downgraded", comparison.downgraded(), versionGroup);
			separateSections(builder);
		}

		if (!comparison.removed().isEmpty()) {
			appendFiles(builder, "Removed", comparison.removed());
			separateSections(builder);
		}

		appendTail(builder);
		return builder.toString();
	}

	/**
	 * Returns ChangelogGenerator options.
	 *
	 * @return a {@link ChangelogGeneratorOptions} instance.
	 */
	protected ChangelogGeneratorOptions getOptions() {
		return options;
	}

	/**
	 * Separates two different changelog sections, e.g. "Updated" and "Downgraded".
	 * By default, this is done by appending {@link System#lineSeparator()} twice.
	 *
	 * @param builder a {@link StringBuilder} to append to.
	 */
	protected void separateSections(StringBuilder builder) {
		builder.append(System.lineSeparator()).append(System.lineSeparator());
	}

	/**
	 * Appends the specified title to the changelog.
	 *
	 * @param builder a {@link StringBuilder} to append to.
	 * @param title a title.
	 */
	protected void appendTitle(StringBuilder builder, String title) {
		builder.append(title).append(':');
	}

	/**
	 * Appends the old and new modpack versions to the changelog.
	 *
	 * @param builder a {@link StringBuilder} to append to.
	 * @param oldModpack the old version of the modpack.
	 * @param newModpack the new version of the modpack.
	 * @throws CurseException if an error occurs.
	 */
	protected void appendModpackVersions(
			StringBuilder builder, CurseModpack oldModpack, CurseModpack newModpack
	) throws CurseException {
		final StringBuilder versions = new StringBuilder();
		versions.append(oldModpack.name()).append(' ').append(oldModpack.version()).append(" --> ").
				append(newModpack.name()).append(' ').append(newModpack.version());

		for (int i = 0; i < versions.length(); i++) {
			builder.append('=');
		}

		builder.append(System.lineSeparator()).append(versions).append(System.lineSeparator());

		for (int i = 0; i < versions.length(); i++) {
			builder.append('=');
		}
	}

	/**
	 * Appends the specified files to the modpack under the specified section title.
	 * By default, the files' project names are listed.
	 *
	 * @param builder a {@link StringBuilder} to append to.
	 * @param title a section title.
	 * @param files a {@link CurseFiles}.
	 * @throws CurseException if an error occurs.
	 */
	protected void appendFiles(
			StringBuilder builder, String title, CurseFiles<BasicCurseFile> files
	) throws CurseException {
		appendTitle(builder, title);

		final Set<String> projectNames = files.parallelMap(
				file -> Optional.ofNullable(file.project()).
						map(CurseProject::name).
						orElse("Deleted project"),
				Collectors.toCollection(TreeSet::new)
		);

		for (String projectName : projectNames) {
			builder.append(System.lineSeparator()).append("* ").append(projectName);
		}
	}

	/**
	 * Appends the changelogs for the specified {@link CurseFileChange}s to the changelog
	 * under the specified section title.
	 *
	 * @param builder a {@link StringBuilder} to append to.
	 * @param title a section title.
	 * @param fileChanges a {@link Set} of {@link CurseFileChange}s.
	 * @param fallbackVersionGroup the {@link CurseGameVersionGroup} to use if a game version group
	 * is necessary and cannot be determined.
	 * @throws CurseException if an error occurs.
	 */
	protected void appendChangelogEntries(
			StringBuilder builder, String title, Set<CurseFileChange<BasicCurseFile>> fileChanges,
			CurseGameVersionGroup<MCVersion> fallbackVersionGroup
	) throws CurseException {
		appendTitle(builder, title);

		final Map<String, ChangelogEntries> allEntries = new TreeMap<>(CurseAPI.parallelMap(
				fileChanges,
				fileChange -> Optional.ofNullable(fileChange.project()).
						map(CurseProject::name).
						orElseGet(() -> "Deleted project (" + fileChange.projectID() + ")"),
				fileChange -> getChangelogEntries(fileChange, fallbackVersionGroup)
		));

		for (Map.Entry<String, ChangelogEntries> changelogEntries : allEntries.entrySet()) {
			builder.append(System.lineSeparator());
			appendChangelogEntries(builder, changelogEntries.getKey(), changelogEntries.getValue());
		}

		builder.setLength(builder.length() - System.lineSeparator().length());
	}

	/**
	 * Appends a {@link ChangelogEntries} to the changelog.
	 *
	 * @param builder a {@link StringBuilder} to append to.
	 * @param projectName a project name.
	 * @param changelogEntries a {@link ChangelogEntries}.
	 * @throws CurseException if an error occurs.
	 */
	@SuppressWarnings("Duplicates")
	protected void appendChangelogEntries(
			StringBuilder builder, String projectName, ChangelogEntries changelogEntries
	) throws CurseException {
		final CurseFileChange<BasicCurseFile> fileChange = changelogEntries.fileChange();

		final CurseFile oldFile = fileChange.oldCurseFile();
		final String oldDisplayName = oldFile == null ? "Archived file" : oldFile.displayName();

		final CurseFile newFile = fileChange.newCurseFile();
		final String newDisplayName = newFile == null ? "Archived file" : newFile.displayName();

		builder.append('\t').append(
				Optional.ofNullable(fileChange.project()).
						map(CurseProject::name).
						orElse("Deleted project")
		).append(" (").append(oldDisplayName).append(" --> ").append(newDisplayName).append("):");

		List<ChangelogEntry> entries = new ArrayList<>(changelogEntries.entries());

		if (entries.isEmpty()) {
			return;
		}

		final int maxEntries = getOptions().maxEntryCount;
		final int extraEntries = maxEntries == 0 ? 0 : entries.size() - maxEntries;

		if (extraEntries > 0) {
			entries = entries.subList(0, maxEntries);
		}

		for (ChangelogEntry entry : entries) {
			builder.append(System.lineSeparator()).append("\t\t").append(entry.title()).append(':');

			List<String> entryLines;

			if (JsoupUtils.isEmpty(entry.entry())) {
				entryLines = Lists.newArrayList("No changelog available.");
			} else {
				entryLines = Lists.newArrayList(
						LINE_SEPARATOR_SPLITTER.split(JsoupUtils.getPlainText(entry.entry()))
				);
			}

			final int maxLines = getOptions().maxEntryLineCount;

			if (maxLines != 0 && entryLines.size() > maxLines) {
				final int extra = entryLines.size() - maxLines;
				entryLines.set(maxLines, "(" + extra + " more line" + (extra == 1 ? ")" : "s)"));
				entryLines = entryLines.subList(0, maxLines + 1);
			}

			for (String line : entryLines) {
				builder.append(System.lineSeparator());

				if (!line.isEmpty()) {
					builder.append("\t\t\t");

					//For consistency, we change "- " and "+ " to "* " at line beginnings.
					if ((line.startsWith("- ") || line.startsWith("+ ") || line.startsWith("* ")) &&
							line.length() > 2) {
						builder.append("* ").append(line.substring(2).trim());
					} else {
						builder.append(line);
					}
				}
			}

			builder.append(System.lineSeparator());
		}

		if (extraEntries > 0) {
			builder.append(System.lineSeparator()).append("\t\t").append(extraEntries).
					append(" more entr").append(extraEntries == 1 ? "y" : "ies").
					append(System.lineSeparator());
		}
	}

	/**
	 * Appends a tail message to the changelog.
	 *
	 * @param builder a {@link StringBuilder} to append to.
	 */
	protected void appendTail(StringBuilder builder) {
		builder.append("Generated using ChangelogGenerator ").append(VERSION).
				append(": https://github.com/TheRandomLabs/ChangelogGenerator");
	}
}
