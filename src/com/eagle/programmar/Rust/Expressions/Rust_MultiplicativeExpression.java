// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Rust_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("*", "/", "%");
	public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "*":
			interpreter.pushInt(leftValue * rightValue);
			return;
		case "/":
			interpreter.pushInt(leftValue / rightValue);
			return;
		case "%":
			interpreter.pushInt(leftValue % rightValue);
			return;
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator);
		}
	}
}
