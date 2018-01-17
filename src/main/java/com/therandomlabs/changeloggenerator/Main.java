package com.therandomlabs.changeloggenerator;

import java.nio.file.Paths;
import com.therandomlabs.curseapi.minecraft.modpack.manifest.ExtendedCurseManifest;
import com.therandomlabs.utils.io.NIOUtils;

public final class Main {
	public static void main(String[] args) throws Exception {
		final String oldFile = args.length > 0 ? args[0] : "old.json";
		final String newFile = args.length > 1 ? args[1] : "new.json";

		final ExtendedCurseManifest oldManifest = ExtendedCurseManifest.from(oldFile);
		final ExtendedCurseManifest manifest = ExtendedCurseManifest.from(newFile);

		final Changelog changelog = Changelog.changelog(oldManifest, manifest);
		final Changelog shortChangelog = Changelog.changelog(oldManifest, manifest, false);

		NIOUtils.write(Paths.get("changelog.txt"), changelog.toString());
		NIOUtils.write(Paths.get("shortchangelog.txt"), shortChangelog.toString());
	}
}
