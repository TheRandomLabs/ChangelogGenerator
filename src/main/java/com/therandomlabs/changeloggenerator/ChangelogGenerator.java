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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates changelogs for CurseForge modpacks.
 */
public abstract class ChangelogGenerator {
	/**
	 * The current version of ChangelogGenerator.
	 */
	public static final String VERSION = "@VERSION@";

	private static final Logger logger = LoggerFactory.getLogger(ChangelogGenerator.class);

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
	 * Returns the {@link ChangelogEntries} for the specified {@link CurseFileChange} by iterating
	 * through the registered {@link ChangelogProvider}s and calling
	 * {@link ChangelogProvider#getChangelog(CurseFileChange)}.
	 *
	 * @param fileChange a {@link CurseFileChange}.
	 * @return the {@link ChangelogEntries} for the specified {@link CurseFileChange}.
	 * @throws CurseException if an error occurs.
	 * @see #withProvider(ChangelogProvider)
	 */
	public ChangelogEntries getChangelogEntries(
			CurseFileChange<? extends BasicCurseFile> fileChange
	) throws CurseException {
		logger.info("Retrieving changelog entries for file change: {}", fileChange);

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

				return new ChangelogEntries(fileChange, changelog);
			}
		}

		throw new CurseException("Could not retrieve changelog entries for: " + fileChange);
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
	public String generate(CurseModpack oldModpack, CurseModpack newModpack) throws CurseException {
		return generate(oldModpack, newModpack, new ChangelogGeneratorOptions());
	}

	/**
	 * Generates a changelog that details changes between an older and newer version of a
	 * CurseForge modpack.
	 *
	 * @param oldModpack a {@link CurseModpack} instance that represents an older version of a
	 * CurseForge modpack.
	 * @param newModpack a {@link CurseModpack} instance that represents a newer version of a
	 * CurseForge modpack.
	 * @param options ChangelogGenerator options.
	 * @return the generated changelog.
	 * @throws CurseException if an error occurs.
	 */
	public abstract String generate(
			CurseModpack oldModpack, CurseModpack newModpack, ChangelogGeneratorOptions options
	) throws CurseException;

	/**
	 * Registers the specified {@link ChangelogProvider} to this {@link ChangelogGenerator}
	 * instance with the highest priority.
	 *
	 * @param provider a {@link ChangelogProvider}.
	 * @return this {@link ChangelogGenerator}.
	 */
	protected final ChangelogGenerator withProvider(ChangelogProvider provider) {
		Preconditions.checkNotNull(provider, "provider should not be null");
		Preconditions.checkArgument(
				!providers.contains(provider), "provider should not already be registered"
		);
		providers.add(0, provider);
		return this;
	}
}
