package com.therandomlabs.changeloggenerator.provider;

import java.util.SortedSet;
import java.util.TreeSet;

import com.therandomlabs.changeloggenerator.ChangelogEntry;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import org.jsoup.nodes.Element;

public final class BiomesOPlentyProvider implements ChangelogProvider {
	public static final BiomesOPlentyProvider instance = new BiomesOPlentyProvider();

	private BiomesOPlentyProvider() {}

	@Override
	public SortedSet<ChangelogEntry> getChangelog(
			CurseFileChange<? extends BasicCurseFile> fileChange
	) throws CurseException {
		if (fileChange.projectID() != 220318) {
			return null;
		}

		final String fullChangelog = fileChange.newerCurseFile().changelogPlainText();

		if (!fullChangelog.startsWith("Build: ")) {
			return null;
		}

		final int oldVersion = getVersionInt(fileChange.olderCurseFile().displayName());

		final SortedSet<ChangelogEntry> changelog = new TreeSet<>();

		int index = 0;
		String currentVersion = "";

		Element currentEntry = new Element("div");
		Element currentList = null;

		for (String line : fullChangelog.split(System.lineSeparator())) {
			if (line.startsWith("Build: ")) {
				currentVersion = getVersion(line);

				if (getVersionInt(line) <= oldVersion) {
					break;
				}

				continue;
			}

			if (!line.startsWith("=")) {
				if (line.isEmpty()) {
					currentEntry.append("<br/>");
					continue;
				}

				if (line.endsWith(":")) {
					currentEntry.appendText(line);
				} else {
					if (currentList == null) {
						currentList = new Element("ul");
						currentEntry.appendChild(currentList);
					}

					currentList.appendChild(new Element("li").text(line.trim()));
				}

				continue;
			}

			changelog.add(new ChangelogEntry(
					index++, "Biomes O' Plenty " + currentVersion, currentEntry
			));
			currentEntry = new Element("div");
			currentList = null;
		}

		return changelog;
	}

	private static String getVersion(String string) {
		return string.replaceAll(".*(\\d+\\.\\d+\\.\\d+-\\d+\\.\\d+\\.\\d+\\.\\d+).*", "$1");
	}

	private static int getVersionInt(String string) {
		return Integer.parseInt(getVersion(string).split("\\.")[5]);
	}
}
