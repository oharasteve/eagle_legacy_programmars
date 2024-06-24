// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.VB.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.tokens.TokenSequence;

public class VB_ExpressionStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @NOSPACE VB_Expression expression;
	public @S(20) @OPT VB_Comment comment;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expression);
	}
}
