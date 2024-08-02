// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2024

package com.eagle.programmar.CMD.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class CMD_BangExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CMD_Punctuation percent1 = new CMD_Punctuation("!");
	public @S(20) CMD_Expression expr;
	public @S(30) CMD_Punctuation percent2 = new CMD_Punctuation("!");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}
