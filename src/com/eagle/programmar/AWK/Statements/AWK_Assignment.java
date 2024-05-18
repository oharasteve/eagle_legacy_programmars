// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 17, 2024

package com.eagle.programmar.AWK.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class AWK_Assignment extends TokenSequence implements EagleRunnable
{
	public @S(10) AWK_Expression assignment;
	public @S(20) @OPT PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(assignment.getWhich());
	}
}