// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Additive_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("+", "-");
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

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
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
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
	
	public static Python_Additive_Expression generateExpression(AbstractExpression leftExpr, AdditiveEnum oper, AbstractExpression rightExpr, AbstractToken source)
	{
		Python_Additive_Expression expr = new Python_Additive_Expression();
		expr.left = (Python_Expression) leftExpr;
		expr.right = (Python_Expression) rightExpr;
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
