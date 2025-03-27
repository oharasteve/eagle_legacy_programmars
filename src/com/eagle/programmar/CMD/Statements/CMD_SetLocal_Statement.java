// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class CMD_SetLocal_Statement extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) @DOC("setlocal.mspx") CMD_Keyword SETLOCAL = new CMD_Keyword("setlocal");
	public @S(20) @OPT CMD_KeywordChoice setWhat = new CMD_KeywordChoice(
			"ENABLEEXTENSIONS", "ENABLEDELAYEDEXPANSION");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}
}
