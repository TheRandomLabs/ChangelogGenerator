package com.therandomlabs.changeloggenerator;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.therandomlabs.curseapi.CurseAPI;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.file.CurseFiles;
import com.therandomlabs.curseapi.file.CurseFilesComparison;
import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;
import com.therandomlabs.curseapi.util.JsoupUtils;

/**
 * A basic implementation of {@link ChangelogGenerator} that generates a plaintext changelog.
 */
public class BasicChangelogGenerator extends ChangelogGenerator {
	/**
	 * The singleton instance of {@link BasicChangelogGenerator}.
	 */
	public static final BasicChangelogGenerator instance = new BasicChangelogGenerator();

	/**
	 * Constructs a {@link BasicChangelogGenerator}.
	 */
	protected BasicChangelogGenerator() {
		//Default constructor.
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String generate(CurseModpack oldModpack, CurseModpack newModpack) throws CurseException {
		final CurseFilesComparison<BasicCurseFile> comparison =
				CurseFilesComparison.of(oldModpack.files(), newModpack.files());
		final StringBuilder builder = new StringBuilder();

		appendModpackVersions(builder, oldModpack, newModpack);
		separateSections(builder);

		if (!comparison.added().isEmpty()) {
			appendFiles(builder, "Added", comparison.added());
			separateSections(builder);
		}

		if (!comparison.updated().isEmpty()) {
			appendChangelog(builder, "Updated", comparison.updated());
			separateSections(builder);
		}

		if (!comparison.downgraded().isEmpty()) {
			appendChangelog(builder, "Downgraded", comparison.downgraded());
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
				file -> file.project().name(),
				Collectors.toCollection(TreeSet::new)
		);

		for (String projectName : projectNames) {
			builder.append(System.lineSeparator()).append("- ").append(projectName);
		}
	}

	/**
	 * Appends the changelogs for the specified {@link CurseFileChange}s to the changelog
	 * under the specified section title.
	 *
	 * @param builder a {@link StringBuilder} to append to.
	 * @param title a section title.
	 * @param fileChanges a {@link Set} of {@link CurseFileChange}.s
	 * @throws CurseException if an error occurs.
	 */
	protected void appendChangelog(
			StringBuilder builder, String title, Set<CurseFileChange<BasicCurseFile>> fileChanges
	) throws CurseException {
		builder.append(title).append(':');

		final Map<String, Changelog> allChangelogs = new TreeMap<>(CurseAPI.parallelMap(
				fileChanges, fileChange -> fileChange.project().name(), this::getChangelog
		));

		for (Map.Entry<String, Changelog> changelog : allChangelogs.entrySet()) {
			builder.append(System.lineSeparator());
			appendChangelog(builder, changelog.getKey(), changelog.getValue());
		}

		builder.setLength(builder.length() - System.lineSeparator().length());
	}

	/**
	 * Appends a {@link Changelog} to the changelog.
	 *
	 * @param builder a {@link StringBuilder} to append to.
	 * @param projectName a project name.
	 * @param changelog a {@link Changelog}.
	 * @throws CurseException if an error occurs.
	 */
	protected void appendChangelog(StringBuilder builder, String projectName, Changelog changelog)
			throws CurseException {
		final CurseFileChange<BasicCurseFile> fileChange = changelog.fileChange();

		final CurseFile oldFile = fileChange.oldCurseFile();
		final String oldDisplayName = oldFile == null ? "Archived file" : oldFile.displayName();

		final CurseFile newFile = fileChange.newCurseFile();
		final String newDisplayName = newFile == null ? "Archived file" : newFile.displayName();

		builder.append('\t').append(fileChange.project().name()).append(" (").
				append(oldDisplayName).append(" --> ").append(newDisplayName).append("):");

		for (ChangelogEntry entry : changelog.entries()) {
			builder.append(System.lineSeparator()).append("\t\t").append(entry.title()).append(':');

			final String[] entryLines =
					JsoupUtils.getPlainText(entry.entry()).split(System.lineSeparator());

			for (String line : entryLines) {
				builder.append(System.lineSeparator());

				if (!line.isEmpty()) {
					builder.append("\t\t\t").append(line);
				}
			}

			builder.append(System.lineSeparator());
		}
	}

	/**
	 * Appends a tail message to the changelog.
	 *
	 * @param builder a {@link StringBuilder} to append to.
	 */
	protected void appendTail(StringBuilder builder) {
		builder.append("Generated using [ChangelogGenerator ").append(VERSION).
				append("](https://github.com/TheRandomLabs/ChangelogGenerator).");
	}
}
