// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia;

import com.eagle.programmar.Julia.Expressions.Julia_AdditiveExpression;
import com.eagle.programmar.Julia.Expressions.Julia_AssignmentExpression;
import com.eagle.programmar.Julia.Expressions.Julia_BracketsExpression;
import com.eagle.programmar.Julia.Expressions.Julia_BuiltIn;
import com.eagle.programmar.Julia.Expressions.Julia_ConditionalAndExpression;
import com.eagle.programmar.Julia.Expressions.Julia_ConditionalOrExpression;
import com.eagle.programmar.Julia.Expressions.Julia_EqualityExpression;
import com.eagle.programmar.Julia.Expressions.Julia_FunctionCall;
import com.eagle.programmar.Julia.Expressions.Julia_LogicalNotExpression;
import com.eagle.programmar.Julia.Expressions.Julia_MultiplicativeExpression;
import com.eagle.programmar.Julia.Expressions.Julia_NegativeExpression;
import com.eagle.programmar.Julia.Expressions.Julia_NotExpression;
import com.eagle.programmar.Julia.Expressions.Julia_ParenthesizedExpression;
import com.eagle.programmar.Julia.Expressions.Julia_PostIncrementExpression;
import com.eagle.programmar.Julia.Expressions.Julia_PreIncrementExpression;
import com.eagle.programmar.Julia.Expressions.Julia_RangeExpression;
import com.eagle.programmar.Julia.Expressions.Julia_RelationalExpression;
import com.eagle.programmar.Julia.Expressions.Julia_Subfield;
import com.eagle.programmar.Julia.Expressions.Julia_SubscriptExpression;
import com.eagle.programmar.Julia.Expressions.Julia_VariableExpression;
import com.eagle.programmar.Julia.Terminals.Julia_Literal;
import com.eagle.programmar.Julia.Terminals.Julia_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Julia_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Julia_Expression()
	{
		super(_operators);
	}

	public Julia_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Julia_Number number;
	public @P(20) Julia_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Julia_FunctionCall functionCall;
	public @P(110) Julia_PreIncrementExpression preIncrementExpression;
	public @P(120) Julia_PostIncrementExpression postIncrementExpression;
	public @P(130) Julia_NegativeExpression negativeExpression;
	public @P(140) Julia_LogicalNotExpression logicalNotExpression;
	public @P(150) Julia_NotExpression notExpression;
	public @P(160) Julia_BuiltIn builtIn;
	public @P(170) Julia_VariableExpression variableExpression;
	public @P(180) Julia_BracketsExpression bracketsExpression;
	public @P(190) Julia_ParenthesizedExpression parenthesizedExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Julia_SubscriptExpression subscriptExpression;
	public @P(510) Julia_Subfield subfield;
	public @P(520) Julia_MultiplicativeExpression multiplicativeExpression;
	public @P(530) Julia_AdditiveExpression additiveExpression;
	public @P(540) Julia_RelationalExpression relationalExpression;
	public @P(550) Julia_EqualityExpression equalityExpression;
	public @P(560) Julia_ConditionalAndExpression conditionalAndExpression;
	public @P(570) Julia_ConditionalOrExpression conditionalOrExpression;
	public @P(580) Julia_AssignmentExpression assignmentExpression;
	public @P(590) Julia_RangeExpression rangeExpression;
}
