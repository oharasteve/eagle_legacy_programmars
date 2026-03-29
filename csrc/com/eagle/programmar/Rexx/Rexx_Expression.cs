// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx
{
	using Rexx_AdditiveExpression = com.eagle.programmar.Rexx.Expressions.Rexx_AdditiveExpression;
	using Rexx_BuiltIns = com.eagle.programmar.Rexx.Expressions.Rexx_BuiltIns;
	using Rexx_CommentExpression = com.eagle.programmar.Rexx.Expressions.Rexx_CommentExpression;
	using Rexx_ConcatExpression = com.eagle.programmar.Rexx.Expressions.Rexx_ConcatExpression;
	using Rexx_FunctionCall = com.eagle.programmar.Rexx.Expressions.Rexx_FunctionCall;
	using Rexx_LogicalAndExpression = com.eagle.programmar.Rexx.Expressions.Rexx_LogicalAndExpression;
	using Rexx_LogicalNotExpression = com.eagle.programmar.Rexx.Expressions.Rexx_LogicalNotExpression;
	using Rexx_LogicalOrExpression = com.eagle.programmar.Rexx.Expressions.Rexx_LogicalOrExpression;
	using Rexx_LogicalXorExpression = com.eagle.programmar.Rexx.Expressions.Rexx_LogicalXorExpression;
	using Rexx_MultiplicativeExpression = com.eagle.programmar.Rexx.Expressions.Rexx_MultiplicativeExpression;
	using Rexx_NegativeExpression = com.eagle.programmar.Rexx.Expressions.Rexx_NegativeExpression;
	using Rexx_ParenthesizedExpression = com.eagle.programmar.Rexx.Expressions.Rexx_ParenthesizedExpression;
	using Rexx_RelationalExpression = com.eagle.programmar.Rexx.Expressions.Rexx_RelationalExpression;
	using Rexx_SubscriptExpression = com.eagle.programmar.Rexx.Expressions.Rexx_SubscriptExpression;
	using Rexx_VariableExpression = com.eagle.programmar.Rexx.Expressions.Rexx_VariableExpression;
	using Rexx_LengthFunction = com.eagle.programmar.Rexx.Functions.Rexx_LengthFunction;
	using Rexx_SubstrFunction = com.eagle.programmar.Rexx.Functions.Rexx_SubstrFunction;
	using Rexx_Literal = com.eagle.programmar.Rexx.Terminals.Rexx_Literal;
	using Rexx_Number = com.eagle.programmar.Rexx.Terminals.Rexx_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Rexx_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Rexx_Expression() : base(_operators)
		{
		}

		public Rexx_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Rexx.Terminals.Rexx_Number number;
		public Rexx_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Rexx.Terminals.Rexx_Literal literal;
		public Rexx_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Rexx.Functions.Rexx_SubstrFunction substrFunction;
		public Rexx_SubstrFunction substrFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Rexx.Functions.Rexx_LengthFunction lengthFunction;
		public Rexx_LengthFunction lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Rexx.Expressions.Rexx_FunctionCall functionCall;
		public Rexx_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Rexx.Expressions.Rexx_NegativeExpression negativeExpression;
		public Rexx_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Rexx.Expressions.Rexx_LogicalNotExpression notExpression;
		public Rexx_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Rexx.Expressions.Rexx_BuiltIns builtIn;
		public Rexx_BuiltIns builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Rexx.Expressions.Rexx_VariableExpression variableExpression;
		public Rexx_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Rexx.Expressions.Rexx_ParenthesizedExpression parenthesizedExpression;
		public Rexx_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Rexx.Expressions.Rexx_CommentExpression commentExpression;
		public Rexx_CommentExpression commentExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Rexx.Expressions.Rexx_SubscriptExpression subscriptExpression;
		public Rexx_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Rexx.Expressions.Rexx_MultiplicativeExpression multiplicativeExpression;
		public Rexx_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Rexx.Expressions.Rexx_AdditiveExpression additiveExpression;
		public Rexx_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Rexx.Expressions.Rexx_ConcatExpression concatExpression;
		public Rexx_ConcatExpression concatExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Rexx.Expressions.Rexx_RelationalExpression relationalExpression;
		public Rexx_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Rexx.Expressions.Rexx_LogicalXorExpression inclusiveOrExpression;
		public Rexx_LogicalXorExpression inclusiveOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Rexx.Expressions.Rexx_LogicalAndExpression conditionalAndExpression;
		public Rexx_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Rexx.Expressions.Rexx_LogicalOrExpression conditionalOrExpression;
		public Rexx_LogicalOrExpression conditionalOrExpression;
	}

}
