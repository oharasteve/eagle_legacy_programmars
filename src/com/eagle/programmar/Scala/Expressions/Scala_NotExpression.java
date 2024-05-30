// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Scala_NotExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Scala_Punctuation notOperator = new Scala_Punctuation('!');
	public @S(20) Scala_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(! value);
	}
}
