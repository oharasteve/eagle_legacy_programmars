// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_RestOfLine;
import com.eagle.tokens.TokenSequence;

public class CMD_Rem_Statement extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("rem.mspx") CMD_Keyword REM = new CMD_Keyword("rem");
	public @S(20) CMD_RestOfLine comment;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}
}
