// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
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

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.toString();

		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, operator, oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());

		if (leftValue.isDouble() || rightValue.isDouble())
		{
			double leftDbl = leftValue.forceDoubleValue();
			double rightDbl = rightValue.forceDoubleValue();
			switch (oper)
			{
			case "*":
				interpreter.pushDouble(leftDbl * rightDbl);
				return;
			case "/":
				interpreter.pushDouble(leftDbl / rightDbl);
				return;
			case "%":
				interpreter.pushDouble(leftDbl % rightDbl);
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper)
			{
			case "*":
				interpreter.pushInt(leftInt * rightInt);
				return;
			case "/":
				interpreter.pushInt(leftInt / rightInt);
				return;
			case "%":
				interpreter.pushInt(leftInt % rightInt);
				return;
			}
		}
		throw new RuntimeException("Unexpected multiplicative operator: " + oper);
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

	public static Java_Expression generateMultiplicative(
			Java_Expression leftExpr, MultiplicativeEnum oper,
			Java_Expression rightExpr, AbstractToken source)
	{
		Java_MultiplicativeExpression mulExp = new Java_MultiplicativeExpression();
		mulExp.left = leftExpr;
		mulExp.right = rightExpr;
		switch (oper)
		{
		case TIMES:
			mulExp.operator.setValue("*");
			break;
		case DIVIDE_TRUNCATE:
			mulExp.operator.setValue("/");
			break;
		case DIVIDE_NO_TRUNCATE:
			mulExp.operator.setValue("/");
			Java_Type type = Java_Type.newPrimitiveType("double");
			mulExp.right = Java_CastExpression.newCastExpression(type, rightExpr, source);
			break;
		case REMAINDER:
			mulExp.operator.setValue("%");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper.toString());
		}
		mulExp.setTransformationSource(source);
		return Java_Generator.wrapExpression(mulExp);
	}
}
