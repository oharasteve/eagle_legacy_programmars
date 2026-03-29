// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.RPGFree.Functions
{
	using RPGFree_Expression = com.eagle.programmar.RPGFree.RPGFree_Expression;
	using RPGFree_Keyword = com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword;
	using RPGFree_Punctuation = com.eagle.programmar.RPGFree.Terminals.RPGFree_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class RPGFree_CharFunction : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPGFree.Terminals.RPGFree_Punctuation percent = new com.eagle.programmar.RPGFree.Terminals.RPGFree_Punctuation("%");
		public RPGFree_Punctuation percent = new RPGFree_Punctuation("%");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword CHAR = new com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword("char");
		public RPGFree_Keyword CHAR = new RPGFree_Keyword("char");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.RPGFree.RPGFree_Expression expr;
		public RPGFree_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
	}

}
