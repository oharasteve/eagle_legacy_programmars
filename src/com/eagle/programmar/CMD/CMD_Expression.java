// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD;

import com.eagle.programmar.CMD.Expressions.CMD_AdditiveExpression;
import com.eagle.programmar.CMD.Expressions.CMD_MultiplicativeExpression;
import com.eagle.programmar.CMD.Expressions.CMD_NegativeExpression;
import com.eagle.programmar.CMD.Expressions.CMD_ParenthesizedExpression;
import com.eagle.programmar.CMD.Expressions.CMD_VariableExpression;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class CMD_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public CMD_Expression()
	{
		super(_operators);
	}

	public CMD_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) CMD_Number number;
	public @P(20) CMD_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) CMD_ParenthesizedExpression parensExpression;
	public @P(110) CMD_NegativeExpression negativeExpression;
	public @P(120) CMD_VariableExpression variableExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) CMD_MultiplicativeExpression multiplicativeExpression;
	public @P(510) CMD_AdditiveExpression additiveExpression;
}
