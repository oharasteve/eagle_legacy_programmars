// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleHash;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Rexx_Subscript;
import com.eagle.programmar.Rexx.Rexx_Variable;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Rexx_AssignmentStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Rexx_Variable variable;
	public @S(20) PunctuationEquals equals;
	public @S(30) Rexx_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue var = interpreter.findSymbol(variable.var.getValue());
		EagleValue val = interpreter.getEagleValue(expr);

		if (variable.subscript != null && variable.subscript.isPresent())
		{
			EagleHash hash = (EagleHash) var;
			if (hash == null)
			{
				hash = new EagleHash();
				interpreter.setSymbol(variable, variable.var.getValue(), hash);
			}
			Rexx_Subscript sub = variable.subscript;
			String key = interpreter.getStrValue(sub.subscr);
			hash.putValue(key, val);
		}
		else
		{
			interpreter.setSymbol(variable, variable.var.getValue(), val);
		}
	}
}
