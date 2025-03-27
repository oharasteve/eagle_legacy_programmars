// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class VB_ExitStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) VB_Keyword EXIT = new VB_Keyword("exit");
	public @S(20) VB_KeywordChoice FOR = new VB_KeywordChoice("do", "for", "function", "sub");

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		switch (FOR.toString())
		{
		case "do":
		case "for":
			return Eagle_Statement_Result.BREAK;
		case "function":
		case "sub":
			return Eagle_Statement_Result.RETURN;
		}
		
		throw new RuntimeException("Cannot handle exit " + FOR + " yet.");
	}
}
