// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Ada_ConditionalAndExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Ada_Keyword andOperator = new Ada_Keyword("and");
	public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		if (leftValue)
		{
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
			return;
		}
		interpreter.pushBool(false);
	}
}