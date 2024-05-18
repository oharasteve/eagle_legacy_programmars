// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Scala_Variable;
import com.eagle.tokens.PrimaryOperator;

public class Scala_VariableExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Scala_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}
}
