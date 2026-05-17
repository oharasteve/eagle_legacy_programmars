// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2026

package com.eagle.programmar.Haskell.Symbols;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Template.Terminals.Template_Identifier;
import com.eagle.tokens.ReferenceInterface;

public class Haskell_Identifier_Reference extends Template_Identifier implements ReferenceInterface, EagleRunnable
{
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(_id.toString());
		interpreter.pushEagleValue(value);
	}
}