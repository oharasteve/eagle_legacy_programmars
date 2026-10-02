// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 1, 2026: ES2020 nullish coalescing, a ?? b

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Javascript_NullishExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_Punctuation nullishOperator = new Javascript_Punctuation("??");
	public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// The left side unless it has no value (null, undefined, or a name the interpreter
		// does not know); then the right side.
		EagleValue leftValue = null;
		try
		{
			leftValue = interpreter.getEagleValue(left);
		}
		catch (RuntimeException ex)
		{
			leftValue = null;
		}
		if (leftValue != null)
		{
			interpreter.pushEagleValue(leftValue);
		}
		else
		{
			interpreter.pushEagleValue(interpreter.getEagleValue(right));
		}
	}
}
