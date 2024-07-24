// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.FSharp.Symbols.FSharp_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;

public class FSharp_Variable extends TokenSequence implements EagleRunnable, AbstractVariable
{
	public @S(10) FSharp_Identifier_Reference id;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter._symbolTable.findSymbol(id.getValue());
		interpreter.pushEagleValue(value);
	}
}
