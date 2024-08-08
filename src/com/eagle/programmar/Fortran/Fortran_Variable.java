// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.tokens.TokenSequence;

public class Fortran_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) Fortran_Variable_Reference var;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(var.toString());
		interpreter.pushEagleValue(value);
	}
}
