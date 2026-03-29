// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

namespace com.eagle.programmar.HTML
{
	using CSS_Program = com.eagle.programmar.CSS.CSS_Program;
	using CSS_Syntax = com.eagle.programmar.CSS.CSS_Syntax;
	using HTML_CData = com.eagle.programmar.HTML.Terminals.HTML_CData;
	using HTML_Keyword = com.eagle.programmar.HTML.Terminals.HTML_Keyword;
	using HTML_Literal = com.eagle.programmar.HTML.Terminals.HTML_Literal;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class HTML_Style : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT HTML_StartStyle startStyle;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) HTML_StyleBody body;
		public HTML_StyleBody body;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OUTDENT HTML_EndStyle endStyle;
		public  OUTDENT;

		public class HTML_StyleBody : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST @SYNTAX(com.eagle.programmar.CSS.CSS_Syntax.class) com.eagle.programmar.CSS.CSS_Program XXcss;
			public @SYNTAX(typeof(CSS_Syntax)) CSS_Program XXcss;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_CData XXcdata;
			public HTML_CData XXcdata;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_StyleInclude extends com.eagle.tokens.TokenSequence
			public static class HTML_StyleInclude extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation leftBrace = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("{%");
				public HTML_Punctuation leftBrace = new HTML_Punctuation("{%");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.HTML.Terminals.HTML_Keyword INCLUDE = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("include");
				public HTML_Keyword INCLUDE = new HTML_Keyword("include");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.HTML.Terminals.HTML_Literal fileName;
				public HTML_Literal fileName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.HTML.Terminals.HTML_Punctuation percent2 = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("%}");
				public HTML_Punctuation percent2 = new HTML_Punctuation("%}");
			}
		}

		public static class HTML_StartStyle extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("<");
			public HTML_Punctuation startTag = new HTML_Punctuation("<");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE @DOC("html_styles.asp") com.eagle.programmar.HTML.Terminals.HTML_Keyword STYLE = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("style");
			public @NOSPACE HTML_Keyword STYLE = new HTML_Keyword("style");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<HTML_Attribute> attributes;
			public @OPT TokenList<HTML_Attribute> attributes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_Punctuation endTag1 = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
			public @NOSPACE HTML_Punctuation endTag1 = new HTML_Punctuation('>');
		}

		public static class HTML_EndStyle extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("</");
			public HTML_Punctuation startTag = new HTML_Punctuation("</");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword STYLE = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("style");
			public @NOSPACE HTML_Keyword STYLE = new HTML_Keyword("style");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}
	}

}
