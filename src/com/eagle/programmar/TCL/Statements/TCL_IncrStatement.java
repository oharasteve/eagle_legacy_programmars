// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class TCL_IncrStatement extends TokenSequence
{
	public @S(10) TCL_KeywordChoice INCR = new TCL_KeywordChoice("incr", "decr");
	public @S(20) TCL_Variable var;
	public @S(30) @OPT TCL_Expression amount;
}
