package com.therandomlabs.changeloggenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import com.google.common.base.Preconditions;
import com.therandomlabs.changeloggenerator.provider.ActuallyAdditionsProvider;
import com.therandomlabs.changeloggenerator.provider.BiomesOPlentyProvider;
import com.therandomlabs.changeloggenerator.provider.ChangelogProvider;
import com.therandomlabs.changeloggenerator.provider.CurseChangelogProvider;
import com.therandomlabs.changeloggenerator.provider.McJtyProvider;
import com.therandomlabs.changeloggenerator.provider.MezzProvider;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.minecraft.CurseAPIMinecraft;
import com.therandomlabs.curseapi.minecraft.modpack.CurseModpack;
import org.jsoup.nodes.Element;

/**
 * Generates changelogs for CurseForge modpacks.
 */
public abstract class ChangelogGenerator {
	/**
	 * The current version of ChangelogGenerator.
	 */
	public static final String VERSION = "@VERSION@";

	private final List<ChangelogProvider> providers = new ArrayList<>();

	static {
		CurseAPIMinecraft.initialize();
	}

	/**
	 * Constructs a {@link ChangelogGenerator} instance with a default set of
	 * {@link CurseChangelogProvider}s.
	 */
	protected ChangelogGenerator() {
		withProvider(CurseChangelogProvider.instance);
		withProvider(ActuallyAdditionsProvider.instance);
		withProvider(BiomesOPlentyProvider.instance);
		withProvider(McJtyProvider.instance);
		withProvider(MezzProvider.instance);
	}

	/**
	 * Registers the specified {@link ChangelogProvider} to this {@link ChangelogGenerator}
	 * instance with the highest priority.
	 *
	 * @param provider a {@link ChangelogProvider}.
	 * @return this {@link ChangelogGenerator}.
	 */
	public final ChangelogGenerator withProvider(ChangelogProvider provider) {
		Preconditions.checkNotNull(provider, "provider should not be null");
		Preconditions.checkArgument(
				!providers.contains(provider), "provider should not already be registered"
		);
		providers.add(0, provider);
		return this;
	}

	/**
	 * Returns the {@link Changelog} for the specified {@link CurseFileChange} by iterating
	 * through registered {@link ChangelogProvider}s and calling
	 * {@link ChangelogProvider#getChangelog(CurseFileChange)}.
	 *
	 * @param fileChange a {@link CurseFileChange}.
	 * @return the {@link Changelog} for the specified {@link CurseFileChange}.
	 * @throws CurseException if an error occurs.
	 * @see #withProvider(ChangelogProvider)
	 */
	public Changelog getChangelog(CurseFileChange<? extends BasicCurseFile> fileChange)
			throws CurseException {
		for (ChangelogProvider provider : providers) {
			final SortedSet<ChangelogEntry> changelog = provider.getChangelog(fileChange);

			if (changelog != null) {
				//Call ChangelogProvider#processChangelog(CurseFileChange, Element).
				for (ChangelogEntry changelogEntry : changelog) {
					Element entry = changelogEntry.entry();

					for (ChangelogProvider provider2 : providers) {
						entry = provider2.processChangelog(fileChange, entry);
					}

					changelogEntry.setEntry(entry);
				}

				return new Changelog(fileChange, changelog);
			}
		}

		throw new CurseException("Could not retrieve changelogs for: " + fileChange);
	}

	/**
	 * Generates a changelog that details changes between an older and newer version of a
	 * CurseForge modpack.
	 *
	 * @param oldModpack a {@link CurseModpack} instance that represents an older version of a
	 * CurseForge modpack.
	 * @param newModpack a {@link CurseModpack} instance that represents a newer version of a
	 * CurseForge modpack.
	 * @return the generated changelog.
	 * @throws CurseException if an error occurs.
	 */
	public abstract String generate(CurseModpack oldModpack, CurseModpack newModpack)
			throws CurseException;
}
