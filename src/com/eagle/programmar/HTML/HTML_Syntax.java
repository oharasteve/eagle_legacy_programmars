// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.HTML;

import com.eagle.core.EagleSyntax;

public class HTML_Syntax extends EagleSyntax
{
	public static final String[] PUNCT = new String[] {
			"<!", "<!--", "</", "/>", "{%", "%}", "<?", "?>", "<%@", "%>",
	};

	public static final boolean IS_CASE_SENSITIVE = false;

	@Override
	public String syntaxId()
	{
		return "HTML";
	}

	public HTML_Syntax()
	{
		_isCaseSensitive = IS_CASE_SENSITIVE;
		_continuationChar = null;
		_extraCharacters = "";
		_punctuationExceptions = PUNCT;

		addReservedWords(RESERVED_WORDS);
	}

	private static final String[] RESERVED_WORDS = new String[] {
			"a", "caption", "pre", "script", "span", "style", "table", "td", "tr"
	};
}
