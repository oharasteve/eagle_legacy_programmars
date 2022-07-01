// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.programmar.TCL.Terminals.TCL_Literal;
import com.eagle.tokens.TokenSequence;

public class TCL_PutsStatement extends TokenSequence
{
	public @S(10) TCL_Keyword PUTS = new TCL_Keyword("puts");
	public @S(20) TCL_Literal literal;
}
