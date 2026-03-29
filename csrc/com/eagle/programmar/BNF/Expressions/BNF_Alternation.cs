// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2025

namespace com.eagle.programmar.BNF.Expressions
{
	using BNF_ExpressionTerm = com.eagle.programmar.BNF.BNF_Expression.BNF_ExpressionTerm;
	using BNF_Punctuation = com.eagle.programmar.BNF.Terminals.BNF_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class BNF_Alternation : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.BNF.Terminals.BNF_Punctuation VerticalBar = new com.eagle.programmar.BNF.Terminals.BNF_Punctuation('|');
		public BNF_Punctuation VerticalBar = new BNF_Punctuation('|');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.BNF.BNF_Expression.BNF_ExpressionTerm> terms;
		public TokenList<BNF_ExpressionTerm> terms;
	}
}
