// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Expressions.AWK_AdditiveExpression;
import com.eagle.programmar.AWK.Expressions.AWK_AndExpression;
import com.eagle.programmar.AWK.Expressions.AWK_AssignmentExpression;
import com.eagle.programmar.AWK.Expressions.AWK_BuiltinFunctionCall;
import com.eagle.programmar.AWK.Expressions.AWK_ConcatenationExpression;
import com.eagle.programmar.AWK.Expressions.AWK_DollarParensExpression;
import com.eagle.programmar.AWK.Expressions.AWK_GetLine;
import com.eagle.programmar.AWK.Expressions.AWK_InExpression;
import com.eagle.programmar.AWK.Expressions.AWK_MultiplicativeExpression;
import com.eagle.programmar.AWK.Expressions.AWK_NegativeExpression;
import com.eagle.programmar.AWK.Expressions.AWK_NotExpression;
import com.eagle.programmar.AWK.Expressions.AWK_OrExpression;
import com.eagle.programmar.AWK.Expressions.AWK_ParenthesizedExpression;
import com.eagle.programmar.AWK.Expressions.AWK_PatternExpression;
import com.eagle.programmar.AWK.Expressions.AWK_PostDecrementExpression;
import com.eagle.programmar.AWK.Expressions.AWK_PostIncrementExpression;
import com.eagle.programmar.AWK.Expressions.AWK_PreDecrementExpression;
import com.eagle.programmar.AWK.Expressions.AWK_PreIncrementExpression;
import com.eagle.programmar.AWK.Expressions.AWK_RegularExpression;
import com.eagle.programmar.AWK.Expressions.AWK_RelationalExpression;
import com.eagle.programmar.AWK.Expressions.AWK_String;
import com.eagle.programmar.AWK.Expressions.AWK_SubscriptExpression;
import com.eagle.programmar.AWK.Expressions.AWK_TrueFalseExpression;
import com.eagle.programmar.AWK.Expressions.AWK_UserFunctionCall;
import com.eagle.programmar.AWK.Expressions.AWK_VariableExpression;
import com.eagle.programmar.AWK.Terminals.AWK_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class AWK_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public AWK_Expression()
	{
		super(_operators);
	}

	public AWK_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) AWK_Number number;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) AWK_GetLine getLine;
	public @P(110) AWK_UserFunctionCall userFunctionCall;
	public @P(120) AWK_PatternExpression patternExpression;
	public @P(130) AWK_PreIncrementExpression preIncrementExpression;
	public @P(140) AWK_PreDecrementExpression preDecrementExpression;
	public @P(150) AWK_PostIncrementExpression postIncrementExpression;
	public @P(160) AWK_PostDecrementExpression postDecrementExpression;
	public @P(170) AWK_NegativeExpression negativeExpression;
	public @P(180) AWK_NotExpression notExpression;
	public @P(190) AWK_ConcatenationExpression concateationExpression; // Has to precede vars and strings
	public @P(200) AWK_VariableExpression variableExpression;
	public @P(210) AWK_String string;
	public @P(220) AWK_BuiltinFunctionCall builtinFunctionCall;
	public @P(230) AWK_ParenthesizedExpression parenthesizedExpression;
	public @P(240) AWK_DollarParensExpression dollarParensExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) AWK_SubscriptExpression subscriptExpression;
	public @P(510) AWK_MultiplicativeExpression multiplicativeExpression;
	public @P(520) AWK_AdditiveExpression additiveExpression;
	public @P(530) AWK_RelationalExpression relationalExpression;
	public @P(540) AWK_RegularExpression regularExpression;
	public @P(550) AWK_InExpression inExpression;
	public @P(560) AWK_AndExpression andExpression;
	public @P(570) AWK_OrExpression orExpression;
	public @P(580) AWK_TrueFalseExpression trueFalseExpression;
	public @P(590) AWK_AssignmentExpression assignmentExpression;
}
