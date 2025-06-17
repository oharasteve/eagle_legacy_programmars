// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.Symbols.TCL_Identifier_Reference;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.tokens.TokenSequence;

public class TCL_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT TCL_Punctuation dollar = new TCL_Punctuation("$");
	public @S(20) TCL_Identifier_Reference id;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(id.toString());
		interpreter.pushEagleValue(value);
	}
}
