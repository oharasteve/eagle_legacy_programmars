// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

package com.eagle.programmar.Eaglish.Symbols;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;

public class Eaglish_Parameter_Definition extends Eaglish_Identifier_Definition implements EagleRunnable
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.FUNCTION;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter.findSymbol(this.toString()).forceIntegerValue();
		interpreter.pushInt(val);
	}
}
