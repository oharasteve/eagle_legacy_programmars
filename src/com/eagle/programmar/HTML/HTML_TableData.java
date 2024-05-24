// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, AUg 13, 2022

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.HTML_Program.HTML_Element;
import com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_TableData extends TokenSequence
{
	public @S(10) @INDENT HTML_StartData startCell;
	public @S(20) @OPT TokenList<HTML_Element> contents;
	public @S(30) @OPT @OUTDENT HTML_EndData endData;

	public static class HTML_StartData extends TokenSequence
	{
		public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("<");
		public @S(20) @NOSPACE HTML_KeywordChoice TD = new HTML_KeywordChoice("td", "th");
		public @S(30) @OPT TokenList<HTML_Attribute> attributes;
		public @S(40) @NOSPACE HTML_EndStartDate endStart;

		public static class HTML_EndStartDate extends TokenChooser
		{
			// Not really a PunctuationChoice here, BUT the EagleWriteXML module insists on
			// difference class names
			public @CHOICE @CURIOUS("Bogus slash in element") HTML_PunctuationChoice slash = new HTML_PunctuationChoice(
					"/>");
			public @CHOICE HTML_Punctuation endTag = new HTML_Punctuation(">");
		}
	}

	public static class HTML_EndData extends TokenSequence
	{
		public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @S(20) @NOSPACE HTML_KeywordChoice TD = new HTML_KeywordChoice("td", "th");
		public @S(30) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
}
