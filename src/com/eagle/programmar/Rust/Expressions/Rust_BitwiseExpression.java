// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BitwiseEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_BitwiseExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("&", "|", "^");
	public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "&":
			interpreter.pushInt(leftValue & rightValue);
			break;
		case "|":
			interpreter.pushInt(leftValue | rightValue);
			break;
		case "^":
			interpreter.pushInt(leftValue ^ rightValue);
			break;
		default:
			throw new RuntimeException("Unable to handle " + operator);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		BitwiseEnum oper;
		switch (operator.getValue())
		{
		case "&":
			oper = BitwiseEnum.AND;
			break;
		case "|":
			oper = BitwiseEnum.OR;
			break;
		case "^":
			oper = BitwiseEnum.XOR;
			break;
		default:
			throw new RuntimeException("Unable to handle " + operator);
		}
		return generator.newBitwiseExpression(leftExpr, oper, rightExpr, this);
	}
}
