// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

namespace com.eagle.programmar.HTML
{
	using Django_Control = com.eagle.programmar.Django.Django_Control;
	using Django_Syntax = com.eagle.programmar.Django.Django_Syntax;
	using HTML_Keyword = com.eagle.programmar.HTML.Terminals.HTML_Keyword;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using Javascript_Program = com.eagle.programmar.Javascript.Javascript_Program;
	using Javascript_Syntax = com.eagle.programmar.Javascript.Javascript_Syntax;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class HTML_Script : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('<');
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("html_scripts.asp") com.eagle.programmar.HTML.Terminals.HTML_Keyword SCRIPT = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("script");
		public @DOC("html_scripts.asp") HTML_Keyword SCRIPT = new HTML_Keyword("script");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<HTML_Attribute> attributes;
		public @OPT TokenList<HTML_Attribute> attributes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) HTML_ScriptContents contents;
		public HTML_ScriptContents contents;

		public static class HTML_ScriptContents extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Punctuation XXendTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("/>");
			public HTML_Punctuation XXendTag = new HTML_Punctuation("/>");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_ScriptWithBody extends com.eagle.tokens.TokenSequence
			public static class HTML_ScriptWithBody extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
				public HTML_Punctuation endTag = new HTML_Punctuation('>');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) HTML_ScriptBody body;
				public HTML_ScriptBody body;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NEWLINE HTML_EndScript endScript;
				public @NEWLINE HTML_EndScript endScript;

				public static class HTML_ScriptBody extends TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.Django.Django_Syntax.class) @OPT Django_Control XXdjango;
					public @SYNTAX(typeof(Django_Syntax)) Django_Control XXdjango;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.Javascript.Javascript_Syntax.class) @OPT Javascript_Program XXjavascript;
					public @SYNTAX(typeof(Javascript_Syntax)) Javascript_Program XXjavascript;
				}

				public static class HTML_EndScript extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("</");
					public HTML_Punctuation startTag = new HTML_Punctuation("</");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.HTML.Terminals.HTML_Keyword SCRIPT = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("script");
					public HTML_Keyword SCRIPT = new HTML_Keyword("script");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.HTML.Terminals.HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
					public HTML_Punctuation endTag = new HTML_Punctuation('>');
				}
			}
		}
	}

}
