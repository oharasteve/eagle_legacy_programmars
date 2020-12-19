// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_DeleteStatement extends COBOL_AbstractStatement
{
	public @S(10) COBOL_Keyword DELETE = new COBOL_Keyword("DELETE");
	public @S(20) COBOL_Identifier_Reference file;
	public @S(30) @OPT COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
}
