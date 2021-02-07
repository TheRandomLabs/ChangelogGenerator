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

import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;
import okio.BufferedSink;
import okio.Okio;
import org.checkerframework.checker.nullness.qual.Nullable;
import picocli.CommandLine;

/**
 * The main class for the runnable ChangelogGenerator application.
 */
public final class Main {
	@Nullable
	private static ChangelogGeneratorOptions options;

	@Nullable
	private static CommandLine commandLine;

	private Main() {}

	/**
	 * The main method for the runnable ChangelogGenerator application.
	 *
	 * @param args the command-line arguments.
	 */
	public static void main(String[] args) throws Exception {
		options = new ChangelogGeneratorOptions(Main::run);
		commandLine = new CommandLine(options).registerConverter(Path.class, Paths::get);
		System.exit(commandLine.execute(args));
	}

	private static Integer run() throws Exception {
		if (options == null) {
			throw new IllegalStateException("options should not be null");
		}

		if (commandLine == null) {
			throw new IllegalStateException("commandLine should not be null");
		}

		if (options.maxEntryLineCount < 0) {
			System.err.println("Cannot display negative number of lines");
			return 1;
		}

		if (options.maxEntryCount < 0) {
			System.err.println("Cannot display negative number of entries");
			return 1;
		}

		if (options.oldManifest == null) {
			options.oldManifest = Paths.get("old.json");
		}

		if (options.newManifest == null) {
			options.newManifest = Paths.get("new.json");
		}

		if (options.output == null) {
			options.output = Paths.get(options.markdown ? "changelog.md" : "changelog.txt");
		}

		final CurseModpack oldModpack = CurseModpack.fromJSON(options.oldManifest);
		final CurseModpack newModpack = CurseModpack.fromJSON(options.newManifest);
		final ChangelogGenerator generator = options.markdown ?
				new MarkdownChangelogGenerator(options) : new BasicChangelogGenerator(options);
		final String changelog = generator.generate(oldModpack, newModpack);

		try (BufferedSink sink = Okio.buffer(Okio.sink(options.output))) {
			sink.writeUtf8(changelog).writeUtf8(System.lineSeparator());
		}

		return 0;
	}
}
