// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2022

namespace com.eagle.programmar.HTML
{
	using HTML_Element = com.eagle.programmar.HTML.HTML_Program.HTML_Element;
	using HTML_Keyword = com.eagle.programmar.HTML.Terminals.HTML_Keyword;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class HTML_Span : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('<');
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword SPAN = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("span");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<HTML_Attribute> attributes;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<com.eagle.programmar.HTML.HTML_Program.HTML_Element> elements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OUTDENT HTML_EndSpan endSpan;
		public  OUTDENT;

		public class HTML_EndSpan : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("</");
			public HTML_Punctuation startTag = new HTML_Punctuation("</");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword SPAN = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("span");
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
			public  NOSPACE;
		}
	}

}
