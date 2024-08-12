// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Expressions.TCL_AdditiveExpression;
import com.eagle.programmar.TCL.Expressions.TCL_ArrayExpression;
import com.eagle.programmar.TCL.Expressions.TCL_BangExpression;
import com.eagle.programmar.TCL.Expressions.TCL_ConditionalAndExpression;
import com.eagle.programmar.TCL.Expressions.TCL_ConditionalOrExpression;
import com.eagle.programmar.TCL.Expressions.TCL_MultiplicativeExpression;
import com.eagle.programmar.TCL.Expressions.TCL_ParenthesizedExpression;
import com.eagle.programmar.TCL.Expressions.TCL_RelationalExpression;
import com.eagle.programmar.TCL.Expressions.TCL_SignedExpression;
import com.eagle.programmar.TCL.Expressions.TCL_VariableExpression;
import com.eagle.programmar.TCL.Functions.TCL_BracketExpr;
import com.eagle.programmar.TCL.Functions.TCL_BracketLindex;
import com.eagle.programmar.TCL.Functions.TCL_BracketStringFirst;
import com.eagle.programmar.TCL.Functions.TCL_BracketStringLength;
import com.eagle.programmar.TCL.Functions.TCL_BracketFunction;
import com.eagle.programmar.TCL.Terminals.TCL_Literal;
import com.eagle.programmar.TCL.Terminals.TCL_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class TCL_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public TCL_Expression()
	{
		super(_operators);
	}

	public TCL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order.
	// The # determines operator precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) TCL_Number number;
	public @P(20) TCL_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) TCL_VariableExpression variableExpression;
	public @P(110) TCL_SignedExpression signedExpression;
	public @P(120) TCL_BangExpression bangExpression;
	public @P(130) TCL_ParenthesizedExpression parenthesizedExpression;
	public @P(140) TCL_ArrayExpression arrayExpression;
	public @P(150) TCL_BracketExpr exprExpression;
	public @P(160) TCL_BracketLindex lindexExpression;
	public @P(170) TCL_BracketStringFirst stringFirstExpression;
	public @P(180) TCL_BracketStringLength stringLengthExpression;
	public @P(190) TCL_BracketFunction functionCall;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) TCL_MultiplicativeExpression multiplicativeExpression;
	public @P(510) TCL_AdditiveExpression additiveExpression;
	public @P(520) TCL_RelationalExpression relationalExpression;
	public @P(530) TCL_ConditionalAndExpression conditionalAndExpression;
	public @P(540) TCL_ConditionalOrExpression conditionalOrExpression;
}
