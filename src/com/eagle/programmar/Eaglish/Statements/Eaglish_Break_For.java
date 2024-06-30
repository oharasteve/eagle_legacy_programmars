// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Eaglish_Break_For extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) Eaglish_Keyword BREAK_FOR = new Eaglish_Keyword("BREAK_FOR");
	public @S(20) Eaglish_EndOfLine eoln;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.BREAK;
	}
}
