// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Rust_ExpressionArray extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationAmpersand ampersand;
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) SeparatedList<Rust_Expression, PunctuationComma> exprs;
	public @S(40) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray vals = new EagleArray();
		for (int i = 0; i < exprs.getPrimaryCount(); i++)
		{
			Rust_Expression expr = exprs.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			vals.addValue(val);
			if (interpreter._TRACE) System.err.println("*** array[" + i + "] = " + val.toString());
		}

		interpreter.pushEagleValue(vals);
	}
}
