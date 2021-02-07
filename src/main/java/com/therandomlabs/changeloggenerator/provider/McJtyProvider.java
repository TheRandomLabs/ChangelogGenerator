/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019-2021 TheRandomLabs
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

package com.therandomlabs.changeloggenerator.provider;

import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.file.BasicCurseFile;
import com.therandomlabs.curseapi.file.CurseFileChange;
import com.therandomlabs.curseapi.project.CurseProject;
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
		final CurseProject project = fileChange.project();

		if (project == null) {
			return changelog;
		}

		if (!"McJty".equals(project.author().name())) {
			return changelog;
		}

		//McJty puts a heading in each of his changelogs with the file version.
		changelog.textNodes().get(0).remove();

		return changelog;
	}
}
