// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class TCL_IncrStatement extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) @DOC("TclCmd/incr.html") TCL_KeywordChoice INCR = new TCL_KeywordChoice("incr", "decr");
	public @S(20) TCL_Variable var;
	public @S(30) @OPT TCL_Expression amount;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int x = 1;
		if (amount != null && amount.isPresent())
		{
			x = interpreter.getIntValue(amount);
		}
		int prev = interpreter.getIntValue(var);
		EagleInteger val = new EagleInteger(prev + x);

		interpreter.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
				var.id.getValue(), val);
	}
}
