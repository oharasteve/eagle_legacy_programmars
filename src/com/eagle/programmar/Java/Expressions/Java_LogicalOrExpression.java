// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.LogicalOrEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_LogicalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Java_PunctuationChoice orOperator = new Java_PunctuationChoice("||", "^");
	public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		boolean rightValue;
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
				rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(rightValue);
			}
			break;
		case "^":
			rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue ^ rightValue);
			break;
		default:
			throw new RuntimeException("Unable to handle " + orOperator);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		LogicalOrEnum oper;
		switch (orOperator.getValue())
		{
		case "||":
			oper = LogicalOrEnum.OR;
			break;
		case "^":
			oper = LogicalOrEnum.XOR;
			break;
		default:
			throw new RuntimeException("Unable to handle " + orOperator);
		}
		return generator.newLogicalOrExpression(leftExpr, oper, rightExpr, this);
	}

	public static Java_Expression generateLogicalOr(Java_Expression leftExpr,
			LogicalOrEnum oper, Java_Expression rightExpr, AbstractToken source)
	{
		Java_LogicalOrExpression orExpr = new Java_LogicalOrExpression();
		orExpr.left = leftExpr;
		orExpr.right = rightExpr;
		switch (oper)
		{
		case OR:
			orExpr.orOperator.setValue("||");
			break;
		case XOR:
			orExpr.orOperator.setValue("^");
			break;
		default:
			throw new RuntimeException("Unable to handle " + oper);
		}
		orExpr.setTransformationSource(source);
		return Java_Generator.wrapExpression(orExpr);
	}
}
