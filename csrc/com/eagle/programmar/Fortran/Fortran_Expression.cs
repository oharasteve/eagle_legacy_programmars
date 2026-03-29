// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran
{
	using Fortran_AdditiveExpression = com.eagle.programmar.Fortran.Expressions.Fortran_AdditiveExpression;
	using Fortran_BracketExpression = com.eagle.programmar.Fortran.Expressions.Fortran_BracketExpression;
	using Fortran_BuiltIn = com.eagle.programmar.Fortran.Expressions.Fortran_BuiltIn;
	using Fortran_EqualityExpression = com.eagle.programmar.Fortran.Expressions.Fortran_EqualityExpression;
	using Fortran_FunctionCall = com.eagle.programmar.Fortran.Expressions.Fortran_FunctionCall;
	using Fortran_LogicalAndExpression = com.eagle.programmar.Fortran.Expressions.Fortran_LogicalAndExpression;
	using Fortran_LogicalNotExpression = com.eagle.programmar.Fortran.Expressions.Fortran_LogicalNotExpression;
	using Fortran_LogicalOrExpression = com.eagle.programmar.Fortran.Expressions.Fortran_LogicalOrExpression;
	using Fortran_MultiplicativeExpression = com.eagle.programmar.Fortran.Expressions.Fortran_MultiplicativeExpression;
	using Fortran_NegativeExpression = com.eagle.programmar.Fortran.Expressions.Fortran_NegativeExpression;
	using Fortran_ParenthesizedExpression = com.eagle.programmar.Fortran.Expressions.Fortran_ParenthesizedExpression;
	using Fortran_RelationalExpression = com.eagle.programmar.Fortran.Expressions.Fortran_RelationalExpression;
	using Fortran_StringConcatenation = com.eagle.programmar.Fortran.Expressions.Fortran_StringConcatenation;
	using Fortran_Subscript = com.eagle.programmar.Fortran.Expressions.Fortran_Subscript;
	using Fortran_VariableExpression = com.eagle.programmar.Fortran.Expressions.Fortran_VariableExpression;
	using Fortran_AdjustLFunction = com.eagle.programmar.Fortran.Functions.Fortran_AdjustLFunction;
	using Fortran_IndexFunction = com.eagle.programmar.Fortran.Functions.Fortran_IndexFunction;
	using Fortran_LenFunction = com.eagle.programmar.Fortran.Functions.Fortran_LenFunction;
	using Fortran_ModFunction = com.eagle.programmar.Fortran.Functions.Fortran_ModFunction;
	using Fortran_TrimFunction = com.eagle.programmar.Fortran.Functions.Fortran_TrimFunction;
	using Fortran_Literal = com.eagle.programmar.Fortran.Terminals.Fortran_Literal;
	using Fortran_Number = com.eagle.programmar.Fortran.Terminals.Fortran_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Fortran_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Fortran_Expression() : base(_operators)
		{
		}

		public Fortran_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Fortran.Terminals.Fortran_Number number;
		public Fortran_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Fortran.Terminals.Fortran_Literal literal;
		public Fortran_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Fortran.Expressions.Fortran_NegativeExpression negativeExpression;
		public Fortran_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Fortran.Functions.Fortran_AdjustLFunction adjustLFunction;
		public Fortran_AdjustLFunction adjustLFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Fortran.Functions.Fortran_LenFunction lenFunction;
		public Fortran_LenFunction lenFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Fortran.Functions.Fortran_ModFunction modFunction;
		public Fortran_ModFunction modFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Fortran.Functions.Fortran_TrimFunction trimFunction;
		public Fortran_TrimFunction trimFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Fortran.Functions.Fortran_IndexFunction indexFunction;
		public Fortran_IndexFunction indexFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Fortran.Expressions.Fortran_FunctionCall functionCall;
		public Fortran_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Fortran.Expressions.Fortran_Subscript subscript;
		public Fortran_Subscript subscript;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Fortran.Expressions.Fortran_LogicalNotExpression notExpression;
		public Fortran_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Fortran.Expressions.Fortran_BuiltIn builtIn;
		public Fortran_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Fortran.Expressions.Fortran_VariableExpression variableExpression;
		public Fortran_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Fortran.Expressions.Fortran_ParenthesizedExpression parenthesizedExpression;
		public Fortran_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Fortran.Expressions.Fortran_BracketExpression bracketExpression;
		public Fortran_BracketExpression bracketExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Fortran.Expressions.Fortran_MultiplicativeExpression multiplicativeExpression;
		public Fortran_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Fortran.Expressions.Fortran_AdditiveExpression additiveExpression;
		public Fortran_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Fortran.Expressions.Fortran_StringConcatenation stringConcatenation;
		public Fortran_StringConcatenation stringConcatenation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Fortran.Expressions.Fortran_RelationalExpression relationalExpression;
		public Fortran_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Fortran.Expressions.Fortran_EqualityExpression equalityExpression;
		public Fortran_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Fortran.Expressions.Fortran_LogicalAndExpression conditionalAndExpression;
		public Fortran_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Fortran.Expressions.Fortran_LogicalOrExpression conditionalOrExpression;
		public Fortran_LogicalOrExpression conditionalOrExpression;
	}
}
