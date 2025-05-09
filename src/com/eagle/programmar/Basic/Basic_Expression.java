// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import com.eagle.programmar.Basic.Expressions.Basic_AdditiveExpression;
import com.eagle.programmar.Basic.Expressions.Basic_ExponentExpression;
import com.eagle.programmar.Basic.Expressions.Basic_MultiplicativeExpression;
import com.eagle.programmar.Basic.Expressions.Basic_NegativeExpression;
import com.eagle.programmar.Basic.Expressions.Basic_ParenthesizedExpression;
import com.eagle.programmar.Basic.Expressions.Basic_RelationalExpression;
import com.eagle.programmar.Basic.Expressions.Basic_SubscriptExpression;
import com.eagle.programmar.Basic.Expressions.Basic_VariableExpression;
import com.eagle.programmar.Basic.Functions.Basic_AbsFunction;
import com.eagle.programmar.Basic.Functions.Basic_ChrFunction;
import com.eagle.programmar.Basic.Terminals.Basic_Literal;
import com.eagle.programmar.Basic.Terminals.Basic_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Basic_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Basic_Expression()
	{
		super(_operators);
	}

	public Basic_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Basic_Number number;
	public @P(20) Basic_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Basic_NegativeExpression negativeExpression;
	public @P(110) Basic_ParenthesizedExpression parenthesizedExpression;
	public @P(120) Basic_AbsFunction absFunction;
	public @P(130) Basic_ChrFunction chrFunction;
	public @P(140) Basic_VariableExpression variableExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Basic_SubscriptExpression subscriptExpression;
	public @P(1010) Basic_ExponentExpression exponentExpression;
	public @P(1020) Basic_MultiplicativeExpression multiplicativeExpression;
	public @P(1030) Basic_AdditiveExpression additiveExpression;
	public @P(1040) Basic_RelationalExpression relationalExpression;
}
