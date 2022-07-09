// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.TokenSequence;

public class Ruby_BreakStatement extends TokenSequence
{
	public @S(10) Ruby_Keyword BREAK = new Ruby_Keyword("break");
	public @S(20) Ruby_EOLN eoln;
}
