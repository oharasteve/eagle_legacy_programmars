// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Ruby.Expressions
{
	using Ruby_Variable = com.eagle.programmar.Ruby.Ruby_Variable;
	using Ruby_PunctuationChoice = com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Ruby_PostIncrementExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Ruby_Variable var;
		public Ruby_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice postIncrementOperator = new com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice("++", "--");
		public Ruby_PunctuationChoice postIncrementOperator = new Ruby_PunctuationChoice("++", "--");
	}

}
