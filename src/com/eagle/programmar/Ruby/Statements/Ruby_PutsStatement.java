// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.programmar.Ruby.Terminals.Ruby_Literal;
import com.eagle.tokens.TokenSequence;

public class Ruby_PutsStatement extends TokenSequence
{
	public @S(10) Ruby_Keyword PUTS = new Ruby_Keyword("puts");
	public @S(20) Ruby_Literal literal;
	public @S(30) Ruby_EOLN eoln;
}
