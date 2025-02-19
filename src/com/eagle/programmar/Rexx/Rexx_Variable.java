// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleHash;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference;
import com.eagle.tokens.TokenSequence;

public class Rexx_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) Rexx_Identifier_Reference var;
	public @S(20) @OPT Rexx_Subscript subscript;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.findSymbol(var.toString());
		if (subscript != null && subscript.isPresent())
		{
			EagleHash hash = (EagleHash) val;
			String key = interpreter.getStrValue(subscript.subscr);
			interpreter.pushEagleValue(hash.getValue(key));
		}
		else
		{
			interpreter.pushEagleValue(val);
		}
	}
}
