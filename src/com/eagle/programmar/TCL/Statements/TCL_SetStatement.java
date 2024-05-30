// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;

public class TCL_SetStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("TclCmd/set.html") TCL_Keyword SET = new TCL_Keyword("set");
	public @S(20) TCL_Variable var;
	public @S(30) TCL_Expression expr;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
				var.id.getValue(), val);
	}
}
