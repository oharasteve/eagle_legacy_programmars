// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Ada_Variable extends TokenSequence implements EagleRunnable, AbstractVariable
{
	public @S(10) @OPT Ada_Punctuation dollar = new Ada_Punctuation("$");
	public @S(20) SeparatedList<Ada_Identifier_Reference, PunctuationPeriod> vars;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ada_Identifier_Reference which = vars.first();
		EagleValue value = interpreter.findSymbol(which.toString());
		interpreter.pushEagleValue(value);
	}
}
