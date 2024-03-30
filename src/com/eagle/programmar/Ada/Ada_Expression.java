// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada;

import com.eagle.programmar.Ada.Expressions.Ada_AdditiveExpression;
import com.eagle.programmar.Ada.Expressions.Ada_AssignmentExpression;
import com.eagle.programmar.Ada.Expressions.Ada_BracketsExpression;
import com.eagle.programmar.Ada.Expressions.Ada_BuiltIn;
import com.eagle.programmar.Ada.Expressions.Ada_ConditionalAndExpression;
import com.eagle.programmar.Ada.Expressions.Ada_ConditionalOrExpression;
import com.eagle.programmar.Ada.Expressions.Ada_EqualityExpression;
import com.eagle.programmar.Ada.Expressions.Ada_LogicalNotExpression;
import com.eagle.programmar.Ada.Expressions.Ada_MethodInvocation;
import com.eagle.programmar.Ada.Expressions.Ada_MultiplicativeExpression;
import com.eagle.programmar.Ada.Expressions.Ada_NegativeExpression;
import com.eagle.programmar.Ada.Expressions.Ada_NotExpression;
import com.eagle.programmar.Ada.Expressions.Ada_ParenthesizedExpression;
import com.eagle.programmar.Ada.Expressions.Ada_PostIncrementExpression;
import com.eagle.programmar.Ada.Expressions.Ada_PreIncrementExpression;
import com.eagle.programmar.Ada.Expressions.Ada_RangeExpression;
import com.eagle.programmar.Ada.Expressions.Ada_RelationalExpression;
import com.eagle.programmar.Ada.Expressions.Ada_Subfield;
import com.eagle.programmar.Ada.Expressions.Ada_SubscriptExpression;
import com.eagle.programmar.Ada.Expressions.Ada_VariableExpression;
import com.eagle.programmar.Ada.Terminals.Ada_Literal;
import com.eagle.programmar.Ada.Terminals.Ada_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Ada_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Ada_Expression()
	{
	    super(_operators);
	}

	public Ada_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
	
	//
	// Note: All fields should stay in @P(#) order. The # determines operator precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Ada_Number number;
	public @P(20) Ada_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions
	
	public @P(100) Ada_MethodInvocation methodInvocation;
	public @P(110) Ada_PreIncrementExpression preIncrementExpression;
	public @P(120) Ada_PostIncrementExpression postIncrementExpression;
	public @P(130) Ada_NegativeExpression negativeExpression;
	public @P(140) Ada_LogicalNotExpression logicalNotExpression;
	public @P(150) Ada_NotExpression notExpression;
	public @P(160) Ada_BuiltIn builtIn;
	public @P(170) Ada_VariableExpression variableExpression;
	public @P(180) Ada_BracketsExpression bracketsExpression;
	public @P(190) Ada_ParenthesizedExpression parenthesizedExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Ada_SubscriptExpression subscriptExpression;
	public @P(510) Ada_Subfield subfield;
	public @P(520) Ada_MultiplicativeExpression multiplicativeExpression;
	public @P(530) Ada_AdditiveExpression additiveExpression;
	public @P(540) Ada_RelationalExpression relationalExpression;
	public @P(550) Ada_EqualityExpression equalityExpression;
	public @P(560) Ada_ConditionalAndExpression conditionalAndExpression;
	public @P(570) Ada_ConditionalOrExpression conditionalOrExpression;
	public @P(580) Ada_AssignmentExpression assignmentExpression;
	public @P(590) Ada_RangeExpression rangeExpression;
}
