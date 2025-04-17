// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_MultiplicativeExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice("*", "/", "%");
	public @S(30) Java_Expression right = new Java_Expression(this, AllowedPrecedence.HIGHER);

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
		}
		throw new RuntimeException("Unexpected multiplicative operator: " + operator);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.toString())
		{
		case "*":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.TIMES, rightExpr, this);
		case "/":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
		case "%":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.REMAINDER, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator);
		}
	}
	
	public static Java_MultiplicativeExpression generateMultiplicative(
			AbstractExpression leftExpr, MultiplicativeEnum oper,
			AbstractExpression rightExpr, AbstractToken source)
	{
		Java_MultiplicativeExpression expr = new Java_MultiplicativeExpression();
		expr.left = (Java_Expression) leftExpr;
		expr.right = (Java_Expression) rightExpr;
		switch(oper)
		{
		case TIMES:
			expr.operator.setValue("*");
			break;
		case DIVIDE_TRUNCATE:
			expr.operator.setValue("/");
			break;
		case DIVIDE_NO_TRUNCATE:
			expr.operator.setValue("/");
			Java_Type type = Java_Type.newPrimitiveType("double");
			Java_CastExpression cast = Java_CastExpression.newCastExpression(type, expr.right);
			expr.right = Java_Generator.wrapExpression(cast);
			break;
		case REMAINDER:
			expr.operator.setValue("%");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper.toString());
		}
		expr.setTransformationSource(source);
		return expr;
	}
}
