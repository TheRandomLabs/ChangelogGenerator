package com.therandomlabs.changeloggenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import com.google.common.base.Preconditions;
import com.therandomlabs.changeloggenerator.provider.ChangelogProvider;
import com.therandomlabs.changeloggenerator.provider.CurseChangelogProvider;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;

public abstract class ChangelogGenerator {
	public static final String VERSION = "CG_VERSION";

	private final List<ChangelogProvider> providers = new ArrayList<>();

	protected ChangelogGenerator() {
		withProvider(CurseChangelogProvider.instance);
	}

	public ChangelogGenerator withProvider(ChangelogProvider provider) {
		Preconditions.checkNotNull(provider, "provider should not be null");
		Preconditions.checkArgument(
				!providers.contains(provider), "provider should not already be registered"
		);
		providers.add(0, provider);
		return this;
	}

	public Changelog getChangelog(CurseFileChange<? extends BasicCurseFile> fileChange)
			throws CurseException {
		for (ChangelogProvider provider : providers) {
			final SortedSet<ChangelogEntry> changelog = provider.getChangelog(fileChange);

			if (changelog != null) {
				return new Changelog(
						new CurseFileChange<>(fileChange.oldCurseFile(), fileChange.newCurseFile()),
						changelog
				);
			}
		}

		throw new CurseException("Could not retrieve changelogs for: " + fileChange);
	}

	public abstract String generate(CurseModpack oldModpack, CurseModpack newModpack)
			throws CurseException;
}
