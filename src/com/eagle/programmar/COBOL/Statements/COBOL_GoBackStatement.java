// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2015

package com.eagle.programmar.COBOL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_GoBackStatement extends COBOL_AbstractStatement
		implements EagleRunnableWithResult
{
	public @S(10) COBOL_Keyword GOBACK = new COBOL_Keyword("GOBACK");

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.RETURN;
	}
}
