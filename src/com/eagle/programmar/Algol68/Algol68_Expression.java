// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68;

import com.eagle.programmar.Algol68.Expressions.Algol68_AdditiveExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_ArrayInfo;
import com.eagle.programmar.Algol68.Expressions.Algol68_AssignmentExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_BracketsExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_BuiltIn;
import com.eagle.programmar.Algol68.Expressions.Algol68_ConditionalAndExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_ConditionalOrExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_LogicalNotExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_MethodInvocation;
import com.eagle.programmar.Algol68.Expressions.Algol68_MultiplicativeExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_NegativeExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_ParenthesizedExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_PostIncrementExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_Power_Expression;
import com.eagle.programmar.Algol68.Expressions.Algol68_PreIncrementExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_RangeExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_RelationalExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_Subfield;
import com.eagle.programmar.Algol68.Expressions.Algol68_SubscriptExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_VariableExpression;
import com.eagle.programmar.Algol68.Terminals.Algol68_Literal;
import com.eagle.programmar.Algol68.Terminals.Algol68_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Algol68_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Algol68_Expression()
	{
		super(_operators);
	}

	public Algol68_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Algol68_Number number;
	public @P(20) Algol68_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Algol68_ArrayInfo arrayInfo;
	public @P(110) Algol68_MethodInvocation methodInvocation;
	public @P(120) Algol68_PreIncrementExpression preIncrementExpression;
	public @P(130) Algol68_PostIncrementExpression postIncrementExpression;
	public @P(140) Algol68_NegativeExpression negativeExpression;
	public @P(150) Algol68_LogicalNotExpression logicalNotExpression;
	public @P(160) Algol68_BuiltIn builtIn;
	public @P(170) Algol68_VariableExpression variableExpression;
	public @P(180) Algol68_BracketsExpression bracketsExpression;
	public @P(190) Algol68_ParenthesizedExpression parenthesizedExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Algol68_SubscriptExpression subscriptExpression;
	public @P(510) Algol68_Subfield subfield;
	public @P(520) Algol68_Power_Expression powerExpression;
	public @P(530) Algol68_MultiplicativeExpression multiplicativeExpression;
	public @P(540) Algol68_AdditiveExpression additiveExpression;
	public @P(550) Algol68_RelationalExpression relationalExpression;
	public @P(560) Algol68_ConditionalAndExpression conditionalAndExpression;
	public @P(570) Algol68_ConditionalOrExpression conditionalOrExpression;
	public @P(580) Algol68_AssignmentExpression assignmentExpression;
	public @P(590) Algol68_RangeExpression rangeExpression;
}
