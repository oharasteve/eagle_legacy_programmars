// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

namespace com.eagle.programmar.CSS.Directives
{
	using CSS_Body = com.eagle.programmar.CSS.CSS_Program.CSS_Body;
	using CSS_Keyword = com.eagle.programmar.CSS.Terminals.CSS_Keyword;
	using CSS_Literal = com.eagle.programmar.CSS.Terminals.CSS_Literal;
	using CSS_Punctuation = com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class CSS_AtMozDocument : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Punctuation at = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('@');
		public CSS_Punctuation at = new CSS_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSS.Terminals.CSS_Keyword MOZDOCUMENT = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("-moz-document");
		public CSS_Keyword MOZDOCUMENT = new CSS_Keyword("-moz-document");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSS.Terminals.CSS_Keyword URLPREFIX = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("url-prefix");
		public CSS_Keyword URLPREFIX = new CSS_Keyword("url-prefix");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CSS.Terminals.CSS_Literal literal;
		public CSS_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.TokenList<com.eagle.programmar.CSS.CSS_Program.CSS_Body> bodies;
		public TokenList<CSS_Body> bodies;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;
	}

}
