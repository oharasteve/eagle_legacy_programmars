// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby;

import com.eagle.programmar.Ruby.Expressions.Ruby_AdditiveExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_AssignmentExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_BracketsExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_BuiltIn;
import com.eagle.programmar.Ruby.Expressions.Ruby_ConditionalAndExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_ConditionalOrExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_EqualityExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_FunctionCall;
import com.eagle.programmar.Ruby.Expressions.Ruby_LogicalNotExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_MethodInvocation;
import com.eagle.programmar.Ruby.Expressions.Ruby_MultiplicativeExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_NegativeExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_NotExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_ParenthesizedExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_PostIncrementExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_PreIncrementExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_RangeExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_RelationalExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_Subfield;
import com.eagle.programmar.Ruby.Expressions.Ruby_SubscriptExpression;
import com.eagle.programmar.Ruby.Expressions.Ruby_VariableExpression;
import com.eagle.programmar.Ruby.Terminals.Ruby_Literal;
import com.eagle.programmar.Ruby.Terminals.Ruby_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Ruby_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Ruby_Expression()
	{
		super(_operators);
	}

	public Ruby_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Ruby_Number number;
	public @P(20) Ruby_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Ruby_FunctionCall functionCall;
	public @P(105) Ruby_MethodInvocation methodInvocation;
	public @P(110) Ruby_PreIncrementExpression preIncrementExpression;
	public @P(120) Ruby_PostIncrementExpression postIncrementExpression;
	public @P(130) Ruby_NegativeExpression negativeExpression;
	public @P(140) Ruby_LogicalNotExpression logicalNotExpression;
	public @P(150) Ruby_NotExpression notExpression;
	public @P(160) Ruby_BuiltIn builtIn;
	public @P(170) Ruby_VariableExpression variableExpression;
	public @P(180) Ruby_BracketsExpression bracketsExpression;
	public @P(190) Ruby_ParenthesizedExpression parenthesizedExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Ruby_SubscriptExpression subscriptExpression;
	public @P(510) Ruby_Subfield subfield;
	public @P(520) Ruby_MultiplicativeExpression multiplicativeExpression;
	public @P(530) Ruby_AdditiveExpression additiveExpression;
	public @P(540) Ruby_RelationalExpression relationalExpression;
	public @P(550) Ruby_EqualityExpression equalityExpression;
	public @P(560) Ruby_ConditionalAndExpression conditionalAndExpression;
	public @P(570) Ruby_ConditionalOrExpression conditionalOrExpression;
	public @P(580) Ruby_AssignmentExpression assignmentExpression;
	public @P(590) Ruby_RangeExpression rangeExpression;
}
