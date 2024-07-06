// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_AndExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_BuiltIn;
import com.eagle.programmar.CSharp.Expressions.CSharp_BuiltInMethod;
import com.eagle.programmar.CSharp.Expressions.CSharp_CastAsExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_CastExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationWithInitializers;
import com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationWithSubscript;
import com.eagle.programmar.CSharp.Expressions.CSharp_CommentExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ConditionalAndExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ConditionalOrExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ConsoleOut;
import com.eagle.programmar.CSharp.Expressions.CSharp_ConsoleRead;
import com.eagle.programmar.CSharp.Expressions.CSharp_DefaultGeneric;
import com.eagle.programmar.CSharp.Expressions.CSharp_Delegation;
import com.eagle.programmar.CSharp.Expressions.CSharp_DotClass;
import com.eagle.programmar.CSharp.Expressions.CSharp_EqualityExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ExclusiveOrExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ExpressionList;
import com.eagle.programmar.CSharp.Expressions.CSharp_InclusiveOrExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_InstanceOfExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_InterfaceCreationWithMethod;
import com.eagle.programmar.CSharp.Expressions.CSharp_LambdaBlock;
import com.eagle.programmar.CSharp.Expressions.CSharp_LambdaExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LambdaFunction;
import com.eagle.programmar.CSharp.Expressions.CSharp_LambdaParameters;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_MethodInvocation;
import com.eagle.programmar.CSharp.Expressions.CSharp_MultiplicativeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_NamespaceExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_NegativeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_NotExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_PostDecrementExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_PostIncrementExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_PreDecrementExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_PreIncrementExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_RelationalExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ShiftExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_SubfieldExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_SubscriptExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_TrueFalseExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_TypeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_TypeOf;
import com.eagle.programmar.CSharp.Expressions.CSharp_VariableExpression;
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

	public @P(100) CSharp_DotClass dotClass;
	public @P(110) CSharp_CastExpression castExpression;
	public @P(120) CSharp_ExpressionList expressionList;
	public @P(130) CSharp_InterfaceCreationWithMethod interfaceCreationWithMethod;
	public @P(140) CSharp_ClassCreationExpression classCreationExpression;
	public @P(150) CSharp_ClassCreationWithInitializers classCreationWithInitializers;
	public @P(160) CSharp_ClassCreationWithSubscript classCreationWithSubscript;
	public @P(170) CSharp_MethodInvocation methodInvocation;
	public @P(180) CSharp_PreIncrementExpression preIncrementExpression;
	public @P(190) CSharp_PreDecrementExpression preDecrementExpression;
	public @P(200) CSharp_PostIncrementExpression postIncrementExpression;
	public @P(210) CSharp_PostDecrementExpression postDecrementExpression;
	public @P(220) CSharp_NegativeExpression negativeExpression;
	public @P(230) CSharp_LogicalNotExpression logicalNotExpression;
	public @P(240) CSharp_NotExpression notExpression;
	public @P(250) CSharp_DefaultGeneric defaultGeneric;
	public @P(260) CSharp_BuiltIn builtIn;
	public @P(270) CSharp_ConsoleOut consoleOut;
	public @P(280) CSharp_ConsoleRead consoleRead;
	public @P(290) CSharp_BuiltInMethod builtInMethod;
	public @P(300) CSharp_VariableExpression variableExpression;
	public @P(310) CSharp_TypeExpression typeExpression;
	public @P(320) CSharp_ParenthesizedExpression parenthesizedExpression;
	public @P(330) CSharp_CommentExpression commentExpression;
	public @P(340) CSharp_TypeOf typeOf;
	public @P(350) CSharp_Delegation delegation;
	public @P(360) CSharp_LambdaBlock lambdaBlock;
	public @P(370) CSharp_LambdaParameters lambdaParameters;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) CSharp_SubscriptExpression subscriptExpression;
	public @P(510) CSharp_NamespaceExpression namespaceExpression;
	public @P(520) CSharp_SubfieldExpression subfieldExpression;
	public @P(530) CSharp_MultiplicativeExpression multiplicativeExpression;
	public @P(540) CSharp_AdditiveExpression additiveExpression;
	public @P(550) CSharp_ShiftExpression shiftExpression;
	public @P(560) CSharp_RelationalExpression relationalExpression;
	public @P(570) CSharp_InstanceOfExpression instanceOfExpression;
	public @P(580) CSharp_CastAsExpression castAsExpression;
	public @P(590) CSharp_EqualityExpression equalityExpression;
	public @P(600) CSharp_AndExpression andExpression;
	public @P(610) CSharp_ExclusiveOrExpression exclusiveOrExpression;
	public @P(620) CSharp_InclusiveOrExpression inclusiveOrExpression;
	public @P(630) CSharp_ConditionalAndExpression conditionalAndExpression;
	public @P(640) CSharp_ConditionalOrExpression conditionalOrExpression;
	public @P(650) CSharp_TrueFalseExpression trueFalseExpression;
	public @P(660) CSharp_AssignmentExpression assignmentExpression;
	public @P(670) CSharp_LambdaFunction lambdaFunction;
	public @P(680) CSharp_LambdaExpression lambdaExpression;
}
