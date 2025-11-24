// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Bash.Conditions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.tokens.PrimaryOperator;

public class Bash_ExpressionCondition extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Bash_Expression expression;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expression);
	}
}
