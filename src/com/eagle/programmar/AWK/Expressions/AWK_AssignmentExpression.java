// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.IntegerValue;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class AWK_AssignmentExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) AWK_Expression var = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) AWK_PunctuationChoice equals = new AWK_PunctuationChoice("=", "+=", "-=", "*=", "/=");
	public @S(30) AWK_Expression expr = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (! (var.getWhich() instanceof AWK_VariableExpression))
		{
			throw new RuntimeException("Unexpected assignment variable: " + var.getWhich());
		}

		AWK_VariableExpression varExpr = (AWK_VariableExpression) var.getWhich();
		switch (equals.getValue())
		{
		case "=":
			int x = interpreter.getIntValue(expr);
			IntegerValue val = new IntegerValue(x);
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(),
					var.getStartChar(), varExpr.variable.id.getValue(), val);
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + equals.getValue());
		}
	}
}
