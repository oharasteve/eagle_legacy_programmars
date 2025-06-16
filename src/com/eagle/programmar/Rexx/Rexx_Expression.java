// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx;

import com.eagle.programmar.Rexx.Expressions.Rexx_AdditiveExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_BuiltIns;
import com.eagle.programmar.Rexx.Expressions.Rexx_CommentExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_ConcatExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_LogicalAndExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_LogicalNotExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_LogicalOrExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_LogicalXorExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_MultiplicativeExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_NegativeExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_ParenthesizedExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_RelationalExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_SubscriptExpression;
import com.eagle.programmar.Rexx.Expressions.Rexx_VariableExpression;
import com.eagle.programmar.Rexx.Functions.Rexx_FunctionCall;
import com.eagle.programmar.Rexx.Functions.Rexx_LengthFunction;
import com.eagle.programmar.Rexx.Functions.Rexx_SubstrFunction;
import com.eagle.programmar.Rexx.Terminals.Rexx_Literal;
import com.eagle.programmar.Rexx.Terminals.Rexx_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Rexx_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Rexx_Expression()
	{
		super(_operators);
	}

	public Rexx_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Rexx_Number number;
	public @P(20) Rexx_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Rexx_SubstrFunction substrFunction;
	public @P(110) Rexx_LengthFunction lengthFunction;
	public @P(120) Rexx_FunctionCall functionCall;
	public @P(130) Rexx_NegativeExpression negativeExpression;
	public @P(140) Rexx_LogicalNotExpression notExpression;
	public @P(150) Rexx_BuiltIns builtIn;
	public @P(160) Rexx_VariableExpression variableExpression;
	public @P(170) Rexx_ParenthesizedExpression parenthesizedExpression;
	public @P(180) Rexx_CommentExpression commentExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Rexx_SubscriptExpression subscriptExpression;
	public @P(1010) Rexx_MultiplicativeExpression multiplicativeExpression;
	public @P(1020) Rexx_AdditiveExpression additiveExpression;
	public @P(1030) Rexx_ConcatExpression concatExpression;
	public @P(1040) Rexx_RelationalExpression relationalExpression;
	public @P(1050) Rexx_LogicalXorExpression inclusiveOrExpression;
	public @P(1060) Rexx_LogicalAndExpression conditionalAndExpression;
	public @P(1070) Rexx_LogicalOrExpression conditionalOrExpression;
}
