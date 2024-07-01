// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 1, 2024

package com.eagle.programmar.Algol68.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Algol68_ExpressionStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Algol68_Expression expr;
	public @S(20) @OPT PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}
