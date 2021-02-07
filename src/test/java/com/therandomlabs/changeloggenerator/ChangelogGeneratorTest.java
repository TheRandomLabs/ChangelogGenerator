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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.nio.file.Path;
import java.util.Optional;

import com.therandomlabs.changeloggenerator.provider.CurseChangelogProvider;
import com.therandomlabs.curseapi.CurseAPI;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFilesComparison;
import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;
import com.therandomlabs.utils.io.ZipFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.slf4j.LoggerFactory;

class ChangelogGeneratorTest {
	@Test
	void exceptionShouldBeThrownIfDuplicateProviderIsRegistered() {
		assertThatThrownBy(() -> new BasicChangelogGenerator().withProvider(
				CurseChangelogProvider.instance
		)).isInstanceOf(IllegalArgumentException.class).
				hasMessageContaining("should not already be registered");
	}

	@Test
	void atm4ChangelogIsValid(@TempDir Path tempDirectory) throws Exception {
		final Optional<Path> optionalOldModpackPath =
				CurseAPI.downloadFileToDirectory(316059, 2839369, tempDirectory);
		assertThat(optionalOldModpackPath).isPresent();

		final Path oldModpackPath = optionalOldModpackPath.get();
		assertThat(oldModpackPath).isRegularFile();

		final Optional<Path> optionalNewModpackPath =
				CurseAPI.downloadFileToDirectory(316059, 2844397, tempDirectory);
		assertThat(optionalNewModpackPath).isPresent();

		final Path newModpackPath = optionalNewModpackPath.get();
		assertThat(newModpackPath).isRegularFile();

		try (
				ZipFile oldModpackZipFile = new ZipFile(oldModpackPath);
				ZipFile newModpackZipFile = new ZipFile(newModpackPath)
		) {
			final CurseModpack oldModpack =
					CurseModpack.fromJSON(oldModpackZipFile.getEntry("manifest.json"));
			final CurseModpack newModpack =
					CurseModpack.fromJSON(newModpackZipFile.getEntry("manifest.json"));

			final String basic = new BasicChangelogGenerator().generate(oldModpack, newModpack);
			assertThat(basic).isNotEmpty();

			final String markdown =
					new MarkdownChangelogGenerator().generate(oldModpack, newModpack);
			assertThat(markdown).isNotEmpty();

			final CurseFilesComparison<BasicCurseFile> comparison =
					CurseFilesComparison.of(oldModpack.files(), newModpack.files());
			final ChangelogEntries entries = new BasicChangelogGenerator().getChangelogEntries(
					comparison.updated().iterator().next(), oldModpack.mcVersion().versionGroup()
			);

			assertThat(entries).isNotNull();
			assertThat(entries.toString()).isNotEmpty();
			assertThat(entries.entries()).isNotEmpty();
			assertThat(entries.entries().first().toString()).isNotEmpty();

			LoggerFactory.getLogger(getClass()).info(basic);
			LoggerFactory.getLogger(getClass()).info(markdown);
		}
	}

	@Test
	void actuallyAdditionsChangelogIsValid() throws CurseException {
		testProject(228404, 2621643, 2844115);
	}

	@Test
	void biomesOPlentyChangelogIsValid() throws CurseException {
		testProject(220318, 2814404, 2846238);
	}

	@Test
	void jeiChangelogIsValid() throws CurseException {
		testProject(238222, 2822850, 2847274);
	}

	@Test
	void rfToolsChangelogIsValid() throws CurseException {
		testProject(224641, 2672513, 2745848);
	}

	private static void testProject(int projectID, int oldFileID, int newFileID)
			throws CurseException {
		final CurseModpack oldModpack = CurseModpack.createEmpty();
		oldModpack.name("Old Test Modpack");
		oldModpack.basicFiles().add(new BasicCurseFile.Immutable(projectID, oldFileID));

		final CurseModpack newModpack = CurseModpack.createEmpty();
		newModpack.name("New Test Modpack");
		newModpack.basicFiles().add(new BasicCurseFile.Immutable(projectID, newFileID));

		final String changelog = new BasicChangelogGenerator().generate(oldModpack, newModpack);
		assertThat(changelog).isNotEmpty();

		LoggerFactory.getLogger(ChangelogGeneratorTest.class).info("\n{}", changelog);
	}
}
