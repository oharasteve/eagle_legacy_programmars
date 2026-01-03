// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleRange;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Rust_RangeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression lowExpression = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_PunctuationChoice dots = new Rust_PunctuationChoice("..", "..=");
	public @S(30) @OPT Rust_Expression highExpression = new Rust_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int lowValue = interpreter.getIntValue(lowExpression);
		int highValue = 0;
		boolean hasHigh = false;
		if (highExpression != null && highExpression.isPresent())
		{
			highValue = interpreter.getIntValue(highExpression);
			hasHigh = true;
			
			if (dots.getValue().equals("..="))
			{
				highValue++;	// Inclusive, 1..5 is 1 to 4; 1..=5 is 1 to 5
			}
		}
		EagleRange range = new EagleRange(lowValue, highValue, hasHigh, 1);
		interpreter.pushEagleValue(range);
	}
}
