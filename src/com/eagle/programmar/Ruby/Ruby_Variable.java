// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ruby.Expressions.Ruby_RangeExpression;
import com.eagle.programmar.Ruby.Symbols.Ruby_Identifier_Reference;
import com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Ruby_Variable extends TokenSequence implements AbstractVariable, EagleRunnable
{
	public @S(10) @OPT Ruby_Punctuation dollar = new Ruby_Punctuation("$");
	public @S(20) SeparatedList<Ruby_Identifier_Reference, PunctuationPeriod> vars;
	public @S(30) @OPT Ruby_Subscript subscript;

	public static class Ruby_Subscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) Ruby_Expression expr;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ruby_Identifier_Reference which = vars.first();
		EagleValue value = interpreter._symbolTable.findSymbol(which.getValue());

		if (subscript != null && subscript.isPresent())
		{
			if (value instanceof EagleArray)
			{
				int subscr = interpreter.getIntValue(subscript.expr);
				EagleArray val = (EagleArray) value;
				interpreter.pushEagleValue(val.getValue(subscr));
				return;
			}

			if (value instanceof EagleString && subscript.expr.getWhich() instanceof Ruby_RangeExpression)
			{
				Ruby_RangeExpression range = (Ruby_RangeExpression) subscript.expr.getWhich();
				String str = value.forceStringValue();
				int len = str.length();
				int sc = interpreter.getIntValue(range.left);
				int ec = interpreter.getIntValue(range.right) + 1;
				if (ec > len) ec = len;
				interpreter.pushStr(str.substring(sc, ec));
				return;
			}
		}

		interpreter.pushEagleValue(value);
	}
}
