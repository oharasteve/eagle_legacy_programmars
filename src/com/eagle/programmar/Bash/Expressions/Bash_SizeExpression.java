// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Bash_SizeExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Bash_Punctuation bang = new Bash_Punctuation("#");
	public @S(20) Bash_Variable var;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.findSymbol(var.id.getValue());
		if (val.isString())
		{
			String str = val.forceStringValue();
			interpreter.pushInt(str.length());
			return;
		}
		throw new RuntimeException("Unable to handle " + var);
	}
}
