// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic
{
	using Basic_AdditiveExpression = com.eagle.programmar.Basic.Expressions.Basic_AdditiveExpression;
	using Basic_ExponentExpression = com.eagle.programmar.Basic.Expressions.Basic_ExponentExpression;
	using Basic_MultiplicativeExpression = com.eagle.programmar.Basic.Expressions.Basic_MultiplicativeExpression;
	using Basic_NegativeExpression = com.eagle.programmar.Basic.Expressions.Basic_NegativeExpression;
	using Basic_ParenthesizedExpression = com.eagle.programmar.Basic.Expressions.Basic_ParenthesizedExpression;
	using Basic_RelationalExpression = com.eagle.programmar.Basic.Expressions.Basic_RelationalExpression;
	using Basic_SubscriptExpression = com.eagle.programmar.Basic.Expressions.Basic_SubscriptExpression;
	using Basic_VariableExpression = com.eagle.programmar.Basic.Expressions.Basic_VariableExpression;
	using Basic_AbsFunction = com.eagle.programmar.Basic.Functions.Basic_AbsFunction;
	using Basic_ChrFunction = com.eagle.programmar.Basic.Functions.Basic_ChrFunction;
	using Basic_IntFunction = com.eagle.programmar.Basic.Functions.Basic_IntFunction;
	using Basic_RndFunction = com.eagle.programmar.Basic.Functions.Basic_RndFunction;
	using Basic_TabFunction = com.eagle.programmar.Basic.Functions.Basic_TabFunction;
	using Basic_TrigFunction = com.eagle.programmar.Basic.Functions.Basic_TrigFunction;
	using Basic_Literal = com.eagle.programmar.Basic.Terminals.Basic_Literal;
	using Basic_Number = com.eagle.programmar.Basic.Terminals.Basic_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Basic_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Basic_Expression() : base(_operators)
		{
		}

		public Basic_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order.
		// The # determines operator precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Basic.Terminals.Basic_Number number;
		public Basic_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Basic.Terminals.Basic_Literal literal;
		public Basic_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Basic.Expressions.Basic_NegativeExpression negativeExpression;
		public Basic_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Basic.Expressions.Basic_ParenthesizedExpression parenthesizedExpression;
		public Basic_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Basic.Functions.Basic_AbsFunction absFunction;
		public Basic_AbsFunction absFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Basic.Functions.Basic_ChrFunction chrFunction;
		public Basic_ChrFunction chrFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Basic.Functions.Basic_IntFunction intFunction;
		public Basic_IntFunction intFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Basic.Functions.Basic_RndFunction rndFunction;
		public Basic_RndFunction rndFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Basic.Functions.Basic_TabFunction tabFunction;
		public Basic_TabFunction tabFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Basic.Functions.Basic_TrigFunction trigFunction;
		public Basic_TrigFunction trigFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Basic.Expressions.Basic_VariableExpression variableExpression;
		public Basic_VariableExpression variableExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Basic.Expressions.Basic_SubscriptExpression subscriptExpression;
		public Basic_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Basic.Expressions.Basic_ExponentExpression exponentExpression;
		public Basic_ExponentExpression exponentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Basic.Expressions.Basic_MultiplicativeExpression multiplicativeExpression;
		public Basic_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Basic.Expressions.Basic_AdditiveExpression additiveExpression;
		public Basic_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Basic.Expressions.Basic_RelationalExpression relationalExpression;
		public Basic_RelationalExpression relationalExpression;
	}

}
