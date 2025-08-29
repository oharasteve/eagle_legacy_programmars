// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleRange;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Rust_SubscriptExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression expr = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) Rust_Expression lowExpr = new Rust_Expression();
	public @S(40) @OPT Rust_Punctuation dotDotOperator = new Rust_Punctuation("..");
	public @S(50) @OPT Rust_Expression highExpr = new Rust_Expression();
	public @S(60) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		int lowValue;
		int highValue = Integer.MAX_VALUE;

		EagleValue low = interpreter.getEagleValue(lowExpr);
		if (low.isRange())
		{
			EagleRange range = (EagleRange) low;
			lowValue = range._lowValue;
			if (range._hasHigh)
			{
				highValue = range._highValue;
			}
		}
		else
		{
			lowValue = low.forceIntegerValue();
			if (highExpr != null && highExpr.isPresent())
			{
				highValue = interpreter.getIntValue(highExpr);
			}
		}

		if (value.isArray())
		{
			EagleArray array = (EagleArray) value;
			EagleValue val = array.getValue(lowValue);
			interpreter.pushEagleValue(val);
		}
		else if (value.isString())
		{
			String str = value.forceStringValue();
			if (highValue > str.length()) highValue = str.length();
			String substr = str.substring(lowValue, highValue);
			interpreter.pushStr(substr);
		}
		else
		{
			throw new RuntimeException("Unable to handle " + value.toString());
		}
	}
}
