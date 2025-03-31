// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class PLI_AndExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PLI_Punctuation andOper = new PLI_Punctuation('&');
	public @S(30) PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		if (leftValue)
		{
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
		}
		else
		{
			// Short circuit, don't bother with RHS
			interpreter.pushBool(false);
		}
	}
}
