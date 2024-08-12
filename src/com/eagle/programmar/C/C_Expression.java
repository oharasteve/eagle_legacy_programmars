// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.C.Expressions.CPlus_NewExpression;
import com.eagle.programmar.C.Expressions.C_AdditiveExpression;
import com.eagle.programmar.C.Expressions.C_AddressOfVariable;
import com.eagle.programmar.C.Expressions.C_ArrowSubfield;
import com.eagle.programmar.C.Expressions.C_AssignmentExpression;
import com.eagle.programmar.C.Expressions.C_BitwiseAndExpression;
import com.eagle.programmar.C.Expressions.C_BitwiseOrExpression;
import com.eagle.programmar.C.Expressions.C_BuiltIn;
import com.eagle.programmar.C.Expressions.C_CastExpression;
import com.eagle.programmar.C.Expressions.C_CommentExpression;
import com.eagle.programmar.C.Expressions.C_ConditionalAndExpression;
import com.eagle.programmar.C.Expressions.C_ConditionalOrExpression;
import com.eagle.programmar.C.Expressions.C_DotSubfield;
import com.eagle.programmar.C.Expressions.C_EqualityExpression;
import com.eagle.programmar.C.Expressions.C_ExclusiveOrExpression;
import com.eagle.programmar.C.Expressions.C_ExpressionList;
import com.eagle.programmar.C.Expressions.C_Literals;
import com.eagle.programmar.C.Expressions.C_LogicalNotExpression;
import com.eagle.programmar.C.Expressions.C_MultiplicativeExpression;
import com.eagle.programmar.C.Expressions.C_NotExpression;
import com.eagle.programmar.C.Expressions.C_Parenthesized_Expression;
import com.eagle.programmar.C.Expressions.C_PostDecrementExpression;
import com.eagle.programmar.C.Expressions.C_PostDecrementVariable;
import com.eagle.programmar.C.Expressions.C_PostIncrementExpression;
import com.eagle.programmar.C.Expressions.C_PostIncrementVariable;
import com.eagle.programmar.C.Expressions.C_PreDecrementExpression;
import com.eagle.programmar.C.Expressions.C_PreIncrementExpression;
import com.eagle.programmar.C.Expressions.C_RelationalExpression;
import com.eagle.programmar.C.Expressions.C_ShiftExpression;
import com.eagle.programmar.C.Expressions.C_SignedExpression;
import com.eagle.programmar.C.Expressions.C_StarExpression;
import com.eagle.programmar.C.Expressions.C_SubscriptExpression;
import com.eagle.programmar.C.Expressions.C_TrueFalseExpression;
import com.eagle.programmar.C.Expressions.C_VaArgExpr;
import com.eagle.programmar.C.Expressions.C_VariableExpression;
import com.eagle.programmar.C.Functions.C_BuiltInFunction;
import com.eagle.programmar.C.Functions.C_FunctionCall;
import com.eagle.programmar.C.Functions.C_FunctionName;
import com.eagle.programmar.C.Functions.C_FunctionPointerCall;
import com.eagle.programmar.C.Functions.C_PrintfFunction;
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
	public @P(130) C_PrintfFunction printfFunction;
	public @P(140) C_StrCmpFunction strcmpFunction;
	public @P(150) C_StrNCmpFunction strncmpFunction;
	public @P(160) C_StrCatFunction strcatFunction;
	public @P(170) C_StrCpyFunction strcpyFunction;
	public @P(180) C_StrDupFunction strdupFunction;
	public @P(190) C_StrLenFunction strlenFunction;
	public @P(200) C_BuiltInFunction builtInFunction;
	public @P(210) C_FunctionCall functionCall;
	public @P(220) C_FunctionName functionName;
	public @P(230) C_FunctionPointerCall functionPointerCall;
	public @P(240) C_PreIncrementExpression preIncrementExpression;
	public @P(250) C_PreDecrementExpression preDecrementExpression;
	public @P(260) C_PostIncrementExpression postIncrementExpression;
	public @P(270) C_PostIncrementVariable postIncrementVariable;
	public @P(280) C_PostDecrementExpression postDecrementExpression;
	public @P(290) C_PostDecrementVariable postDecrementVariable;
	public @P(300) C_SignedExpression signedExpression;
	public @P(310) C_LogicalNotExpression logicalNotExpression;
	public @P(320) C_NotExpression notExpression;
	public @P(330) C_BuiltIn builtIn;
	public @P(340) C_VariableExpression variableExpression;
	public @P(350) C_AddressOfVariable addressOfVariable;
	public @P(360) C_SizeOfType sizeOfType;
	public @P(370) C_SizeOfExpr sizeOfExpr;
	public @P(380) C_VaArgExpr vaArgExpr;
	public @P(390) C_Parenthesized_Expression parenthesized_Expression;
	public @P(400) C_StarExpression starExpression;
	public @P(410) C_CommentExpression commentExpression;
	public @P(420) CPlus_NewExpression newExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) C_SubscriptExpression subscriptExpression;
	public @P(510) C_DotSubfield dotSubfield;
	public @P(520) C_ArrowSubfield arrowSubfield;
	public @P(530) C_MultiplicativeExpression multiplicativeExpression;
	public @P(540) C_AdditiveExpression additiveExpression;
	public @P(550) C_ShiftExpression shiftExpression;
	public @P(560) C_RelationalExpression relationalExpression;
	public @P(570) C_EqualityExpression equalityExpression;
	public @P(580) C_BitwiseAndExpression bitwiseAndExpression;
	public @P(590) C_ExclusiveOrExpression exclusiveOrExpression;
	public @P(600) C_BitwiseOrExpression bitwiseOrExpression;
	public @P(610) C_ConditionalAndExpression conditionalAndExpression;
	public @P(620) C_ConditionalOrExpression conditionalOrExpression;
	public @P(630) C_TrueFalseExpression trueFalseExpression;
	public @P(640) C_AssignmentExpression assignmentExpression;

	//
	// Not easy to have CPlus_Expression extend C_Expression.
	// Have to use <generics> to make it work. C_Expression and CPlus_Expression
	// would both have to derive off a generic base class which we are avoiding.
	//

	// NOTE: C++ adds the 'new' operator here. See CPlus_Expression.java and the
	// constructor in CPlus_Program.java
}
