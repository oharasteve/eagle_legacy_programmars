// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
import com.eagle.programmar.Java.Expressions.Java_AndExpression;
import com.eagle.programmar.Java.Expressions.Java_AnnotationInvocation;
import com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
import com.eagle.programmar.Java.Expressions.Java_BuiltIn;
import com.eagle.programmar.Java.Expressions.Java_BuiltInMethod;
import com.eagle.programmar.Java.Expressions.Java_CastExpression;
import com.eagle.programmar.Java.Expressions.Java_ClassCreationExpression;
import com.eagle.programmar.Java.Expressions.Java_ClassCreationWithInitializers;
import com.eagle.programmar.Java.Expressions.Java_ClassCreationWithSubscript;
import com.eagle.programmar.Java.Expressions.Java_ColonColon;
import com.eagle.programmar.Java.Expressions.Java_ColonColonNew;
import com.eagle.programmar.Java.Expressions.Java_ColonColonType;
import com.eagle.programmar.Java.Expressions.Java_CommentExpression;
import com.eagle.programmar.Java.Expressions.Java_ConditionalAndExpression;
import com.eagle.programmar.Java.Expressions.Java_ConditionalOrExpression;
import com.eagle.programmar.Java.Expressions.Java_DotClass;
import com.eagle.programmar.Java.Expressions.Java_EqualityExpression;
import com.eagle.programmar.Java.Expressions.Java_ExclusiveOrExpression;
import com.eagle.programmar.Java.Expressions.Java_ExpressionList;
import com.eagle.programmar.Java.Expressions.Java_InclusiveOrExpression;
import com.eagle.programmar.Java.Expressions.Java_InstanceOfExpression;
import com.eagle.programmar.Java.Expressions.Java_InterfaceCreationWithMethod;
import com.eagle.programmar.Java.Expressions.Java_LambdaExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalNotExpression;
import com.eagle.programmar.Java.Expressions.Java_MethodInvocation;
import com.eagle.programmar.Java.Expressions.Java_MultiplicativeExpression;
import com.eagle.programmar.Java.Expressions.Java_NegativeExpression;
import com.eagle.programmar.Java.Expressions.Java_NotExpression;
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
	public @P(110) Java_DotClass dotClass;
	public @P(120) Java_LambdaExpression lambdaExpression;
	public @P(130) Java_CastExpression castExpression;
	public @P(140) Java_ExpressionList expressionList;
	public @P(150) Java_InterfaceCreationWithMethod interfaceCreationWithMethod;
	public @P(160) Java_ClassCreationExpression classCreationExpression;
	public @P(170) Java_ClassCreationWithInitializers classCreationWithInitializers;
	public @P(180) Java_ClassCreationWithSubscript classCreationWithSubscript;
	public @P(190) Java_MethodInvocation methodInvocation;
	public @P(200) Java_AnnotationInvocation annotationInvocation;
	public @P(210) Java_PreIncrementExpression preIncrementExpression;
	public @P(220) Java_PreDecrementExpression preDecrementExpression;
	public @P(230) Java_PostIncrementExpression postIncrementExpression;
	public @P(240) Java_PostDecrementExpression postDecrementExpression;
	public @P(250) Java_NegativeExpression negativeExpression;
	public @P(260) Java_LogicalNotExpression logicalNotExpression;
	public @P(270) Java_NotExpression notExpression;
	public @P(280) Java_BuiltIn builtIn;
	public @P(290) Java_VariableExpression variableExpression;
	public @P(300) Java_ParenthesizedExpression parenthesizedExpression;
	public @P(310) Java_CommentExpression commentExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Java_SubscriptExpression subscriptExpression;
	public @P(510) Java_BuiltInMethod builtInMethod;
	public @P(520) Java_SubfieldExpression subfield;
	public @P(530) Java_ColonColon colonColon;
	public @P(540) Java_ColonColonNew colonColonNew;
	public @P(550) Java_MultiplicativeExpression multiplicativeExpression;
	public @P(560) Java_AdditiveExpression additiveExpression;
	public @P(570) Java_ShiftExpression shiftExpression;
	public @P(580) Java_RelationalExpression relationalExpression;
	public @P(590) Java_InstanceOfExpression instanceOfExpression;
	public @P(600) Java_EqualityExpression equalityExpression;
	public @P(610) Java_AndExpression andExpression;
	public @P(620) Java_ExclusiveOrExpression exclusiveOrExpression;
	public @P(630) Java_InclusiveOrExpression inclusiveOrExpression;
	public @P(640) Java_ConditionalAndExpression conditionalAndExpression;
	public @P(650) Java_ConditionalOrExpression conditionalOrExpression;
	public @P(660) Java_AssignmentExpression assignmentExpression;
	public @P(670) Java_TrueFalseExpression trueFalseExpression;
}
