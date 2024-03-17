// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 15, 2024

package com.eagle.programmar.Eaglish.Symbols;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;

public class Eaglish_Variable_Definition extends Eaglish_Identifier_Definition implements EagleRunnable
{
	@Override
	public DefinitionType getType()
	{
		return DefinitionType.VARIABLE;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter._symbolTable.findSymbol(this.toString()).forceIntegerValue();
		interpreter.pushInt(val);
	}
}
