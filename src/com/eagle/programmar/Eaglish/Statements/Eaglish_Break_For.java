// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenSequence;

public class Eaglish_Break_For extends TokenSequence implements EagleRunnable
{
	public @S(10) Eaglish_Keyword BREAK_FOR = new Eaglish_Keyword("BREAK_FOR");
	public @S(20) Eaglish_EndOfLine eoln;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		throw new RuntimeException("Uh oh! How to do I implement BREAK?");
	}
}
