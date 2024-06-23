// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleRange;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Rust_RangeExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Rust_Expression lowExpression;
	public @S(30) Rust_Punctuation dots = new Rust_Punctuation("..");
	public @S(40) @OPT Rust_Expression highExpression;
	public @S(50) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int lowValue = interpreter.getIntValue(lowExpression);
		int highValue = interpreter.getIntValue(highExpression);
		EagleRange range = new EagleRange(lowValue, highValue, 1);
		interpreter.pushEagleValue(range);
	}
}
