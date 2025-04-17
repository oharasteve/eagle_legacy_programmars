// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_AdditiveExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("+", "-");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (operator.toString())
			{
			case "+":
				interpreter.pushStr(leftStr + rightStr);
				break;
			default:
				throw new RuntimeException("Unexpected concatenation operator: " + operator);
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (operator.toString())
			{
			case "+":
				interpreter.pushInt(leftInt + rightInt);
				break;
			case "-":
				interpreter.pushInt(leftInt - rightInt);
				break;
			default:
				throw new RuntimeException("Unexpected additive operator: " + operator);
			}
		}
	}
	
	@Override
	public AbstractExpression transformAdditive(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.toString())
		{
		case "+":
			return generator.newAdditiveExpression(leftExpr, AdditiveEnum.PLUS, rightExpr, this);
		case "-":
			return generator.newAdditiveExpression(leftExpr, AdditiveEnum.MINUS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected additive operator: " + operator);
		}
	}
	
	public static CSharp_AdditiveExpression generateAdditive(
			AbstractExpression leftExpr, AdditiveEnum oper,
			AbstractExpression rightExpr, AbstractToken source)
	{
		CSharp_AdditiveExpression expr = new CSharp_AdditiveExpression();
		expr.left = (CSharp_Expression) leftExpr;
		expr.right = (CSharp_Expression) rightExpr;
		switch (oper)
		{
		case PLUS:
			expr.operator.setValue("+");
			break;
		case MINUS:
			expr.operator.setValue("-");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper);
		}
		expr.setTransformationSource(source);
		return expr;
	}
}
