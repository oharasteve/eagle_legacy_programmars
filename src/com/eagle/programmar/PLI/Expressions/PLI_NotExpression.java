// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class PLI_NotExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PLI_Punctuation notOperator = new PLI_Punctuation('^');
	public @S(20) PLI_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(! value);
	}
}
