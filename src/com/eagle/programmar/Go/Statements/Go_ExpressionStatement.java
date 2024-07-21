// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 19, 2024

package com.eagle.programmar.Go.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Terminals.Go_Comment;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Go_ExpressionStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @NEWLINE Go_Expression expr;
	public @S(20) @OPT Go_Comment comment;
	public @S(30) Go_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}
