package com.therandomlabs.changeloggenerator.provider;

import java.util.SortedSet;
import java.util.TreeSet;

import com.therandomlabs.changeloggenerator.ChangelogEntry;
import com.therandomlabs.changeloggenerator.CommonMarkUtils;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.util.OkHttpUtils;
import okhttp3.HttpUrl;
import org.jsoup.nodes.Element;

public final class ActuallyAdditionsProvider implements ChangelogProvider {
	public static final HttpUrl CHANGELOG_URL = HttpUrl.get(
			"https://raw.githubusercontent.com/Ellpeck/ActuallyAdditions/master/update/changelog.md"
	);

	public static final ActuallyAdditionsProvider instance = new ActuallyAdditionsProvider();

	private ActuallyAdditionsProvider() {}

	@Override
	public SortedSet<ChangelogEntry> getChangelog(
			CurseFileChange<? extends BasicCurseFile> fileChange
	) throws CurseException {
		if (fileChange.projectID() != 228404) {
			return null;
		}

		final int oldVersion = getVersion(fileChange.olderCurseFile());
		final int newVersion = getVersion(fileChange.newerCurseFile());

		final Element fullChangelog = CommonMarkUtils.parseElement(OkHttpUtils.read(CHANGELOG_URL));
		final SortedSet<ChangelogEntry> changelog = new TreeSet<>();

		boolean newVersionFound = false;
		int index = 0;
		String currentVersion = "";

		for (Element element : fullChangelog.children()) {
			if ("h1".equals(element.tagName())) {
				currentVersion = element.text();

				final int currentVersionInt = getVersion(currentVersion);

				if (newVersionFound) {
					if (currentVersionInt <= oldVersion) {
						break;
					}
				} else if (currentVersionInt <= newVersion) {
					newVersionFound = true;
				}

				continue;
			}

			if (newVersionFound && "ul".equals(element.tagName())) {
				changelog.add(new ChangelogEntry(
						index++, "Actually Additions " + currentVersion, element
				));
			}
		}

		return changelog;
	}

	private static int getVersion(CurseFile file) {
		return getVersion(
				file.displayName().replaceAll("ActuallyAdditions-([\\d.]+-r\\d+).*", "$1")
		);
	}

	private static int getVersion(String version) {
		return Integer.parseInt(version.split("-")[1].substring(1));
	}
}
