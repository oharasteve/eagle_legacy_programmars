// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.LogicalOrEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_LogicalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Java_Punctuation orOperator = new Java_Punctuation("||");
	public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
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
	}
	
	@Override
	public AbstractExpression transformAdditive(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.OR, rightExpr, this);
	}
	
	public static Java_LogicalOrExpression generateExpression(AbstractExpression leftExpr, LogicalOrEnum oper, AbstractExpression rightExpr, AbstractToken source)
	{
		Java_LogicalOrExpression expr = new Java_LogicalOrExpression();
		expr.left = (Java_Expression) leftExpr;
		expr.right = (Java_Expression) rightExpr;
		switch (oper)
		{
		case OR:
			break;
		default:
			throw new RuntimeException("Unable to handle " + oper);
		}
		expr.setTransformationSource(source);
		return expr;
	}
}
