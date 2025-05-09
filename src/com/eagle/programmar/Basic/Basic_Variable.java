// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Basic.Symbols.Basic_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Basic_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) Basic_Identifier_Reference var;
	public @S(20) @OPT Basic_Subscript subscript;

	public static class Basic_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Basic_Expression expr;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(var.toString());
		interpreter.pushEagleValue(value);
	}
}
