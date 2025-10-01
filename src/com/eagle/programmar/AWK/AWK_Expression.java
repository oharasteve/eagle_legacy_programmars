// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Expressions.AWK_AdditiveExpression;
import com.eagle.programmar.AWK.Expressions.AWK_AssignmentExpression;
import com.eagle.programmar.AWK.Expressions.AWK_CallFunction;
import com.eagle.programmar.AWK.Expressions.AWK_ConcatenationExpression;
import com.eagle.programmar.AWK.Expressions.AWK_DollarParensExpression;
import com.eagle.programmar.AWK.Expressions.AWK_GetLine;
import com.eagle.programmar.AWK.Expressions.AWK_InExpression;
import com.eagle.programmar.AWK.Expressions.AWK_LogicalAndExpression;
import com.eagle.programmar.AWK.Expressions.AWK_LogicalNotExpression;
import com.eagle.programmar.AWK.Expressions.AWK_LogicalOrExpression;
import com.eagle.programmar.AWK.Expressions.AWK_MultiplicativeExpression;
import com.eagle.programmar.AWK.Expressions.AWK_NegativeExpression;
import com.eagle.programmar.AWK.Expressions.AWK_ParenthesizedExpression;
import com.eagle.programmar.AWK.Expressions.AWK_PatternExpression;
import com.eagle.programmar.AWK.Expressions.AWK_PostIncrementExpression;
import com.eagle.programmar.AWK.Expressions.AWK_PreIncrementExpression;
import com.eagle.programmar.AWK.Expressions.AWK_RegularExpression;
import com.eagle.programmar.AWK.Expressions.AWK_RelationalExpression;
import com.eagle.programmar.AWK.Expressions.AWK_String;
import com.eagle.programmar.AWK.Expressions.AWK_SubscriptExpression;
import com.eagle.programmar.AWK.Expressions.AWK_TrueFalseExpression;
import com.eagle.programmar.AWK.Expressions.AWK_VariableExpression;
import com.eagle.programmar.AWK.Functions.AWK_BuiltinFunction;
import com.eagle.programmar.AWK.Functions.AWK_IntFunction;
import com.eagle.programmar.AWK.Functions.AWK_LengthFunction;
import com.eagle.programmar.AWK.Functions.AWK_SprintfFunction;
import com.eagle.programmar.AWK.Functions.AWK_SubstrFunction;
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
	public @P(110) AWK_CallFunction userFunction;
	public @P(120) AWK_PatternExpression patternExpression;
	public @P(130) AWK_PreIncrementExpression preIncrementExpression;
	public @P(140) AWK_PostIncrementExpression postIncrementExpression;
	public @P(150) AWK_NegativeExpression negativeExpression;
	public @P(160) AWK_LogicalNotExpression notExpression;
	public @P(170) AWK_ConcatenationExpression concateationExpression; // Has to precede vars and strings
	public @P(180) AWK_VariableExpression variableExpression;
	public @P(190) AWK_String string;
	public @P(200) AWK_IntFunction intFunction;
	public @P(210) AWK_LengthFunction lengthFunction;
	public @P(220) AWK_SprintfFunction sprintfFunction;
	public @P(230) AWK_SubstrFunction substrFunction;
	public @P(240) AWK_BuiltinFunction builtinFunction;
	public @P(250) AWK_ParenthesizedExpression parenthesizedExpression;
	public @P(260) AWK_DollarParensExpression dollarParensExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) AWK_SubscriptExpression subscriptExpression;
	public @P(1010) AWK_MultiplicativeExpression multiplicativeExpression;
	public @P(1020) AWK_AdditiveExpression additiveExpression;
	public @P(1030) AWK_RelationalExpression relationalExpression;
	public @P(1040) AWK_RegularExpression regularExpression;
	public @P(1050) AWK_InExpression inExpression;
	public @P(1060) AWK_LogicalAndExpression andExpression;
	public @P(1070) AWK_LogicalOrExpression orExpression;
	public @P(1080) AWK_TrueFalseExpression trueFalseExpression;
	public @P(1090) AWK_AssignmentExpression assignmentExpression;
}
