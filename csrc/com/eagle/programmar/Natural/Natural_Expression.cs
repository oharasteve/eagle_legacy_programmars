// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

namespace com.eagle.programmar.Natural
{
	using Natural_AdditiveExpression = com.eagle.programmar.Natural.Expressions.Natural_AdditiveExpression;
	using Natural_Function_Call = com.eagle.programmar.Natural.Expressions.Natural_Function_Call;
	using Natural_LiteralExpression = com.eagle.programmar.Natural.Expressions.Natural_LiteralExpression;
	using Natural_MultiplicativeExpression = com.eagle.programmar.Natural.Expressions.Natural_MultiplicativeExpression;
	using Natural_NegativeExpression = com.eagle.programmar.Natural.Expressions.Natural_NegativeExpression;
	using Natural_ParenthesizedExpression = com.eagle.programmar.Natural.Expressions.Natural_ParenthesizedExpression;
	using Natural_System_Variable = com.eagle.programmar.Natural.Expressions.Natural_System_Variable;
	using Natural_VariableExpression = com.eagle.programmar.Natural.Expressions.Natural_VariableExpression;
	using Natural_Number = com.eagle.programmar.Natural.Terminals.Natural_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class Natural_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public Natural_Expression() : base(_operators)
		{
			setOperators(_operators);
		}

		public Natural_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Natural.Terminals.Natural_Number number;
		public Natural_Number number;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Natural.Expressions.Natural_LiteralExpression literalExpression;
		public Natural_LiteralExpression literalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Natural.Expressions.Natural_System_Variable system_Variable;
		public Natural_System_Variable system_Variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Natural.Expressions.Natural_NegativeExpression negativeExpression;
		public Natural_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Natural.Expressions.Natural_VariableExpression variableExpression;
		public Natural_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Natural.Expressions.Natural_Function_Call function_Call;
		public Natural_Function_Call function_Call;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Natural.Expressions.Natural_ParenthesizedExpression parenthesizedExpression;
		public Natural_ParenthesizedExpression parenthesizedExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Natural.Expressions.Natural_MultiplicativeExpression multiplicativeExpression;
		public Natural_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Natural.Expressions.Natural_AdditiveExpression additiveExpression;
		public Natural_AdditiveExpression additiveExpression;
	}
}
