package com.therandomlabs.changeloggenerator;

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
		return JsoupUtils.parseBody(renderer.render(parser.parse(markdown)));
	}
}
