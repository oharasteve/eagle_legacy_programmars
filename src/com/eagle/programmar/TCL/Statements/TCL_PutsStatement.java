// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;

public class TCL_PutsStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("TclCmd/puts.html") TCL_Keyword PUTS = new TCL_Keyword("puts");
	public @S(20) TCL_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(expr);
		System.out.println(val);
	}
}
