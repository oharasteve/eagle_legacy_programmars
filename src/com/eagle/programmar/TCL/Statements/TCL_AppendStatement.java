// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 12, 2026

package com.eagle.programmar.TCL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Variable;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class TCL_AppendStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) @DOC("TclCmd/append.html") TCL_Keyword APPEND = new TCL_Keyword("append");
	public @S(20) TCL_Variable var;
	public @S(30) TCL_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue oldValue = interpreter.findSymbol(var.id.getValue());
		String val = interpreter.getStrValue(expr);
		EagleString newValue = new EagleString(oldValue.forceStringValue() + val);
		interpreter.setSymbol(var, var.id.getValue(), newValue);
	}
}
