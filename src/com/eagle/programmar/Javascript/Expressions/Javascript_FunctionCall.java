// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Variable functionName;
	public @S(20) Javascript_ParenthesizedExpression arguments;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Assume System.out.println(expr.exp);
		EagleValue result = interpreter.getEagleValue(arguments.expressions.first());
		System.out.println(result.toString());
	}
}
