// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleRange;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Rust_DotDotExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression lowExpr = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_Punctuation dotDotOperator = new Rust_Punctuation("..");
	public @S(30) @OPT Rust_Expression highExpr = new Rust_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int low = interpreter.getIntValue(lowExpr);
		int high = Integer.MAX_VALUE;
		if (highExpr.isPresent()) high = interpreter.getIntValue(highExpr);
		EagleRange range = new EagleRange(low, high, 1);
		interpreter.pushEagleValue(range);
	}
}
