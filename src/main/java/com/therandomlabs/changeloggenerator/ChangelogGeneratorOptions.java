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

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;
import okio.BufferedSink;
import okio.Okio;
import org.checkerframework.checker.nullness.qual.Nullable;
import picocli.CommandLine;

/**
 * Options for ChangelogGenerator.
 * This class also contains the main method for the runnable ChangelogGenerator application.
 */
@CommandLine.Command(
		mixinStandardHelpOptions = true, version = ChangelogGenerator.VERSION,
		description = "Generates changelogs for CurseForge modpacks."
)
public final class ChangelogGeneratorOptions implements Callable<Integer> {
	@CommandLine.Option(
			names = {"-o", "--old-manifest"},
			description = "The old modpack manifest. \"old.json\" by default."
	)
	@Nullable
	public Path oldManifest;

	@CommandLine.Option(
			names = {"-n", "--new-manifest"},
			description = "The new modpack manifest. \"new.json\" by default."
	)
	@Nullable
	public Path newManifest;

	@CommandLine.Option(
			names = {"-O", "--output"},
			description = "The changelog output location. \"changelog.txt\" or " +
					"\"changelog.md\" by default."
	)
	@Nullable
	public Path output;

	@CommandLine.Option(
			names = {"-l", "--lines"},
			description = "The maximum number of lines to display in a changelog entry."
	)
	public int maxEntryLineCount;

	@CommandLine.Option(
			names = {"-m", "--markdown"},
			description = "Generate a Markdown changelog."
	)
	public boolean markdown;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer call() throws Exception {
		if (oldManifest == null) {
			oldManifest = Paths.get("old.json");
		}

		if (newManifest == null) {
			newManifest = Paths.get("new.json");
		}

		if (output == null) {
			output = Paths.get(markdown ? "changelog.md" : "changelog.txt");
		}

		if (maxEntryLineCount < 0) {
			throw new IllegalArgumentException("Cannot display negative number of lines");
		}

		final CurseModpack oldModpack = CurseModpack.fromJSON(oldManifest);
		final CurseModpack newModpack = CurseModpack.fromJSON(newManifest);
		final ChangelogGenerator generator =
				markdown ? MarkdownChangelogGenerator.instance : BasicChangelogGenerator.instance;
		final String changelog = generator.generate(oldModpack, newModpack, this);

		try (BufferedSink sink = Okio.buffer(Okio.sink(output))) {
			sink.writeUtf8(changelog).writeUtf8(System.lineSeparator());
		}

		return 0;
	}

	/**
	 * The main method for the runnable ChangelogGenerator application.
	 *
	 * @param args the command-line arguments.
	 */
	public static void main(String[] args) {
		System.exit(
				new CommandLine(new ChangelogGeneratorOptions()).
						registerConverter(Path.class, Paths::get).
						execute(args)
		);
	}
}
