// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Basic_Variable;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Basic_AssignmentStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_Variable var;
	public @S(20) PunctuationEquals equals;
	public @S(30) Basic_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		var.assignValue(interpreter, value);	// Might be subscript(s)
	}
}
