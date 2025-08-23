// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_Parenthesized_Expression extends PrimaryOperator
		implements EagleRunnable
{
	public @S(10) Javascript_ParenthesizedExpression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}
