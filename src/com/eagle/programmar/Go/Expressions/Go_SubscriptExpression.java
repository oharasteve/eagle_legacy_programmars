// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Go_SubscriptExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Go_Expression expr = new Go_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) @OPT Go_Expression subscr1;
	public @S(40) @OPT PunctuationColon colon;
	public @S(50) @OPT Go_Expression subscr2;
	public @S(60) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(subscr1);
		int ec = interpreter.getIntValue(subscr2);
		interpreter.pushStr(val.substring(sc, ec));
	}
}
