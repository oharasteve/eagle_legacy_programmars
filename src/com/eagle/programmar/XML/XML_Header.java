// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 7, 2022

package com.eagle.programmar.XML;

import com.eagle.programmar.HTML.HTML_Tag.HTML_TagElement;
import com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class XML_Header extends TokenChooser
{
	public @CHOICE static class XML_HeaderQuestionMark extends TokenSequence
	{
		public @S(10) @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation("<?");
		public @S(20) @NOSPACE HTML_KeywordChoice XMLISH = new HTML_KeywordChoice(
				"mso-application",
				"rfc",
				"test",
				"test-style",
				"xml",
				"xml-stylesheet");
		public @S(30) @OPT TokenList<HTML_TagElement> attributes; 
		public @S(40) @NOSPACE HTML_Punctuation question2 = new HTML_Punctuation("?>");
	}

	public @CHOICE static class XML_HeaderPercent extends TokenSequence
	{
		public @S(10) @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation("<%@");
		public @S(20) @OPT TokenList<HTML_TagElement> attributes; 
		public @S(30) @NOSPACE HTML_Punctuation question2 = new HTML_Punctuation("%>");
	}
}
