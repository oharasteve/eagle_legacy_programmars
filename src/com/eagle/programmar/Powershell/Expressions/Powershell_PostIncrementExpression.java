// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Powershell_PostIncrementExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Powershell_Variable var;
	public @S(20) Powershell_Punctuation postIncrementOperator = new Powershell_Punctuation("++");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.findSymbol(var.id.getValue());
		int prev = val.forceIntegerValue();
		EagleValue curr = new EagleInteger(prev + 1);
		interpreter.setSymbol(var, var.id.getValue(), curr);
		interpreter.pushInt(prev);
	}
}
