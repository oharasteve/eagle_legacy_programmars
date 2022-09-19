// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 10, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_Overflow extends TokenSequence
{
	public @S(10) @OPT COBOL_Keyword ON = new COBOL_Keyword("ON");
	public @S(20) COBOL_Keyword OVERFLOW = new COBOL_Keyword("OVERFLOW");
	public @S(30) COBOL_Statement statement;
}
