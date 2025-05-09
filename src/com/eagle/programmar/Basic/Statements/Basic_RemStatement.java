// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Terminals.Basic_Comment;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.tokens.TokenSequence.S;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Basic_RemStatement extends Basic_Comment
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_Keyword REM = new Basic_Keyword("REM");
	public @S(20) Basic_Comment comment;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothin' to do
	}
}
