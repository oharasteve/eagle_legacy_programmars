// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2024

package com.eagle.programmar.CMD;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference;
import com.eagle.programmar.CMD.Terminals.CMD_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class CMD_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT CMD_Punctuation dollar = new CMD_Punctuation("$");
	public @S(20) SeparatedList<CMD_Identifier_Reference, PunctuationPeriod> ids;
	public @S(30) @OPT CMD_Subscript subscript;

	public static class CMD_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) CMD_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = ids.first().getValue();
		if (subscript != null && subscript.isPresent())
		{
			int sub = interpreter.getIntValue(subscript.expr);
			name += "[" + sub + "]";
		}
		EagleValue value = interpreter.findSymbol(name);
		interpreter.pushEagleValue(value);
	}
}
