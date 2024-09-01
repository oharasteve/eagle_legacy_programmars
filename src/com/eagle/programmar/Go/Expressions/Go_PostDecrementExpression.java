// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
import com.eagle.programmar.Go.Terminals.Go_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Go_PostDecrementExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Go_Variable var;
	public @S(20) Go_Punctuation postDecrementOperator = new Go_Punctuation("--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Go_Identifier_Reference id = var.vars.first();

		EagleValue val = interpreter.findSymbol(id.getValue());
		int prev = val.forceIntegerValue();
		EagleValue curr = new EagleInteger(prev - 1);
		interpreter.setSymbol(var, id.getValue(), curr);
		interpreter.pushInt(prev);
	}
}
