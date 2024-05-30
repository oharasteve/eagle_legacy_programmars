// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Algol68_LogicalNotExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Algol68_Punctuation logicalNotOperator = new Algol68_Punctuation('~');
	public @S(20) Algol68_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(! value);
	}
}
