// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Terminals.Go_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Go_LogicalNotExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Go_Punctuation logicalNotOperator = new Go_Punctuation('~');
	public @S(20) Go_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(! value);
	}
}
