// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2014

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.HTML_Program.HTML_Element;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Caption extends TokenSequence
{
	public @S(10) @INDENT HTML_Punctuation startTag = new HTML_Punctuation('<');
	public @S(20) @NOSPACE HTML_Keyword CAPTION = new HTML_Keyword("caption");
	public @S(30) @OPT TokenList<HTML_Attribute> attributes; 
	public @S(40) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	public @S(50) @OPT TokenList<HTML_Element> elements;
	public @S(60) @OUTDENT HTML_EndCaption endCaption;

	public static class HTML_EndCaption extends TokenSequence
	{
		public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @S(20) @NOSPACE HTML_Keyword CAPTION = new HTML_Keyword("caption");
		public @S(30) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
}
