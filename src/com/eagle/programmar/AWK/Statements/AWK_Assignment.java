// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 17, 2024

package com.eagle.programmar.AWK.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.IntegerValue;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Variable;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.TokenSequence;

public class AWK_Assignment extends TokenSequence implements EagleRunnable
{
	public @S(10) AWK_Variable var;
	public @S(20) AWK_PunctuationChoice equals = new AWK_PunctuationChoice("=", "+=", "-=", "*=", "/=");
	public @S(30) AWK_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int x = interpreter.getIntValue(expr);
		IntegerValue val = new IntegerValue(x);
		interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
				var.id.getValue(), val);
	}
}