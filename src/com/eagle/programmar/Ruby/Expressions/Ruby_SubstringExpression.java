// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Ruby_SubstringExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Ruby_Expression expr = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) Ruby_RangeExpression range;
	public @S(40) PunctuationRightBracket rightBracket;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		int len = str.length();
		int sc = interpreter.getIntValue(range.left);
		int ec = interpreter.getIntValue(range.right) + 1;
		if (ec > len) ec = len;
		interpreter.pushStr(str.substring(sc, ec));
	}
}
