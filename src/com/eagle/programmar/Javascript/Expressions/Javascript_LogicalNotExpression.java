// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_LogicalNotExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Punctuation logicalNotOperator = new Javascript_Punctuation('~');
	public @S(20) Javascript_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(! value);
	}
}
