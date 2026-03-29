// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.RPGFree.Expressions
{
	using RPGFree_Expression = com.eagle.programmar.RPGFree.RPGFree_Expression;
	using RPGFree_PunctuationChoice = com.eagle.programmar.RPGFree.Terminals.RPGFree_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class RPGFree_Additive : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPGFree.RPGFree_Expression left = new com.eagle.programmar.RPGFree.RPGFree_Expression(this, AllowedPrecedence.ATLEAST);
		public RPGFree_Expression left = new RPGFree_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPGFree.Terminals.RPGFree_PunctuationChoice operator = new com.eagle.programmar.RPGFree.Terminals.RPGFree_PunctuationChoice("+", "-");
		public RPGFree_PunctuationChoice @operator = new RPGFree_PunctuationChoice("+", "-");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPGFree.RPGFree_Expression right = new com.eagle.programmar.RPGFree.RPGFree_Expression(this, AllowedPrecedence.HIGHER);
		public RPGFree_Expression right = new RPGFree_Expression(this, AllowedPrecedence.HIGHER);
	}

}
