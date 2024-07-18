// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Algol68_Variable extends TokenSequence implements EagleRunnable, AbstractVariable
{
	public @S(10) SeparatedList<Algol68_Identifier_Reference, PunctuationPeriod> vars;
	public @S(20) @OPT Algol68_Subscript subscript;

	public static class Algol68_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Algol68_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Algol68_Identifier_Reference which = vars.first();
		EagleValue value = interpreter._symbolTable.findSymbol(which.toString());
		if (subscript != null && subscript.isPresent() && value.isArray())
		{
			EagleArray array = (EagleArray) value;
			int sub = interpreter.getIntValue(subscript.expr);
			EagleValue val = array.getValue(sub - 1);
			interpreter.pushEagleValue(val);
		}
		else if (subscript != null && subscript.isPresent() && value.isString())
		{
			String str = value.forceStringValue();
			int sub = interpreter.getIntValue(subscript.expr);
			interpreter.pushStr(str.substring(sub-1, sub));
		}
		else
		{
			interpreter.pushEagleValue(value);
		}
	}
}
