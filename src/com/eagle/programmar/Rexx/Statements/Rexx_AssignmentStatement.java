// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Rexx_Variable;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Rexx_AssignmentStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Rexx_Variable var;
	public @S(20) PunctuationEquals equals;
	public @S(30) Rexx_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		interpreter.setSymbol(var, var.var.getValue(), value);
	}
}
