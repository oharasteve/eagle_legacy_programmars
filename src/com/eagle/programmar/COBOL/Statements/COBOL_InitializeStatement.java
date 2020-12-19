// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_InitializeStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsinit.htm") COBOL_Keyword INITIALIZE = new COBOL_Keyword("INITIALIZE");
	public @S(20) COBOL_Identifier_Reference what;
}
