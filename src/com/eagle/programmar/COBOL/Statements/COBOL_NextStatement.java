// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2012

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;

public class COBOL_NextStatement extends COBOL_AbstractStatement
{
	public @S(10) COBOL_Keyword NEXT = new COBOL_Keyword("NEXT");
	public @S(20) COBOL_Keyword SENTENCE = new COBOL_Keyword("SENTENCE");
}
