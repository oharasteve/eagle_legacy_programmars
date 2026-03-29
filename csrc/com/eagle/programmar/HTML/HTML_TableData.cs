// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, AUg 13, 2022

namespace com.eagle.programmar.HTML
{
	using HTML_Element = com.eagle.programmar.HTML.HTML_Program.HTML_Element;
	using HTML_KeywordChoice = com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using HTML_PunctuationChoice = com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class HTML_TableData : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT HTML_StartData startCell;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.HTML.HTML_Program.HTML_Element> contents;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @OUTDENT HTML_EndData endData;
		public  OPT;

		public class HTML_StartData : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("<");
			public HTML_Punctuation startTag = new HTML_Punctuation("<");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_KeywordChoice TD = new com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice("td", "th");
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<HTML_Attribute> attributes;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_EndStartDate endStart;
			public  NOSPACE;

			public class HTML_EndStartDate : TokenChooser
			{
				// Not really a PunctuationChoice here, BUT the EagleWriteXML module insists on
				// difference class names
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @CURIOUS("Bogus slash in element") com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice XXslash = new com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice("/>");
				public @CURIOUS("Bogus slash in element") HTML_PunctuationChoice XXslash = new HTML_PunctuationChoice("/>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Punctuation XXendTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation(">");
				public HTML_Punctuation XXendTag = new HTML_Punctuation(">");
			}
		}

		public static class HTML_EndData extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("</");
			public HTML_Punctuation startTag = new HTML_Punctuation("</");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_KeywordChoice TD = new com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice("td", "th");
			public @NOSPACE HTML_KeywordChoice TD = new HTML_KeywordChoice("td", "th");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}
	}

}
