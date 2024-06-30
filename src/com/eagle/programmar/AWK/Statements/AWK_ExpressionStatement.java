// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2024

package com.eagle.programmar.AWK.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class AWK_ExpressionStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) AWK_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}