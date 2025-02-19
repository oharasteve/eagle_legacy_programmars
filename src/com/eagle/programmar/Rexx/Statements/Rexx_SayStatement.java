// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Rexx_SayStatement extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) @DOC("instructions-say") Rexx_Keyword SAY = new Rexx_Keyword("SAY");
	public @S(30) Rexx_Expression expr;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String line = interpreter.getStrValue(expr);
		System.out.println(line);
	}
}
