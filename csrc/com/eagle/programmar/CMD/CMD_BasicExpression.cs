// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 4, 2024

namespace com.eagle.programmar.CMD
{
	using CMD_BangExpression = com.eagle.programmar.CMD.Expressions.CMD_BangExpression;
	using CMD_NegativeExpression = com.eagle.programmar.CMD.Expressions.CMD_NegativeExpression;
	using CMD_ParenthesizedExpression = com.eagle.programmar.CMD.Expressions.CMD_ParenthesizedExpression;
	using CMD_PercentExpression = com.eagle.programmar.CMD.Expressions.CMD_PercentExpression;
	using CMD_VariableExpression = com.eagle.programmar.CMD.Expressions.CMD_VariableExpression;
	using CMD_Filename = com.eagle.programmar.CMD.Terminals.CMD_Filename;
	using CMD_Literal = com.eagle.programmar.CMD.Terminals.CMD_Literal;
	using CMD_Number = com.eagle.programmar.CMD.Terminals.CMD_Number;
	using CMD_PctPctVariable = com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class CMD_BasicExpression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public CMD_BasicExpression() : base(_operators)
		{
		}

		public CMD_BasicExpression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals -- Identical to CMD_Expression

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.CMD.Terminals.CMD_Number number;
		public CMD_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.CMD.Terminals.CMD_Literal literal;
		public CMD_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable pctVariable;
		public CMD_PctPctVariable pctVariable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(40) com.eagle.programmar.CMD.Terminals.CMD_Filename filename;
		public CMD_Filename filename;

		///////////////////////////////////////////////
		// Primary expressions -- Subset from CMD_Expression

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.CMD.Expressions.CMD_ParenthesizedExpression parensExpression;
		public CMD_ParenthesizedExpression parensExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.CMD.Expressions.CMD_NegativeExpression negativeExpression;
		public CMD_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.CMD.Expressions.CMD_VariableExpression variableExpression;
		public CMD_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.CMD.Expressions.CMD_PercentExpression percentExpression;
		public CMD_PercentExpression percentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.CMD.Expressions.CMD_BangExpression bangExpression;
		public CMD_BangExpression bangExpression;

		///////////////////////////////////////////////
		// Binary expressions -- only in CMD_Expression
	}

}
