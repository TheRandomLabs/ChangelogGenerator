package com.therandomlabs.changeloggenerator;

import java.nio.file.Paths;
import com.therandomlabs.curseapi.minecraft.modpack.manifest.Changelog;
import com.therandomlabs.curseapi.minecraft.modpack.manifest.ExtendedCurseManifest;
import com.therandomlabs.utils.io.NIOUtils;

public final class Main {
	public static void main(String[] args) throws Exception {
		final ExtendedCurseManifest oldManifest = ExtendedCurseManifest.from("old.json");
		final ExtendedCurseManifest manifest = ExtendedCurseManifest.from("new.json");

		final Changelog changelog = Changelog.changelog(oldManifest, manifest);
		NIOUtils.write(Paths.get("changelog.txt"), changelog.toString());
	}
}
