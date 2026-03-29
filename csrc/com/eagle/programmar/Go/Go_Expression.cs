// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

namespace com.eagle.programmar.Go
{
	using Go_AdditiveExpression = com.eagle.programmar.Go.Expressions.Go_AdditiveExpression;
	using Go_AmpersandExpression = com.eagle.programmar.Go.Expressions.Go_AmpersandExpression;
	using Go_AssignmentExpression = com.eagle.programmar.Go.Expressions.Go_AssignmentExpression;
	using Go_BracesExpression = com.eagle.programmar.Go.Expressions.Go_BracesExpression;
	using Go_BuiltIn = com.eagle.programmar.Go.Expressions.Go_BuiltIn;
	using Go_Constructor = com.eagle.programmar.Go.Expressions.Go_Constructor;
	using Go_EmptyArrayExpression = com.eagle.programmar.Go.Expressions.Go_EmptyArrayExpression;
	using Go_EqualityExpression = com.eagle.programmar.Go.Expressions.Go_EqualityExpression;
	using Go_FunctionCall = com.eagle.programmar.Go.Expressions.Go_FunctionCall;
	using Go_LogicalAndExpression = com.eagle.programmar.Go.Expressions.Go_LogicalAndExpression;
	using Go_LogicalNotExpression = com.eagle.programmar.Go.Expressions.Go_LogicalNotExpression;
	using Go_LogicalOrExpression = com.eagle.programmar.Go.Expressions.Go_LogicalOrExpression;
	using Go_MultiplicativeExpression = com.eagle.programmar.Go.Expressions.Go_MultiplicativeExpression;
	using Go_NegativeExpression = com.eagle.programmar.Go.Expressions.Go_NegativeExpression;
	using Go_ParenthesizedExpression = com.eagle.programmar.Go.Expressions.Go_ParenthesizedExpression;
	using Go_PostIncrementExpression = com.eagle.programmar.Go.Expressions.Go_PostIncrementExpression;
	using Go_PreIncrementExpression = com.eagle.programmar.Go.Expressions.Go_PreIncrementExpression;
	using Go_RelationalExpression = com.eagle.programmar.Go.Expressions.Go_RelationalExpression;
	using Go_StarExpression = com.eagle.programmar.Go.Expressions.Go_StarExpression;
	using Go_SubscriptExpression = com.eagle.programmar.Go.Expressions.Go_SubscriptExpression;
	using Go_VariableExpression = com.eagle.programmar.Go.Expressions.Go_VariableExpression;
	using Go_LenFunction = com.eagle.programmar.Go.Functions.Go_LenFunction;
	using Go_Literal = com.eagle.programmar.Go.Terminals.Go_Literal;
	using Go_Number = com.eagle.programmar.Go.Terminals.Go_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Go_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Go_Expression() : base(_operators)
		{
		}

		public Go_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Go.Terminals.Go_Number number;
		public Go_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Go.Terminals.Go_Literal literal;
		public Go_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Go.Functions.Go_LenFunction lenFunction;
		public Go_LenFunction lenFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Go.Expressions.Go_FunctionCall functionCall;
		public Go_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Go.Expressions.Go_PreIncrementExpression preIncrementExpression;
		public Go_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Go.Expressions.Go_PostIncrementExpression postIncrementExpression;
		public Go_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Go.Expressions.Go_NegativeExpression negativeExpression;
		public Go_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Go.Expressions.Go_LogicalNotExpression logicalNotExpression;
		public Go_LogicalNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Go.Expressions.Go_BuiltIn builtIn;
		public Go_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Go.Expressions.Go_VariableExpression variableExpression;
		public Go_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Go.Expressions.Go_Constructor constructor;
		public Go_Constructor constructor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Go.Expressions.Go_BracesExpression bracesExpression;
		public Go_BracesExpression bracesExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Go.Expressions.Go_ParenthesizedExpression parenthesizedExpression;
		public Go_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Go.Expressions.Go_EmptyArrayExpression emptyArrayExpression;
		public Go_EmptyArrayExpression emptyArrayExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Go.Expressions.Go_StarExpression starExpression;
		public Go_StarExpression starExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.Go.Expressions.Go_AmpersandExpression ampersandExpression;
		public Go_AmpersandExpression ampersandExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Go.Expressions.Go_SubscriptExpression subscriptExpression;
		public Go_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Go.Expressions.Go_MultiplicativeExpression multiplicativeExpression;
		public Go_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Go.Expressions.Go_AdditiveExpression additiveExpression;
		public Go_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Go.Expressions.Go_RelationalExpression relationalExpression;
		public Go_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Go.Expressions.Go_EqualityExpression equalityExpression;
		public Go_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Go.Expressions.Go_LogicalAndExpression conditionalAndExpression;
		public Go_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Go.Expressions.Go_LogicalOrExpression conditionalOrExpression;
		public Go_LogicalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Go.Expressions.Go_AssignmentExpression assignmentExpression;
		public Go_AssignmentExpression assignmentExpression;
	}

}
