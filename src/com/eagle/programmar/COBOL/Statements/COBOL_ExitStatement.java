// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_ExitStatement extends COBOL_AbstractStatement implements EagleRunnableWithResult
{
	public @S(10) @DOC("rlpsexit.htm") COBOL_Keyword EXIT = new COBOL_Keyword("EXIT");
	public @S(20) @OPT COBOL_Keyword PROGRAM = new COBOL_Keyword("PROGRAM");

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.BREAK;
	}
}

