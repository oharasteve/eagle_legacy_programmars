// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Bash_DollarPound extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Bash_PunctuationChoice dollarPound = new Bash_PunctuationChoice("$#", "$?", "$@", "$*");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (dollarPound.getValue())
		{
		case "$?":
			EagleValue val = interpreter.findSymbol("$?");
			int code = val.forceIntegerValue();
			interpreter.pushInt(code);
			break;
		default:
			throw new RuntimeException("Unable to handle variable: " + dollarPound.getValue());
		}
	}
}
