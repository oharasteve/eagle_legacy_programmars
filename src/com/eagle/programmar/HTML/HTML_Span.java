// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2022

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.HTML_Program.HTML_Element;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Span extends TokenSequence
{
	public @S(10) @INDENT HTML_Punctuation startTag = new HTML_Punctuation('<');
	public @S(20) @NOSPACE HTML_Keyword SPAN = new HTML_Keyword("span");
	public @S(30) @OPT TokenList<HTML_Attribute> attributes;
	public @S(40) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	public @S(50) @OPT TokenList<HTML_Element> elements;
	public @S(60) @OUTDENT HTML_EndSpan endSpan;

	public static class HTML_EndSpan extends TokenSequence
	{
		public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @S(20) @NOSPACE HTML_Keyword SPAN = new HTML_Keyword("span");
		public @S(30) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
}
