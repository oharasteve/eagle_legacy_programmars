// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BitwiseExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BitwiseNotExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BuiltIn;
import com.eagle.programmar.CSharp.Expressions.CSharp_CastAsExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_CastExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationWithInitializers;
import com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationWithSubscript;
import com.eagle.programmar.CSharp.Expressions.CSharp_CommentExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ConsoleOut;
import com.eagle.programmar.CSharp.Expressions.CSharp_ConsoleRead;
import com.eagle.programmar.CSharp.Expressions.CSharp_DefaultGeneric;
import com.eagle.programmar.CSharp.Expressions.CSharp_Delegation;
import com.eagle.programmar.CSharp.Expressions.CSharp_DotClass;
import com.eagle.programmar.CSharp.Expressions.CSharp_EqualityExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ExpressionList;
import com.eagle.programmar.CSharp.Expressions.CSharp_InstanceOfExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_InterfaceCreationWithMethod;
import com.eagle.programmar.CSharp.Expressions.CSharp_LambdaBlock;
import com.eagle.programmar.CSharp.Expressions.CSharp_LambdaExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LambdaFunction;
import com.eagle.programmar.CSharp.Expressions.CSharp_LambdaParameters;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalAndExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalOrExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_MethodInvocation;
import com.eagle.programmar.CSharp.Expressions.CSharp_MultiplicativeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_NamespaceExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_NegativeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_PostIncrementExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_PreIncrementExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_RelationalExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ShiftExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_SubfieldExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_SubscriptExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_TrueFalseExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_TypeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_VariableExpression;
import com.eagle.programmar.CSharp.Functions.CSharp_MathFunction;
import com.eagle.programmar.CSharp.Functions.CSharp_PrintFunction;
import com.eagle.programmar.CSharp.Functions.CSharp_SizeOfFunction;
import com.eagle.programmar.CSharp.Functions.CSharp_StringFunction;
import com.eagle.programmar.CSharp.Methods.CSharp_IndexOfMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_LengthMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_StartsWithMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_SubstringMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_ToStringMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_ToUpperMethod;
import com.eagle.programmar.CSharp.Methods.CSharp_TrimMethod;
import com.eagle.programmar.CSharp.Terminals.CSharp_Character_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber;
import com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class CSharp_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public CSharp_Expression()
	{
		super(_operators);
	}

	public CSharp_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) CSharp_HexNumber hex;
	public @P(20) CSharp_Number number;
	public @P(30) CSharp_Literal literal;
	public @P(40) CSharp_Character_Literal characters;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) CSharp_MathFunction mathFunction;
	public @P(110) CSharp_StringFunction stringFunction;
	public @P(120) CSharp_PrintFunction printFunction;
	public @P(130) CSharp_DotClass dotClass;
	public @P(140) CSharp_CastExpression castExpression;
	public @P(150) CSharp_ExpressionList expressionList;
	public @P(160) CSharp_InterfaceCreationWithMethod interfaceCreationWithMethod;
	public @P(170) CSharp_ClassCreationExpression classCreationExpression;
	public @P(180) CSharp_ClassCreationWithInitializers classCreationWithInitializers;
	public @P(190) CSharp_ClassCreationWithSubscript classCreationWithSubscript;
	public @P(200) CSharp_MethodInvocation methodInvocation;
	public @P(210) CSharp_PreIncrementExpression preIncrementExpression;
	public @P(220) CSharp_PostIncrementExpression postIncrementExpression;
	public @P(230) CSharp_NegativeExpression negativeExpression;
	public @P(240) CSharp_BitwiseNotExpression logicalNotExpression;
	public @P(250) CSharp_LogicalNotExpression notExpression;
	public @P(260) CSharp_DefaultGeneric defaultGeneric;
	public @P(270) CSharp_BuiltIn builtIn;
	public @P(280) CSharp_ConsoleOut consoleOut;
	public @P(290) CSharp_ConsoleRead consoleRead;
	public @P(300) CSharp_VariableExpression variableExpression;
	public @P(310) CSharp_TypeExpression typeExpression;
	public @P(320) CSharp_ParenthesizedExpression parenthesizedExpression;
	public @P(330) CSharp_CommentExpression commentExpression;
	public @P(340) CSharp_SizeOfFunction typeOf;
	public @P(350) CSharp_Delegation delegation;
	public @P(360) CSharp_LambdaBlock lambdaBlock;
	public @P(370) CSharp_LambdaParameters lambdaParameters;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) CSharp_ToUpperMethod toUpperMethod;
	public @P(1010) CSharp_TrimMethod trimMethod;
	public @P(1020) CSharp_ToStringMethod toStringMethod;
	public @P(1030) CSharp_SubstringMethod substringMethod;
	public @P(1040) CSharp_IndexOfMethod indexOfMethod;
	public @P(1050) CSharp_StartsWithMethod startswithMethod;
	public @P(1060) CSharp_LengthMethod lengthMethod;
	public @P(1070) CSharp_SubfieldExpression subfieldExpression;
	public @P(1080) CSharp_SubscriptExpression subscriptExpression;
	public @P(1090) CSharp_NamespaceExpression namespaceExpression;
	public @P(1100) CSharp_MultiplicativeExpression multiplicativeExpression;
	public @P(1110) CSharp_AdditiveExpression additiveExpression;
	public @P(1120) CSharp_ShiftExpression shiftExpression;
	public @P(1130) CSharp_RelationalExpression relationalExpression;
	public @P(1140) CSharp_InstanceOfExpression instanceOfExpression;
	public @P(1150) CSharp_CastAsExpression castAsExpression;
	public @P(1160) CSharp_EqualityExpression equalityExpression;
	public @P(1170) CSharp_BitwiseExpression andExpression;
	public @P(1180) CSharp_LogicalAndExpression conditionalAndExpression;
	public @P(1190) CSharp_LogicalOrExpression conditionalOrExpression;
	public @P(1200) CSharp_TrueFalseExpression trueFalseExpression;
	public @P(1210) CSharp_AssignmentExpression assignmentExpression;
	public @P(1220) CSharp_LambdaFunction lambdaFunction;
	public @P(1230) CSharp_LambdaExpression lambdaExpression;
}
