// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

namespace com.eagle.programmar.HTML
{
	using HTML_Identifier = com.eagle.programmar.HTML.Terminals.HTML_Identifier;
	using HTML_Keyword = com.eagle.programmar.HTML.Terminals.HTML_Keyword;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class HTML_IfCondition : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("<!");
		public HTML_Punctuation startTag = new HTML_Punctuation("<!");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.HTML.Terminals.HTML_Keyword IF = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("if");
		public HTML_Keyword IF = new HTML_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.HTML.Terminals.HTML_Identifier tag;
		public HTML_Identifier tag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.HTML.Terminals.HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
		public HTML_Punctuation endTag = new HTML_Punctuation('>');
	}

}
