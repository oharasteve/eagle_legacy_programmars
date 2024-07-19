// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Expressions.Javascript_AdditiveExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_AndExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_AssignmentExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_AwaitFunctionCall;
import com.eagle.programmar.Javascript.Expressions.Javascript_BuiltInVar;
import com.eagle.programmar.Javascript.Expressions.Javascript_BuiltinFunction;
import com.eagle.programmar.Javascript.Expressions.Javascript_CastExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_ClassCreationExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_ClassCreationWithInitializers;
import com.eagle.programmar.Javascript.Expressions.Javascript_ClassCreationWithSubscript;
import com.eagle.programmar.Javascript.Expressions.Javascript_ClassExpr;
import com.eagle.programmar.Javascript.Expressions.Javascript_CommentExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_ConditionalAndExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_ConditionalOrExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_DeleteExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_DotClass;
import com.eagle.programmar.Javascript.Expressions.Javascript_EllipsisExpr;
import com.eagle.programmar.Javascript.Expressions.Javascript_EqualityExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_ExclusiveOrExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_ExpressionList;
import com.eagle.programmar.Javascript.Expressions.Javascript_FunctionCall;
import com.eagle.programmar.Javascript.Expressions.Javascript_FunctionExpr;
import com.eagle.programmar.Javascript.Expressions.Javascript_InExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_InclusiveOrExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_InstanceOfExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_LambdaFunction;
import com.eagle.programmar.Javascript.Expressions.Javascript_LogicalNotExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_MultiplicativeExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_NegativeExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_NewNoArgsExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_NotExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_ObjectLiteral;
import com.eagle.programmar.Javascript.Expressions.Javascript_ParenthesizedFunction;
import com.eagle.programmar.Javascript.Expressions.Javascript_Parenthesized_Expression;
import com.eagle.programmar.Javascript.Expressions.Javascript_PostDecrementExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_PostIncrementExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_Power_Expression;
import com.eagle.programmar.Javascript.Expressions.Javascript_PreDecrementExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_PreIncrementExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_RelationalExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_ShiftExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_SimpleArray;
import com.eagle.programmar.Javascript.Expressions.Javascript_StrangeFunction;
import com.eagle.programmar.Javascript.Expressions.Javascript_StringFunction;
import com.eagle.programmar.Javascript.Expressions.Javascript_Subfield;
import com.eagle.programmar.Javascript.Expressions.Javascript_SubscriptExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_TemplateExpr;
import com.eagle.programmar.Javascript.Expressions.Javascript_TrueFalseExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_TypeOfExpr;
import com.eagle.programmar.Javascript.Expressions.Javascript_VariableExpression;
import com.eagle.programmar.Javascript.Expressions.Javascript_VoidExpr;
import com.eagle.programmar.Javascript.Terminals.Javascript_HexNumber;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.programmar.Javascript.Terminals.Javascript_Number;
import com.eagle.programmar.Javascript.Terminals.Javascript_RegularExpression;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class Javascript_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public Javascript_Expression()
	{
		super(_operators);
	}

	public Javascript_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Javascript_HexNumber hex;
	public @P(20) Javascript_Number number;
	public @P(30) Javascript_Literal literal;
	public @P(40) Javascript_RegularExpression regEx;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Javascript_BuiltinFunction builtin_Function;
	public @P(110) Javascript_DotClass dotClass;
	public @P(120) Javascript_CastExpression castExpression;
	public @P(130) Javascript_ExpressionList expressionList;
	public @P(140) Javascript_StringFunction stringFunction;
	public @P(150) Javascript_DeleteExpression deleteExpression;
	public @P(160) Javascript_ClassCreationExpression classCreationExpression;
	public @P(170) Javascript_ClassCreationWithInitializers classCreationWithInitializers;
	public @P(180) Javascript_ClassCreationWithSubscript classCreationWithSubscript;
	public @P(190) Javascript_NewNoArgsExpression newNoArgsExpression;
	public @P(200) Javascript_AwaitFunctionCall awaitFunctionCall;
	public @P(210) Javascript_FunctionCall functionCall;
	public @P(220) Javascript_PreIncrementExpression preIncrementExpression;
	public @P(230) Javascript_PreDecrementExpression preDecrementExpression;
	public @P(240) Javascript_PostIncrementExpression postIncrementExpression;
	public @P(250) Javascript_PostDecrementExpression postDecrementExpression;
	public @P(260) Javascript_NegativeExpression negativeExpression;
	public @P(270) Javascript_LogicalNotExpression logicalNotExpression;
	public @P(280) Javascript_NotExpression notExpression;
	public @P(290) Javascript_BuiltInVar builtInVar;
	public @P(300) Javascript_TemplateExpr templateExpr;
	public @P(310) Javascript_LambdaFunction lambdaFunction;
	public @P(320) Javascript_VariableExpression variableExpression;
	public @P(330) Javascript_StrangeFunction strangeFunction;
	public @P(340) Javascript_ParenthesizedFunction parenthesizedFunction;
	public @P(350) Javascript_Parenthesized_Expression parenthesized_Expression;
	public @P(360) Javascript_SimpleArray simpleArray;
	public @P(370) Javascript_CommentExpression commentExpression;
	public @P(380) Javascript_ObjectLiteral objectLiteral;
	public @P(390) Javascript_FunctionExpr functionExpr;
	public @P(400) Javascript_ClassExpr classExpr;
	public @P(410) Javascript_TypeOfExpr typeOfExpr;
	public @P(420) Javascript_VoidExpr voidExpr;
	public @P(430) Javascript_EllipsisExpr ellipsisExpr;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Javascript_SubscriptExpression subscriptExpression;
	public @P(510) Javascript_Subfield subfield;
	public @P(520) Javascript_Power_Expression power_Expression;
	public @P(530) Javascript_MultiplicativeExpression multiplicativeExpression;
	public @P(540) Javascript_AdditiveExpression additiveExpression;
	public @P(550) Javascript_ShiftExpression shiftExpression;
	public @P(560) Javascript_RelationalExpression relationalExpression;
	public @P(570) Javascript_InstanceOfExpression instanceOfExpression;
	public @P(580) Javascript_InExpression inExpression;
	public @P(590) Javascript_EqualityExpression equalityExpression;
	public @P(600) Javascript_AndExpression andExpression;
	public @P(610) Javascript_ExclusiveOrExpression exclusiveOrExpression;
	public @P(620) Javascript_InclusiveOrExpression inclusiveOrExpression;
	public @P(630) Javascript_ConditionalAndExpression conditionalAndExpression;
	public @P(640) Javascript_ConditionalOrExpression conditionalOrExpression;
	public @P(650) Javascript_TrueFalseExpression trueFalseExpression;
	public @P(660) Javascript_AssignmentExpression assignmentExpression;
}
