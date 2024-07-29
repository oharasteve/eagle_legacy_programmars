// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Powershell_PostDecrementExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Powershell_Variable var;
	public @S(20) Powershell_Punctuation postDecrementOperator = new Powershell_Punctuation("--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter._symbolTable.findSymbol(var.id.getValue());
		int prev = val.forceIntegerValue();
		EagleValue curr = new EagleInteger(prev - 1);
		interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), var.id.getValue(),
				curr);
		interpreter.pushInt(prev);
	}
}
