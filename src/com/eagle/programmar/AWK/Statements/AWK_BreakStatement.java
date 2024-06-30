// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2022

package com.eagle.programmar.AWK.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class AWK_BreakStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @DOC("#Break-Statement") AWK_Keyword BREAK = new AWK_Keyword("break");

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.BREAK;
	}
}
