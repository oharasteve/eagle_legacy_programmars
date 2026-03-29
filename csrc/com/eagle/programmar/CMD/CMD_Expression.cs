// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

namespace com.eagle.programmar.CMD
{
	using CMD_AdditiveExpression = com.eagle.programmar.CMD.Expressions.CMD_AdditiveExpression;
	using CMD_BangExpression = com.eagle.programmar.CMD.Expressions.CMD_BangExpression;
	using CMD_DefinedExpression = com.eagle.programmar.CMD.Expressions.CMD_DefinedExpression;
	using CMD_EqualityExpression = com.eagle.programmar.CMD.Expressions.CMD_EqualityExpression;
	using CMD_MultiplicativeExpression = com.eagle.programmar.CMD.Expressions.CMD_MultiplicativeExpression;
	using CMD_NegativeExpression = com.eagle.programmar.CMD.Expressions.CMD_NegativeExpression;
	using CMD_NotExpression = com.eagle.programmar.CMD.Expressions.CMD_NotExpression;
	using CMD_ParenthesizedExpression = com.eagle.programmar.CMD.Expressions.CMD_ParenthesizedExpression;
	using CMD_PercentExpression = com.eagle.programmar.CMD.Expressions.CMD_PercentExpression;
	using CMD_RelationalExpression = com.eagle.programmar.CMD.Expressions.CMD_RelationalExpression;
	using CMD_VariableExpression = com.eagle.programmar.CMD.Expressions.CMD_VariableExpression;
	using CMD_Filename = com.eagle.programmar.CMD.Terminals.CMD_Filename;
	using CMD_Literal = com.eagle.programmar.CMD.Terminals.CMD_Literal;
	using CMD_Number = com.eagle.programmar.CMD.Terminals.CMD_Number;
	using CMD_PctPctVariable = com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class CMD_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public CMD_Expression() : base(_operators)
		{
		}

		public CMD_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals -- Identical to CMD_BasicExpression

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
		// Primary expressions -- Identical to CMD_BasicExpression

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.CMD.Expressions.CMD_ParenthesizedExpression parensExpression;
		public CMD_ParenthesizedExpression parensExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.CMD.Expressions.CMD_NegativeExpression negativeExpression;
		public CMD_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.CMD.Expressions.CMD_NotExpression notExpression;
		public CMD_NotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.CMD.Expressions.CMD_DefinedExpression definedExpression;
		public CMD_DefinedExpression definedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.CMD.Expressions.CMD_VariableExpression variableExpression;
		public CMD_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.CMD.Expressions.CMD_PercentExpression percentExpression;
		public CMD_PercentExpression percentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.CMD.Expressions.CMD_BangExpression bangExpression;
		public CMD_BangExpression bangExpression;

		///////////////////////////////////////////////
		// Binary expressions -- Missing in CMD_BasicExpression

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.CMD.Expressions.CMD_MultiplicativeExpression multiplicativeExpression;
		public CMD_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.CMD.Expressions.CMD_AdditiveExpression additiveExpression;
		public CMD_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.CMD.Expressions.CMD_RelationalExpression relationalExpression;
		public CMD_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.CMD.Expressions.CMD_EqualityExpression equalityExpression;
		public CMD_EqualityExpression equalityExpression;
	}

}
