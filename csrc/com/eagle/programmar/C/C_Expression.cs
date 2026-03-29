// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C
{
	using CPlus_NewExpression = com.eagle.programmar.C.Expressions.CPlus_NewExpression;
	using C_AdditiveExpression = com.eagle.programmar.C.Expressions.C_AdditiveExpression;
	using C_AddressOfVariable = com.eagle.programmar.C.Expressions.C_AddressOfVariable;
	using C_ArrowSubfield = com.eagle.programmar.C.Expressions.C_ArrowSubfield;
	using C_AssignmentExpression = com.eagle.programmar.C.Expressions.C_AssignmentExpression;
	using C_BitwiseAndExpression = com.eagle.programmar.C.Expressions.C_BitwiseAndExpression;
	using C_BitwiseNotExpression = com.eagle.programmar.C.Expressions.C_BitwiseNotExpression;
	using C_BitwiseOrExpression = com.eagle.programmar.C.Expressions.C_BitwiseOrExpression;
	using C_BuiltIn = com.eagle.programmar.C.Expressions.C_BuiltIn;
	using C_CastExpression = com.eagle.programmar.C.Expressions.C_CastExpression;
	using C_CommentExpression = com.eagle.programmar.C.Expressions.C_CommentExpression;
	using C_DotSubfield = com.eagle.programmar.C.Expressions.C_DotSubfield;
	using C_EqualityExpression = com.eagle.programmar.C.Expressions.C_EqualityExpression;
	using C_ExpressionList = com.eagle.programmar.C.Expressions.C_ExpressionList;
	using C_FunctionCall = com.eagle.programmar.C.Expressions.C_FunctionCall;
	using C_FunctionPointerCall = com.eagle.programmar.C.Expressions.C_FunctionPointerCall;
	using C_Literals = com.eagle.programmar.C.Expressions.C_Literals;
	using C_LogicalAndExpression = com.eagle.programmar.C.Expressions.C_LogicalAndExpression;
	using C_LogicalNotExpression = com.eagle.programmar.C.Expressions.C_LogicalNotExpression;
	using C_LogicalOrExpression = com.eagle.programmar.C.Expressions.C_LogicalOrExpression;
	using C_LogicalXorExpression = com.eagle.programmar.C.Expressions.C_LogicalXorExpression;
	using C_MultiplicativeExpression = com.eagle.programmar.C.Expressions.C_MultiplicativeExpression;
	using C_NegativeExpression = com.eagle.programmar.C.Expressions.C_NegativeExpression;
	using C_Parenthesized_Expression = com.eagle.programmar.C.Expressions.C_Parenthesized_Expression;
	using C_PostIncrementVariable = com.eagle.programmar.C.Expressions.C_PostIncrementVariable;
	using C_PreIncrementExpression = com.eagle.programmar.C.Expressions.C_PreIncrementExpression;
	using C_RelationalExpression = com.eagle.programmar.C.Expressions.C_RelationalExpression;
	using C_ShiftExpression = com.eagle.programmar.C.Expressions.C_ShiftExpression;
	using C_StarExpression = com.eagle.programmar.C.Expressions.C_StarExpression;
	using C_SubscriptExpression = com.eagle.programmar.C.Expressions.C_SubscriptExpression;
	using C_TrueFalseExpression = com.eagle.programmar.C.Expressions.C_TrueFalseExpression;
	using C_VaArgExpr = com.eagle.programmar.C.Expressions.C_VaArgExpr;
	using C_VariableExpression = com.eagle.programmar.C.Expressions.C_VariableExpression;
	using C_ExitFunction = com.eagle.programmar.C.Functions.C_ExitFunction;
	using C_FunctionName = com.eagle.programmar.C.Functions.C_FunctionName;
	using C_SizeOfExpr = com.eagle.programmar.C.Functions.C_SizeOfExpr;
	using C_SizeOfType = com.eagle.programmar.C.Functions.C_SizeOfType;
	using C_StrCatFunction = com.eagle.programmar.C.Functions.C_StrCatFunction;
	using C_StrCmpFunction = com.eagle.programmar.C.Functions.C_StrCmpFunction;
	using C_StrCpyFunction = com.eagle.programmar.C.Functions.C_StrCpyFunction;
	using C_StrDupFunction = com.eagle.programmar.C.Functions.C_StrDupFunction;
	using C_StrLenFunction = com.eagle.programmar.C.Functions.C_StrLenFunction;
	using C_StrNCmpFunction = com.eagle.programmar.C.Functions.C_StrNCmpFunction;
	using C_Character_Literal = com.eagle.programmar.C.Terminals.C_Character_Literal;
	using C_HexNumber = com.eagle.programmar.C.Terminals.C_HexNumber;
	using C_Number = com.eagle.programmar.C.Terminals.C_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class C_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public C_Expression() : base(_operators)
		{
		}

		public C_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.C.Terminals.C_HexNumber hex;
		public C_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.C.Terminals.C_Number number;
		public C_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.C.Terminals.C_Character_Literal characters;
		public C_Character_Literal characters;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.C.Expressions.C_Literals literals;
		public C_Literals literals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.C.Expressions.C_CastExpression castExpression;
		public C_CastExpression castExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.C.Expressions.C_ExpressionList expressionList;
		public C_ExpressionList expressionList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.C.Functions.C_StrCmpFunction strcmpFunction;
		public C_StrCmpFunction strcmpFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.C.Functions.C_StrNCmpFunction strncmpFunction;
		public C_StrNCmpFunction strncmpFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.C.Functions.C_StrCatFunction strcatFunction;
		public C_StrCatFunction strcatFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.C.Functions.C_StrCpyFunction strcpyFunction;
		public C_StrCpyFunction strcpyFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.C.Functions.C_StrDupFunction strdupFunction;
		public C_StrDupFunction strdupFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.C.Functions.C_StrLenFunction strlenFunction;
		public C_StrLenFunction strlenFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.C.Functions.C_ExitFunction builtInFunction;
		public C_ExitFunction builtInFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.C.Expressions.C_FunctionCall functionCall;
		public C_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.C.Functions.C_FunctionName functionName;
		public C_FunctionName functionName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.C.Expressions.C_FunctionPointerCall functionPointerCall;
		public C_FunctionPointerCall functionPointerCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.C.Expressions.C_PreIncrementExpression preIncrementExpression;
		public C_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(240) com.eagle.programmar.C.Expressions.C_PostIncrementVariable postIncrementVariable;
		public C_PostIncrementVariable postIncrementVariable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(250) com.eagle.programmar.C.Expressions.C_NegativeExpression signedExpression;
		public C_NegativeExpression signedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(260) com.eagle.programmar.C.Expressions.C_BitwiseNotExpression logicalNotExpression;
		public C_BitwiseNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(270) com.eagle.programmar.C.Expressions.C_LogicalNotExpression notExpression;
		public C_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(280) com.eagle.programmar.C.Expressions.C_BuiltIn builtIn;
		public C_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(290) com.eagle.programmar.C.Expressions.C_VariableExpression variableExpression;
		public C_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(300) com.eagle.programmar.C.Expressions.C_AddressOfVariable addressOfVariable;
		public C_AddressOfVariable addressOfVariable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(310) com.eagle.programmar.C.Functions.C_SizeOfType sizeOfType;
		public C_SizeOfType sizeOfType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(320) com.eagle.programmar.C.Functions.C_SizeOfExpr sizeOfExpr;
		public C_SizeOfExpr sizeOfExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(330) com.eagle.programmar.C.Expressions.C_VaArgExpr vaArgExpr;
		public C_VaArgExpr vaArgExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(340) com.eagle.programmar.C.Expressions.C_Parenthesized_Expression parenthesized_Expression;
		public C_Parenthesized_Expression parenthesized_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(350) com.eagle.programmar.C.Expressions.C_StarExpression starExpression;
		public C_StarExpression starExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(360) com.eagle.programmar.C.Expressions.C_CommentExpression commentExpression;
		public C_CommentExpression commentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(370) com.eagle.programmar.C.Expressions.CPlus_NewExpression newExpression;
		public CPlus_NewExpression newExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.C.Expressions.C_SubscriptExpression subscriptExpression;
		public C_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.C.Expressions.C_DotSubfield dotSubfield;
		public C_DotSubfield dotSubfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.C.Expressions.C_ArrowSubfield arrowSubfield;
		public C_ArrowSubfield arrowSubfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.C.Expressions.C_MultiplicativeExpression multiplicativeExpression;
		public C_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.C.Expressions.C_AdditiveExpression additiveExpression;
		public C_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.C.Expressions.C_ShiftExpression shiftExpression;
		public C_ShiftExpression shiftExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.C.Expressions.C_RelationalExpression relationalExpression;
		public C_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.C.Expressions.C_EqualityExpression equalityExpression;
		public C_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.C.Expressions.C_BitwiseAndExpression bitwiseAndExpression;
		public C_BitwiseAndExpression bitwiseAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.C.Expressions.C_LogicalXorExpression exclusiveOrExpression;
		public C_LogicalXorExpression exclusiveOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.C.Expressions.C_BitwiseOrExpression bitwiseOrExpression;
		public C_BitwiseOrExpression bitwiseOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1110) com.eagle.programmar.C.Expressions.C_LogicalAndExpression conditionalAndExpression;
		public C_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1120) com.eagle.programmar.C.Expressions.C_LogicalOrExpression conditionalOrExpression;
		public C_LogicalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1130) com.eagle.programmar.C.Expressions.C_TrueFalseExpression trueFalseExpression;
		public C_TrueFalseExpression trueFalseExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1140) com.eagle.programmar.C.Expressions.C_AssignmentExpression assignmentExpression;
		public C_AssignmentExpression assignmentExpression;

		//
		// Not easy to have CPlus_Expression extend C_Expression.
		// Have to use <generics> to make it work. C_Expression and CPlus_Expression
		// would both have to derive off a generic base class which we are avoiding.
		//

		// NOTE: C++ adds the 'new' operator here. See CPlus_Expression.java and the
		// constructor in CPlus_Program.java
	}

}
