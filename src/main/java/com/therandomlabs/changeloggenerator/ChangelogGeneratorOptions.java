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

import java.nio.file.Path;
import java.util.concurrent.Callable;

import org.checkerframework.checker.nullness.qual.Nullable;
import picocli.CommandLine;

/**
 * Options for ChangelogGenerator.
 */
@CommandLine.Command(
		mixinStandardHelpOptions = true, version = ChangelogGenerator.VERSION,
		description = "Generates changelogs for CurseForge modpacks."
)
public final class ChangelogGeneratorOptions implements Callable<Integer> {
	@CommandLine.Option(
			names = {"-l", "--lines"},
			description = "The maximum number of lines to display in a changelog entry."
	)
	public int maxEntryLineCount;

	@CommandLine.Option(
			names = {"-e", "--entries"},
			description = "The maximum number of changelog entries to display per mod."
	)
	public int maxEntryCount;

	@CommandLine.Option(
			names = {"-m", "--markdown"},
			description = "Generate a Markdown changelog."
	)
	boolean markdown;

	@CommandLine.Option(
			names = {"-o", "--old-manifest"},
			description = "The old modpack manifest. \"old.json\" by default."
	)
	@Nullable
	Path oldManifest;

	@CommandLine.Option(
			names = {"-n", "--new-manifest"},
			description = "The new modpack manifest. \"new.json\" by default."
	)
	@Nullable
	Path newManifest;

	@CommandLine.Option(
			names = {"-O", "--output"},
			description = "The changelog output location. \"changelog.txt\" or " +
					"\"changelog.md\" by default."
	)
	@Nullable
	Path output;

	@Nullable
	private final Callable<Integer> callable;

	/**
	 * Constructs a {@link ChangelogGeneratorOptions}.
	 */
	public ChangelogGeneratorOptions() {
		this(null);
	}

	ChangelogGeneratorOptions(@Nullable Callable<Integer> callable) {
		this.callable = callable;
	}

	/**
	 * This should not be called directly.
	 *
	 * @return an exit code.
	 */
	@Override
	public Integer call() throws Exception {
		return callable == null ? Integer.valueOf(0) : callable.call();
	}
}
