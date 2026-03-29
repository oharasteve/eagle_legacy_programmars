// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Natural.Expressions
{
	using Natural_Expression = com.eagle.programmar.Natural.Natural_Expression;
	using Natural_PunctuationChoice = com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Natural_NegativeExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice plusMinus = new com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice("+", "-");
		public Natural_PunctuationChoice plusMinus = new Natural_PunctuationChoice("+", "-");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Natural_Expression expr;
		public Natural_Expression expr;
	}

}
