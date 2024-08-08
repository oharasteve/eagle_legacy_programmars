// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 1, 2011

package com.eagle.programmar.Java.Symbols;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;

public class Java_Variable_Definition extends Java_Identifier_Definition implements EagleRunnable
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.VARIABLE;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter.findSymbol(this.toString()).forceIntegerValue();
		interpreter.pushInt(val);
	}
}
