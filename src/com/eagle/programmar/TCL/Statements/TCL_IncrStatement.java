// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class TCL_IncrStatement extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) @DOC("TclCmd/incr.html") TCL_Keyword INCR = new TCL_Keyword("incr");
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
		int newV = prev + x;
		EagleInteger val = new EagleInteger(newV);
		interpreter.setSymbol(var, var.id.getValue(), val);
	}
}
