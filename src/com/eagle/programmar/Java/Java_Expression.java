// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
import com.eagle.programmar.Java.Expressions.Java_AnnotationInvocation;
import com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
import com.eagle.programmar.Java.Expressions.Java_BitwiseAndExpression;
import com.eagle.programmar.Java.Expressions.Java_BitwiseNotExpression;
import com.eagle.programmar.Java.Expressions.Java_BitwiseOrExpression;
import com.eagle.programmar.Java.Expressions.Java_BuiltIn;
import com.eagle.programmar.Java.Expressions.Java_CastExpression;
import com.eagle.programmar.Java.Expressions.Java_ClassCreationExpression;
import com.eagle.programmar.Java.Expressions.Java_ClassCreationWithInitializers;
import com.eagle.programmar.Java.Expressions.Java_ClassCreationWithSubscript;
import com.eagle.programmar.Java.Expressions.Java_ColonColon;
import com.eagle.programmar.Java.Expressions.Java_ColonColonNew;
import com.eagle.programmar.Java.Expressions.Java_ColonColonType;
import com.eagle.programmar.Java.Expressions.Java_CommentExpression;
import com.eagle.programmar.Java.Expressions.Java_DotClass;
import com.eagle.programmar.Java.Expressions.Java_ExpressionList;
import com.eagle.programmar.Java.Expressions.Java_InstanceOfExpression;
import com.eagle.programmar.Java.Expressions.Java_InterfaceCreationWithMethod;
import com.eagle.programmar.Java.Expressions.Java_LambdaExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalAndExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalNotExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalOrExpression;
import com.eagle.programmar.Java.Expressions.Java_MethodInvocation;
import com.eagle.programmar.Java.Expressions.Java_MultiplicativeExpression;
import com.eagle.programmar.Java.Expressions.Java_NegativeExpression;
import com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
import com.eagle.programmar.Java.Expressions.Java_PostDecrementExpression;
import com.eagle.programmar.Java.Expressions.Java_PostIncrementExpression;
import com.eagle.programmar.Java.Expressions.Java_PreDecrementExpression;
import com.eagle.programmar.Java.Expressions.Java_PreIncrementExpression;
import com.eagle.programmar.Java.Expressions.Java_RelationalExpression;
import com.eagle.programmar.Java.Expressions.Java_ShiftExpression;
import com.eagle.programmar.Java.Expressions.Java_SubfieldExpression;
import com.eagle.programmar.Java.Expressions.Java_SubscriptExpression;
import com.eagle.programmar.Java.Expressions.Java_TrueFalseExpression;
import com.eagle.programmar.Java.Expressions.Java_VariableExpression;
import com.eagle.programmar.Java.Functions.Java_EqualsMethod;
import com.eagle.programmar.Java.Functions.Java_LengthMethod;
import com.eagle.programmar.Java.Functions.Java_MathFunction;
import com.eagle.programmar.Java.Functions.Java_StartsWithMethod;
import com.eagle.programmar.Java.Functions.Java_SubstringMethod;
import com.eagle.programmar.Java.Functions.Java_ToUpperCaseMethod;
import com.eagle.programmar.Java.Terminals.Java_BinaryNumber;
import com.eagle.programmar.Java.Terminals.Java_Character_Literal;
import com.eagle.programmar.Java.Terminals.Java_HexFloatingNumber;
import com.eagle.programmar.Java.Terminals.Java_HexNumber;
import com.eagle.programmar.Java.Terminals.Java_Literal;
import com.eagle.programmar.Java.Terminals.Java_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Java_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Java_Expression()
	{
		super(_operators);
	}

	public Java_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Java_HexFloatingNumber hexFloat;
	public @P(20) Java_HexNumber hex;
	public @P(30) Java_BinaryNumber binary;
	public @P(40) Java_Number number;
	public @P(50) Java_Literal literal;
	public @P(60) Java_Character_Literal characters;
	public @P(70) Java_Annotation annotation;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Java_ColonColonType colonColonType;
	public @P(110) Java_MathFunction mathFunction;
	public @P(120) Java_DotClass dotClass;
	public @P(130) Java_LambdaExpression lambdaExpression;
	public @P(140) Java_CastExpression castExpression;
	public @P(150) Java_ExpressionList expressionList;
	public @P(160) Java_InterfaceCreationWithMethod interfaceCreationWithMethod;
	public @P(170) Java_ClassCreationExpression classCreationExpression;
	public @P(180) Java_ClassCreationWithInitializers classCreationWithInitializers;
	public @P(190) Java_ClassCreationWithSubscript classCreationWithSubscript;
	public @P(200) Java_MethodInvocation methodInvocation;
	public @P(210) Java_AnnotationInvocation annotationInvocation;
	public @P(220) Java_PreIncrementExpression preIncrementExpression;
	public @P(230) Java_PreDecrementExpression preDecrementExpression;
	public @P(240) Java_PostIncrementExpression postIncrementExpression;
	public @P(250) Java_PostDecrementExpression postDecrementExpression;
	public @P(260) Java_NegativeExpression negativeExpression;
	public @P(270) Java_BitwiseNotExpression logicalNotExpression;
	public @P(280) Java_LogicalNotExpression notExpression;
	public @P(290) Java_BuiltIn builtIn;
	public @P(300) Java_VariableExpression variableExpression;
	public @P(310) Java_ParenthesizedExpression parenthesizedExpression;
	public @P(320) Java_CommentExpression commentExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Java_SubscriptExpression subscriptExpression;
	public @P(1010) Java_EqualsMethod equalsMethod;
	public @P(1020) Java_ToUpperCaseMethod toUpperCaseMethod;
	public @P(1030) Java_LengthMethod lengthMethod;
	public @P(1040) Java_StartsWithMethod startsWithMethod;
	public @P(1050) Java_SubstringMethod substringMethod;
	public @P(1060) Java_SubfieldExpression subfield;
	public @P(1070) Java_ColonColon colonColon;
	public @P(1080) Java_ColonColonNew colonColonNew;
	public @P(1090) Java_MultiplicativeExpression multiplicativeExpression;
	public @P(1100) Java_AdditiveExpression additiveExpression;
	public @P(1110) Java_ShiftExpression shiftExpression;
	public @P(1120) Java_InstanceOfExpression instanceOfExpression;
	public @P(1130) Java_RelationalExpression relationalExpression;
	public @P(1140) Java_BitwiseAndExpression andExpression;
	public @P(1150) Java_BitwiseOrExpression inclusiveOrExpression;
	public @P(1160) Java_LogicalAndExpression conditionalAndExpression;
	public @P(1170) Java_LogicalOrExpression conditionalOrExpression;
	public @P(1180) Java_AssignmentExpression assignmentExpression;
	public @P(1190) Java_TrueFalseExpression trueFalseExpression;
}
