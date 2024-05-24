// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.Django.Django_Control;
import com.eagle.programmar.Django.Django_Syntax;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.Javascript.Javascript_Program;
import com.eagle.programmar.Javascript.Javascript_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Script extends TokenSequence
{
	public @S(10) @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation('<');
	public @S(20) @DOC("html_scripts.asp") HTML_Keyword SCRIPT = new HTML_Keyword("script");
	public @S(30) @OPT TokenList<HTML_Attribute> attributes;
	public @S(40) HTML_ScriptContents contents;

	public static class HTML_ScriptContents extends TokenChooser
	{
		public @CHOICE HTML_Punctuation endTag = new HTML_Punctuation("/>");

		public @CHOICE static class HTML_ScriptWithBody extends TokenSequence
		{
			public @S(10) HTML_Punctuation endTag = new HTML_Punctuation('>');
			public @S(20) HTML_ScriptBody body;
			public @S(30) @NEWLINE HTML_EndScript endScript;

			public static class HTML_ScriptBody extends TokenChooser
			{
				public @CHOICE @SYNTAX(Django_Syntax.class) @OPT Django_Control django;
				public @CHOICE @SYNTAX(Javascript_Syntax.class) @OPT Javascript_Program javascript;
			}

			public static class HTML_EndScript extends TokenSequence
			{
				public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("</");
				public @S(20) HTML_Keyword SCRIPT = new HTML_Keyword("script");
				public @S(30) HTML_Punctuation endTag = new HTML_Punctuation('>');
			}
		}
	}
}
