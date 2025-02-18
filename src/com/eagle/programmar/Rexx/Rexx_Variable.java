// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Rexx_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) Rexx_Identifier_Reference var;
	public @S(20) @OPT Rexx_Subscript subscript;
	public @S(30) @OPT TokenList<Rexx_VariableField> dotFields;

	public static class Rexx_VariableField extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) Rexx_Identifier_Reference var;
		public @S(30) @OPT Rexx_Subscript subscript;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(var.toString());
		interpreter.pushEagleValue(value);
	}
}
