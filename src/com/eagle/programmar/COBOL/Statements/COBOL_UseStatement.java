// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_UseStatement extends COBOL_AbstractStatement
{
	public @S(10) COBOL_Keyword USE = new COBOL_Keyword("USE");
	public @S(20) COBOL_Keyword BEFORE = new COBOL_Keyword("BEFORE");
	public @S(30) COBOL_Keyword REPORTING = new COBOL_Keyword("REPORTING");
	public @S(40) COBOL_Identifier_Reference id;
}
