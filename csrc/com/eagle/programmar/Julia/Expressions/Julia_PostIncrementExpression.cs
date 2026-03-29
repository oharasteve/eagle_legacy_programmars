// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Julia.Expressions
{
	using Julia_Variable = com.eagle.programmar.Julia.Julia_Variable;
	using Julia_PunctuationChoice = com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Julia_PostIncrementExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Julia.Julia_Variable var;
		public Julia_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice postIncrementOperator = new com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice("++", "--");
		public Julia_PunctuationChoice postIncrementOperator = new Julia_PunctuationChoice("++", "--");
	}

}
