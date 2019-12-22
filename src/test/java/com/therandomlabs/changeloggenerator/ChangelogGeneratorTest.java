package com.therandomlabs.changeloggenerator;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.util.Optional;

import com.therandomlabs.curseapi.CurseAPI;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;
import com.therandomlabs.utils.io.ZipFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.slf4j.LoggerFactory;

public class ChangelogGeneratorTest {
	@Test
	public void atm4ChangelogIsValid(@TempDir Path tempDirectory) throws Exception {
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
			final String changelog = new BasicChangelogGenerator().generate(oldModpack, newModpack);
			assertThat(changelog).isNotEmpty();

			LoggerFactory.getLogger(getClass()).info(changelog);
		}
	}

	@Test
	public void actuallyAdditionsChangelogIsValid() throws CurseException {
		testProject(228404, 2621643, 2844115);
	}

	@Test
	public void biomesOPlentyChangelogIsValid() throws CurseException {
		testProject(220318, 2814404, 2846238);
	}

	private static void testProject(int projectID, int oldFileID, int newFileID)
			throws CurseException {
		final CurseModpack oldModpack = CurseModpack.createEmpty();
		oldModpack.name("Old Test Modpack");
		oldModpack.files().add(new CurseFile.Immutable(projectID, oldFileID));

		final CurseModpack newModpack = CurseModpack.createEmpty();
		newModpack.name("New Test Modpack");
		newModpack.files().add(new CurseFile.Immutable(projectID, newFileID));

		final String changelog = new BasicChangelogGenerator().generate(oldModpack, newModpack);
		assertThat(changelog).isNotEmpty();

		LoggerFactory.getLogger(ChangelogGeneratorTest.class).info("\n{}", changelog);
	}
}
