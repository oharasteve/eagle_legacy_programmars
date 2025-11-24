// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Bash.Terminals.Bash_Number;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Bash_DollarNumber extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
	public @S(20) Bash_Number number;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = dollar.getValue() + number.toString();
		EagleValue value = interpreter.findSymbol(name);
		interpreter.pushEagleValue(value);
	}
}
