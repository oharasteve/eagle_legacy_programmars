// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;

public class TCL_BreakStatement extends TokenSequence
{
	public @S(10) @DOC("TclCmd/break.html") TCL_Keyword BREAK = new TCL_Keyword("break");
}
