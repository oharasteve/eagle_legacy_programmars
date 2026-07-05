// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.LogicalOrEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_LogicalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Rust_PunctuationChoice orOperator = new Rust_PunctuationChoice("||", "^");
	public @S(30) Rust_Expression right = new Rust_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		switch (orOperator.toString())
		{
		case "||":
			if (leftValue)
			{
				// Short circuit, don't bother with RHS
				interpreter.pushBool(true);
			}
			else
			{
				boolean rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(rightValue);
			}
			break;
		case "^":
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue ^ rightValue);
			break;
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.OR, rightExpr, this);
	}
	
	public static Rust_Expression generateLogicalOr(Rust_Expression leftExpr,
			LogicalOrEnum oper, Rust_Expression rightExpr, AbstractToken source)
	{
		Rust_LogicalOrExpression or = new Rust_LogicalOrExpression();
		or.left = leftExpr;
		or.right = rightExpr;
		switch (oper)
		{
		case OR:
			or.orOperator.setValue("||");
			break;
		case XOR:
			or.orOperator.setValue("^");
			break;
		default:
			throw new RuntimeException("Unable to handle " + oper);
		}
		or.setTransformationSource(source);
		return Rust_Generator.wrapExpression(or);
	}
}
