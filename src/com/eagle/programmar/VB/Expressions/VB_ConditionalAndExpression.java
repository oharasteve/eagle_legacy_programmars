// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;

public class VB_ConditionalAndExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) VB_KeywordChoice andOperator = new VB_KeywordChoice("and", "andalso");
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		String oper = andOperator.getValue();
		switch (oper)
		{
		case "andalso":
			if (!leftValue)
			{
				// Short circuit operation. Don't bother with RHS
				interpreter.pushBool(false);
				return;
			}
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
			return;
		case "and":
			boolean rightVal = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue && rightVal);
			return;
		default:
			throw new RuntimeException("Unable to handle " + oper + " in VB_ConditionalAndExpression");
		}
	}
}
