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

public class BasicChangelogGenerator extends ChangelogGenerator {
	public static final BasicChangelogGenerator instance = new BasicChangelogGenerator();

	protected BasicChangelogGenerator() {
		//Default constructor.
	}

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
			appendChangelogs(builder, "Updated", comparison.updated());
			separateSections(builder);
		}

		if (!comparison.downgraded().isEmpty()) {
			appendChangelogs(builder, "Downgraded", comparison.downgraded());
			separateSections(builder);
		}

		if (!comparison.removed().isEmpty()) {
			appendFiles(builder, "Removed", comparison.removed());
			separateSections(builder);
		}

		appendTail(builder);
		return builder.toString();
	}

	protected void separateSections(StringBuilder builder) {
		builder.append(System.lineSeparator()).append(System.lineSeparator());
	}

	protected void appendTitle(StringBuilder builder, String title) {
		builder.append(title).append(':');
	}

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

	protected void appendChangelogs(
			StringBuilder builder, String title, Set<CurseFileChange<BasicCurseFile>> fileChanges
	) throws CurseException {
		builder.append(title).append(':');

		final Map<String, Changelog> allChangelogs = new TreeMap<>(CurseAPI.parallelMap(
				fileChanges, fileChange -> fileChange.project().name(), this::getChangelog
		));

		for (Map.Entry<String, Changelog> changelog : allChangelogs.entrySet()) {
			builder.append(System.lineSeparator());
			appendChangelogs(builder, changelog.getKey(), changelog.getValue());
		}
	}

	protected void appendChangelogs(StringBuilder builder, String projectName, Changelog changelog)
			throws CurseException {
		final CurseFileChange<CurseFile> fileChange = changelog.fileChange();
		builder.append('\t').append(fileChange.project().name()).append(" (").
				append(fileChange.oldFile().displayName()).append(" --> ").
				append(fileChange.newFile().displayName()).append("):");

		for (ChangelogEntry entry : changelog.entries()) {
			builder.append(System.lineSeparator()).append("\t\t").
					append(entry.displayName()).append(':');

			for (String line : JsoupUtils.getPlainText(entry.entry()).split("\n")) {
				builder.append(System.lineSeparator());

				if (!line.isEmpty()) {
					builder.append("\t\t\t").append(line);
				}
			}
		}
	}

	protected void appendTail(StringBuilder builder) {
		builder.append("Generated using [ChangelogGenerator");

		//If this is a development environment, we don't append the version.
		if (VERSION.length() != 10) {
			builder.append(' ').append(VERSION);
		}

		builder.append("](https://github.com/TheRandomLabs/ChangelogGenerator) by TheRandomLabs.");
	}
}
