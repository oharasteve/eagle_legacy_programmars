// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;

public class Eaglish_Variable extends TokenSequence implements EagleRunnable, AbstractVariable
{
	public @S(10) Eaglish_VariableIdentifier var;

	public static class Eaglish_VariableIdentifier extends TokenChooser
	{
		public @CHOICE Eaglish_Identifier_Reference XXid;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Eaglish_Identifier_Reference which = (Eaglish_Identifier_Reference) var.getWhich();
		EagleValue value = interpreter.findSymbol(which.toString());
		interpreter.pushEagleValue(value);
	}
}
