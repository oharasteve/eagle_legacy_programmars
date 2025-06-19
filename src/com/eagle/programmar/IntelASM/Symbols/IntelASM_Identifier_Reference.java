// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM.Symbols;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Identifier;
import com.eagle.tokens.ReferenceInterface;

public class IntelASM_Identifier_Reference extends IntelASM_Identifier
		implements ReferenceInterface, EagleRunnable
{
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int val = interpreter.findSymbol(this.getValue()).forceIntegerValue();
		interpreter.pushInt(val);
	}	
}
