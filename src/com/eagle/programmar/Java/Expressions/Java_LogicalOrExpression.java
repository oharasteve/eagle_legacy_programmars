// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.LogicalOrEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Logical_Or;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_LogicalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression,
				Eagle_Generate_Logical_Or<Java_Expression>
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
			EagleGenerator generator)
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
	
	@Override
	public Java_Expression generateLogicalOr(Java_Expression leftExpr,
			LogicalOrEnum oper, Java_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.right = rightExpr;
		switch (oper)
		{
		case OR:
			this.orOperator.setValue("||");
			break;
		case XOR:
			this.orOperator.setValue("^");
			break;
		default:
			throw new RuntimeException("Unable to handle " + oper);
		}
		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
