// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.programmar.TCL.Expressions.TCL_AdditiveExpression;
import com.eagle.programmar.TCL.Expressions.TCL_ArrayExpression;
import com.eagle.programmar.TCL.Expressions.TCL_BuiltIns;
import com.eagle.programmar.TCL.Expressions.TCL_LogicalAndExpression;
import com.eagle.programmar.TCL.Expressions.TCL_LogicalNotExpression;
import com.eagle.programmar.TCL.Expressions.TCL_LogicalOrExpression;
import com.eagle.programmar.TCL.Expressions.TCL_MultiplicativeExpression;
import com.eagle.programmar.TCL.Expressions.TCL_ParenthesizedExpression;
import com.eagle.programmar.TCL.Expressions.TCL_RelationalExpression;
import com.eagle.programmar.TCL.Expressions.TCL_SignedExpression;
import com.eagle.programmar.TCL.Expressions.TCL_VariableExpression;
import com.eagle.programmar.TCL.Functions.TCL_BracketExpr;
import com.eagle.programmar.TCL.Functions.TCL_BracketFunction;
import com.eagle.programmar.TCL.Functions.TCL_BracketLindex;
import com.eagle.programmar.TCL.Functions.TCL_BracketStringCat;
import com.eagle.programmar.TCL.Functions.TCL_BracketStringFirst;
import com.eagle.programmar.TCL.Functions.TCL_BracketStringLength;
import com.eagle.programmar.TCL.Terminals.TCL_Literal;
import com.eagle.programmar.TCL.Terminals.TCL_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class TCL_Expression extends PrecedenceChooser implements AbstractExpression
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

	public @P(100) TCL_SignedExpression signedExpression;
	public @P(110) TCL_LogicalNotExpression bangExpression;
	public @P(120) TCL_BuiltIns builtIn;
	public @P(130) TCL_VariableExpression variableExpression;
	public @P(140) TCL_ParenthesizedExpression parenthesizedExpression;
	public @P(150) TCL_ArrayExpression arrayExpression;
	public @P(160) TCL_BracketExpr exprExpression;
	public @P(170) TCL_BracketLindex lindexExpression;
	public @P(180) TCL_BracketStringFirst stringFirstExpression;
	public @P(190) TCL_BracketStringLength stringLengthExpression;
	public @P(200) TCL_BracketStringCat stringCatExpression;
	public @P(210) TCL_BracketFunction functionCall;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) TCL_MultiplicativeExpression multiplicativeExpression;
	public @P(1010) TCL_AdditiveExpression additiveExpression;
	public @P(1020) TCL_RelationalExpression relationalExpression;
	public @P(1030) TCL_LogicalAndExpression conditionalAndExpression;
	public @P(1040) TCL_LogicalOrExpression conditionalOrExpression;
}
