package com.therandomlabs.changeloggenerator.provider;

import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import org.jsoup.nodes.Element;

/**
 * An implementation of {@link ChangelogProvider} for McJty's mods.
 */
public final class McJtyProvider implements ChangelogProvider {
	/**
	 * The singleton instance of {@link McJtyProvider}.
	 */
	public static final McJtyProvider instance = new McJtyProvider();

	private McJtyProvider() {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Element processChangelog(
			CurseFileChange<? extends BasicCurseFile> fileChange, Element changelog
	) throws CurseException {
		if (!"McJty".equals(fileChange.project().author().name())) {
			return changelog;
		}

		//McJty puts a heading in each of his changelogs with the file version.
		changelog.textNodes().get(0).remove();

		return changelog;
	}
}
