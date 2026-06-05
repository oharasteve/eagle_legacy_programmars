// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.Expressions.CPlus_NewExpression;
import com.eagle.programmar.C.Expressions.C_AdditiveExpression;
import com.eagle.programmar.C.Expressions.C_AddressOfVariable;
import com.eagle.programmar.C.Expressions.C_ArrowSubfield;
import com.eagle.programmar.C.Expressions.C_AssignmentExpression;
import com.eagle.programmar.C.Expressions.C_BitwiseAndExpression;
import com.eagle.programmar.C.Expressions.C_BitwiseNotExpression;
import com.eagle.programmar.C.Expressions.C_BitwiseOrExpression;
import com.eagle.programmar.C.Expressions.C_BuiltIn;
import com.eagle.programmar.C.Expressions.C_CastExpression;
import com.eagle.programmar.C.Expressions.C_CommentExpression;
import com.eagle.programmar.C.Expressions.C_DotSubfield;
import com.eagle.programmar.C.Expressions.C_EqualityExpression;
import com.eagle.programmar.C.Expressions.C_ExpressionList;
import com.eagle.programmar.C.Expressions.C_FunctionCall;
import com.eagle.programmar.C.Expressions.C_FunctionPointerCall;
import com.eagle.programmar.C.Expressions.C_Literals;
import com.eagle.programmar.C.Expressions.C_LogicalAndExpression;
import com.eagle.programmar.C.Expressions.C_LogicalNotExpression;
import com.eagle.programmar.C.Expressions.C_LogicalOrExpression;
import com.eagle.programmar.C.Expressions.C_LogicalXorExpression;
import com.eagle.programmar.C.Expressions.C_MultiplicativeExpression;
import com.eagle.programmar.C.Expressions.C_NegativeExpression;
import com.eagle.programmar.C.Expressions.C_Parenthesized_Expression;
import com.eagle.programmar.C.Expressions.C_PostIncrementParens;
import com.eagle.programmar.C.Expressions.C_PostIncrementVariable;
import com.eagle.programmar.C.Expressions.C_PreIncrementExpression;
import com.eagle.programmar.C.Expressions.C_RelationalExpression;
import com.eagle.programmar.C.Expressions.C_ShiftExpression;
import com.eagle.programmar.C.Expressions.C_StarExpression;
import com.eagle.programmar.C.Expressions.C_SubscriptExpression;
import com.eagle.programmar.C.Expressions.C_TrueFalseExpression;
import com.eagle.programmar.C.Expressions.C_VaArgExpr;
import com.eagle.programmar.C.Expressions.C_VariableExpression;
import com.eagle.programmar.C.Functions.C_ExitFunction;
import com.eagle.programmar.C.Functions.C_FunctionName;
import com.eagle.programmar.C.Functions.C_SizeOfExpr;
import com.eagle.programmar.C.Functions.C_SizeOfType;
import com.eagle.programmar.C.Functions.C_StrCatFunction;
import com.eagle.programmar.C.Functions.C_StrCmpFunction;
import com.eagle.programmar.C.Functions.C_StrCpyFunction;
import com.eagle.programmar.C.Functions.C_StrDupFunction;
import com.eagle.programmar.C.Functions.C_StrLenFunction;
import com.eagle.programmar.C.Functions.C_StrNCmpFunction;
import com.eagle.programmar.C.Terminals.C_Character_Literal;
import com.eagle.programmar.C.Terminals.C_HexNumber;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class C_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public C_Expression()
	{
		super(_operators);
	}

	public C_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) C_HexNumber hex;
	public @P(20) C_Number number;
	public @P(30) C_Character_Literal characters;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) C_Literals literals;
	public @P(110) C_CastExpression castExpression;
	public @P(120) C_ExpressionList expressionList;
	public @P(130) C_StrCmpFunction strcmpFunction;
	public @P(140) C_StrNCmpFunction strncmpFunction;
	public @P(150) C_StrCatFunction strcatFunction;
	public @P(160) C_StrCpyFunction strcpyFunction;
	public @P(170) C_StrDupFunction strdupFunction;
	public @P(180) C_StrLenFunction strlenFunction;
	public @P(190) C_ExitFunction exitFunction;
	public @P(200) C_FunctionPointerCall functionPointerCall;
	public @P(210) C_FunctionCall functionCall;
	public @P(220) C_FunctionName functionName;
	public @P(230) C_PreIncrementExpression preIncrementExpression;
	public @P(240) C_PostIncrementVariable postIncrementVariable;
	public @P(250) C_PostIncrementParens postIncrementParens;
	public @P(260) C_NegativeExpression signedExpression;
	public @P(270) C_BitwiseNotExpression logicalNotExpression;
	public @P(280) C_LogicalNotExpression notExpression;
	public @P(290) C_BuiltIn builtInExpression;
	public @P(300) C_VariableExpression variableExpression;
	public @P(310) C_AddressOfVariable addressOfVariable;
	public @P(320) C_SizeOfType sizeOfType;
	public @P(330) C_SizeOfExpr sizeOfExpr;
	public @P(340) C_VaArgExpr vaArgExpr;
	public @P(350) C_Parenthesized_Expression parenthesized_Expression;
	public @P(360) C_StarExpression starExpression;
	public @P(370) C_CommentExpression commentExpression;
	public @P(380) CPlus_NewExpression newExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) C_SubscriptExpression subscriptExpression;
	public @P(1010) C_DotSubfield dotSubfield;
	public @P(1020) C_ArrowSubfield arrowSubfield;
	public @P(1030) C_MultiplicativeExpression multiplicativeExpression;
	public @P(1040) C_AdditiveExpression additiveExpression;
	public @P(1050) C_ShiftExpression shiftExpression;
	public @P(1060) C_RelationalExpression relationalExpression;
	public @P(1070) C_EqualityExpression equalityExpression;
	public @P(1080) C_BitwiseAndExpression bitwiseAndExpression;
	public @P(1090) C_LogicalXorExpression exclusiveOrExpression;
	public @P(1100) C_BitwiseOrExpression bitwiseOrExpression;
	public @P(1110) C_LogicalAndExpression conditionalAndExpression;
	public @P(1120) C_LogicalOrExpression conditionalOrExpression;
	public @P(1130) C_TrueFalseExpression trueFalseExpression;
	public @P(1140) C_AssignmentExpression assignmentExpression;

	//
	// Not easy to have CPlus_Expression extend C_Expression.
	// Have to use <generics> to make it work. C_Expression and CPlus_Expression
	// would both have to derive off a generic base class which we are avoiding.
	//

	// NOTE: C++ adds the 'new' operator here. See CPlus_Expression.java and the
	// constructor in CPlus_Program.java
}
