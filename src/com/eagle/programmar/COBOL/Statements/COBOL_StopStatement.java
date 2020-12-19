// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_StopStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsstop.htm") COBOL_Keyword STOP = new COBOL_Keyword("STOP");
	public @S(20) COBOL_Keyword RUN = new COBOL_Keyword("RUN");
}
