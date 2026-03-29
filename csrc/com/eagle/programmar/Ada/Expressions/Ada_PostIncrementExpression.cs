// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Ada.Expressions
{
	using Ada_Variable = com.eagle.programmar.Ada.Ada_Variable;
	using Ada_PunctuationChoice = com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Ada_PostIncrementExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Ada_Variable var;
		public Ada_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice postIncrementOperator = new com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice("++", "--");
		public Ada_PunctuationChoice postIncrementOperator = new Ada_PunctuationChoice("++", "--");
	}

}
