package com.therandomlabs.changeloggenerator;

import com.therandomlabs.curseapi.util.JsoupUtils;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.jsoup.nodes.Element;

public final class CommonMarkUtils {
	private static final Parser parser = Parser.builder().build();
	private static final HtmlRenderer renderer = HtmlRenderer.builder().build();

	private CommonMarkUtils() {}

	public static Element parseElement(String markdown) {
		return JsoupUtils.parseBody(renderer.render(parser.parse(markdown)));
	}
}
