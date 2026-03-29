// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

namespace com.eagle.programmar.CSS.Qualifiers
{
	using CSS_Keyword = com.eagle.programmar.CSS.Terminals.CSS_Keyword;
	using CSS_Literal = com.eagle.programmar.CSS.Terminals.CSS_Literal;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class CSS_QualifierRow : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Keyword ROW = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("row$");
		public CSS_Keyword ROW = new CSS_Keyword("row$");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSS.Terminals.CSS_Literal literal;
		public CSS_Literal literal;
	}
}
