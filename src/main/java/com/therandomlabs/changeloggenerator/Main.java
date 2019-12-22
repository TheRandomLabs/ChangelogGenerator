package com.therandomlabs.changeloggenerator;

import java.nio.file.Paths;

import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;
import okio.BufferedSink;
import okio.Okio;

public final class Main {
	private Main() {}

	public static void main(String[] args) throws Exception {
		//TODO
		final CurseModpack oldModpack = CurseModpack.fromJSON(Paths.get("old.json"));
		final CurseModpack newModpack = CurseModpack.fromJSON(Paths.get("new.json"));

		try (BufferedSink sink = Okio.buffer(Okio.sink(Paths.get("changelog.txt")))) {
			sink.writeUtf8(new BasicChangelogGenerator().generate(oldModpack, newModpack)).
					writeUtf8(System.lineSeparator());
		}
	}
}
