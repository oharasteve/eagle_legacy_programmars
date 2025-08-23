// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.LogicalOrEnum;

public class Ada_ConditionalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Ada_Expression left = new Ada_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Ada_KeywordChoice orOperator = new Ada_KeywordChoice("or", "xor");
	public @S(30) Ada_Expression right = new Ada_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		switch (orOperator.toString())
		{
		case "or":
			if (leftValue)
			{
				interpreter.pushBool(true);
				return;
			}
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
			return;
		case "xor":
			boolean rightVal = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue ^ rightVal);
			return;
		default:
			throw new RuntimeException("Unexpected logical or operator: " + orOperator.toString());
		}
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (orOperator.toString())
		{
		case "or":
			return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.OR, rightExpr, this);
		case "xor":
			return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.XOR, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected logical or operator: " + orOperator.toString());
		}
	}
}
