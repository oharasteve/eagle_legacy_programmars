// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 7, 2022

namespace com.eagle.programmar.XML
{
	using HTML_TagElement = com.eagle.programmar.HTML.HTML_Tag.HTML_TagElement;
	using HTML_KeywordChoice = com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class XML_Header : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class XML_HeaderQuestionMark extends com.eagle.tokens.TokenSequence
		public class XML_HeaderQuestionMark : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("<?");
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_KeywordChoice XMLISH = new com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice("mso-application", "rfc", "test", "test-style", "xml", "xml-stylesheet");
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.HTML.HTML_Tag.HTML_TagElement> attributes;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_Punctuation question2 = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("?>");
			public  NOSPACE;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class XML_HeaderPercent extends com.eagle.tokens.TokenSequence
		public class XML_HeaderPercent : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("<%@");
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.HTML.HTML_Tag.HTML_TagElement> attributes;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE HTML_Punctuation question2 = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("%>");
			public  NOSPACE;
		}
	}

}
