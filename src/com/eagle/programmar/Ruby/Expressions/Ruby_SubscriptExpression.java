// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Ruby_SubscriptExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Ruby_Expression expr = new Ruby_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) Ruby_Expression subscr1;
	public @S(40) PunctuationRightBracket rightBracket;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		if (value.isArray())
		{
			EagleArray array = (EagleArray) value;
			int sub = interpreter.getIntValue(subscr1);
			EagleValue val = array.getValue(sub);
			interpreter.pushEagleValue(val);
			return;
		}
		throw new RuntimeException("Unable to handle subscript");
	}
}
