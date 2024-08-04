// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 4, 2024

package com.eagle.programmar.CMD;

import com.eagle.programmar.CMD.Expressions.CMD_BangExpression;
import com.eagle.programmar.CMD.Expressions.CMD_NegativeExpression;
import com.eagle.programmar.CMD.Expressions.CMD_ParenthesizedExpression;
import com.eagle.programmar.CMD.Expressions.CMD_PercentExpression;
import com.eagle.programmar.CMD.Expressions.CMD_VariableExpression;
import com.eagle.programmar.CMD.Terminals.CMD_Filename;
import com.eagle.programmar.CMD.Terminals.CMD_Literal;
import com.eagle.programmar.CMD.Terminals.CMD_Number;
import com.eagle.programmar.CMD.Terminals.CMD_PctPctVariable;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class CMD_BasicExpression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public CMD_BasicExpression()
	{
		super(_operators);
	}

	public CMD_BasicExpression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals -- Identical to CMD_Expression

	public @P(10) CMD_Number number;
	public @P(20) CMD_Literal literal;
	public @P(30) CMD_PctPctVariable pctVariable;
	public @P(40) CMD_Filename filename;

	///////////////////////////////////////////////
	// Primary expressions -- Identical to CMD_Expression

	public @P(100) CMD_ParenthesizedExpression parensExpression;
	public @P(110) CMD_NegativeExpression negativeExpression;
	public @P(120) CMD_VariableExpression variableExpression;
	public @P(130) CMD_PercentExpression percentExpression;
	public @P(140) CMD_BangExpression bangExpression;

	///////////////////////////////////////////////
	// Binary expressions -- only in CMD_Expression

}
