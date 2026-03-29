// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

namespace com.eagle.programmar.Eaglish
{
	using Eaglish_AdditiveExpression = com.eagle.programmar.Eaglish.Expressions.Eaglish_AdditiveExpression;
	using Eaglish_BuiltInExpression = com.eagle.programmar.Eaglish.Expressions.Eaglish_BuiltInExpression;
	using Eaglish_ConditionStringMatch = com.eagle.programmar.Eaglish.Expressions.Eaglish_ConditionStringMatch;
	using Eaglish_FunctionCall = com.eagle.programmar.Eaglish.Expressions.Eaglish_FunctionCall;
	using Eaglish_LogicalAndExpression = com.eagle.programmar.Eaglish.Expressions.Eaglish_LogicalAndExpression;
	using Eaglish_LogicalNotExpresion = com.eagle.programmar.Eaglish.Expressions.Eaglish_LogicalNotExpresion;
	using Eaglish_LogicalOrExpression = com.eagle.programmar.Eaglish.Expressions.Eaglish_LogicalOrExpression;
	using Eaglish_MultiplicativeExpression = com.eagle.programmar.Eaglish.Expressions.Eaglish_MultiplicativeExpression;
	using Eaglish_NegativeExpression = com.eagle.programmar.Eaglish.Expressions.Eaglish_NegativeExpression;
	using Eaglish_ParenthesizedExpression = com.eagle.programmar.Eaglish.Expressions.Eaglish_ParenthesizedExpression;
	using Eaglish_RelationalExpression = com.eagle.programmar.Eaglish.Expressions.Eaglish_RelationalExpression;
	using Eaglish_SubscriptExpression = com.eagle.programmar.Eaglish.Expressions.Eaglish_SubscriptExpression;
	using Eaglish_VariableExpression = com.eagle.programmar.Eaglish.Expressions.Eaglish_VariableExpression;
	using Eaglish_LengthFunction = com.eagle.programmar.Eaglish.Functions.Eaglish_LengthFunction;
	using Eaglish_Literal = com.eagle.programmar.Eaglish.Terminals.Eaglish_Literal;
	using Eaglish_Number = com.eagle.programmar.Eaglish.Terminals.Eaglish_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Eaglish_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Eaglish_Expression() : base(_operators)
		{
		}

		public Eaglish_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Eaglish.Terminals.Eaglish_Number number;
		public Eaglish_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Eaglish.Terminals.Eaglish_Literal literal;
		public Eaglish_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Eaglish.Functions.Eaglish_LengthFunction lengthFunction;
		public Eaglish_LengthFunction lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Eaglish.Expressions.Eaglish_FunctionCall funcCall;
		public Eaglish_FunctionCall funcCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Eaglish.Expressions.Eaglish_NegativeExpression negativeExpr;
		public Eaglish_NegativeExpression negativeExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Eaglish.Expressions.Eaglish_LogicalNotExpresion notExpr;
		public Eaglish_LogicalNotExpresion notExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Eaglish.Expressions.Eaglish_ParenthesizedExpression parenExpr;
		public Eaglish_ParenthesizedExpression parenExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Eaglish.Expressions.Eaglish_BuiltInExpression builtinExpr;
		public Eaglish_BuiltInExpression builtinExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Eaglish.Expressions.Eaglish_VariableExpression varExpr;
		public Eaglish_VariableExpression varExpr;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Eaglish.Expressions.Eaglish_SubscriptExpression subscrExpr;
		public Eaglish_SubscriptExpression subscrExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Eaglish.Expressions.Eaglish_MultiplicativeExpression multExpr;
		public Eaglish_MultiplicativeExpression multExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Eaglish.Expressions.Eaglish_AdditiveExpression addExpr;
		public Eaglish_AdditiveExpression addExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Eaglish.Expressions.Eaglish_RelationalExpression relExpr;
		public Eaglish_RelationalExpression relExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Eaglish.Expressions.Eaglish_ConditionStringMatch matchExpr;
		public Eaglish_ConditionStringMatch matchExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Eaglish.Expressions.Eaglish_LogicalAndExpression andExpr;
		public Eaglish_LogicalAndExpression andExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Eaglish.Expressions.Eaglish_LogicalOrExpression orExpr;
		public Eaglish_LogicalOrExpression orExpr;
	}
}
