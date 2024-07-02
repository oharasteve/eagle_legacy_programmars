// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class TCL_ReturnStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("TclCmd/return.html") TCL_Keyword RETURN = new TCL_Keyword("return");
	public @S(20) TCL_Expression expr;
}
