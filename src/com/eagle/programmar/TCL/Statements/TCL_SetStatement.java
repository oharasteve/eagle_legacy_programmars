// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Statements;

import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;

public class TCL_SetStatement extends TokenSequence
{
	public @S(10) TCL_Keyword SET = new TCL_Keyword("set");
	public @S(20) TCL_Variable variable;
	public @S(30) TCL_Expression value;
}
