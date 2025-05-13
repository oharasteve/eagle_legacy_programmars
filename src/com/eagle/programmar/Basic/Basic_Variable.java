// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Basic.Symbols.Basic_Identifier_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Basic_Variable extends TokenSequence implements EagleRunnable
{
	public @S(10) Basic_Identifier_Reference var;
	public @S(20) @OPT Basic_Subscript subscripts;

	public static class Basic_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Basic_Expression,PunctuationComma> subs;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.findSymbol(var.toString());
		interpreter.pushEagleValue(value);

		EagleValue val = interpreter.findSymbol(var.toString());
		if (subscripts != null && subscripts.isPresent())
		{
			if (subscripts.subs.getPrimaryCount() == 1)
			{
				EagleArray array = (EagleArray) val;
				Basic_Expression sub = subscripts.subs.first();
				int indx = interpreter.getIntValue(sub);
				interpreter.pushEagleValue(array.getValue(indx));
			}
			else
			{
				throw new RuntimeException("Can only single dimensions");
			}
		}
		else
		{
			interpreter.pushEagleValue(val);
		}
	}
}
