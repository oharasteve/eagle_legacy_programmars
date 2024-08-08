// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_PostDecrementExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Variable var;
	public @S(20) Javascript_Punctuation postDecrementOperator = new Javascript_Punctuation("--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (var.firstId.getWhich() instanceof Javascript_Identifier_Reference)
		{
			Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) var.firstId.getWhich();
			EagleValue val = interpreter.findSymbol(id.getValue());
			int prev = val.forceIntegerValue();
			EagleValue curr = new EagleInteger(prev - 1);
			interpreter.setSymbol(var, id.getValue(), curr);
			interpreter.pushInt(prev);
		}
	}
}
