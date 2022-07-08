// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenSequence;

public class Go_BreakStatement extends TokenSequence
{
	public @S(10) Go_Keyword BREAK = new Go_Keyword("break");
	public @S(20) Go_EOLN eoln;
}
