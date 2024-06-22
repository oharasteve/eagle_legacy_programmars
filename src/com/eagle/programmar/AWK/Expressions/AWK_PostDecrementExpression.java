// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.AWK.AWK_Variable;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class AWK_PostDecrementExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) AWK_Variable var; // Cannot be just AWK_Expression -- infinite loop
	public @S(20) AWK_Punctuation operator = new AWK_Punctuation("--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter._symbolTable.findSymbol(var.id.getValue());
		int prev = val.forceIntegerValue();
		EagleValue curr = new EagleInteger(prev - 1);
		interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
				var.id.getValue(), curr);
		interpreter.pushInt(prev);
	}
}
