package com.therandomlabs.changeloggenerator;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import com.therandomlabs.curseapi.CurseAPI;
import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;
import com.therandomlabs.utils.io.ZipFile;
import okio.BufferedSink;
import okio.Okio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class BasicChangelogGeneratorTest {
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

			try (BufferedSink sink = Okio.buffer(Okio.sink(Paths.get("changelog.txt")))) {
				sink.writeUtf8(changelog).writeUtf8(System.lineSeparator());
			}
		}
	}
}
