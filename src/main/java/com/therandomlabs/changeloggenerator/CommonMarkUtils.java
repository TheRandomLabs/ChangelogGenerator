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

package com.therandomlabs.changeloggenerator;

import com.google.common.base.Preconditions;
import com.therandomlabs.curseapi.util.JsoupUtils;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.nodes.Element;

/**
 * Contains utility methods for working with Markdown.
 */
public final class CommonMarkUtils {
	private static final Parser parser = Parser.builder().build();
	private static final HtmlRenderer renderer = HtmlRenderer.builder().build();

	private CommonMarkUtils() {}

	/**
	 * Parses the specified Markdown string as an HTML {@link Element}.
	 *
	 * @param markdown a Markdown string.
	 * @return an {@link Element} parsed from the specified Markdown string.
	 */
	public static Element parseElement(String markdown) {
		Preconditions.checkNotNull(markdown, "markdown should not be null");
		final Element element = JsoupUtils.parseBody(renderer.render(parser.parse(markdown)));
		Preconditions.checkNotNull(element, "element should not be null");
		return element;
	}
}
