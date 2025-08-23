// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.LogicalOrEnum;

public class Eaglish_ConditionalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Eaglish_KeywordChoice orOperator = new Eaglish_KeywordChoice("OR", "XOR");
	public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		String oper = orOperator.getValue();
		switch (oper)
		{
		case "OR":
			if (leftValue)
			{
				// Short circuit operation. Don't bother with RHS
				interpreter.pushBool(true);
				return;
			}
			boolean rightVal = interpreter.getBoolValue(right);
			interpreter.pushBool(rightVal);
			return;
		case "XOR":
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue ^ rightValue);
			return;
		default:
			throw new RuntimeException("Unable to handle " + oper + " in Eaglish_ConditionalOrExpression");
		}
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		String oper = orOperator.getValue();
		switch (oper)
		{
		case "OR":
			return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.OR, rightExpr, this);
		case "XOR":
			return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.XOR, rightExpr, this);
		default:
			throw new RuntimeException("Unable to handle " + oper + " in Eaglish_ConditionalOrExpression");
		}
	}
}