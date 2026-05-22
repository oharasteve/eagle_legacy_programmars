// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_AdditiveExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @DOC("operators/arithmetic-operators") CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice(
			"+", "-");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

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
		_metrics.operated(leftValue.getType(), rightValue.getType());

		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper)
			{
			case "+":
				interpreter.pushStr(leftStr + rightStr);
				break;
			default:
				throw new RuntimeException("Unexpected concatenation operator: " + oper);
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
				throw new RuntimeException("Unexpected additive operator: " + oper);
			}
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);

		switch (operator.toString())
		{
		case "+":
			return generator.newAdditiveExpression(types, leftExpr, AdditiveEnum.PLUS, rightExpr, this);
		case "-":
			return generator.newAdditiveExpression(types, leftExpr, AdditiveEnum.MINUS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected additive operator: " + operator);
		}
	}

	public static CSharp_Expression generateAdditive(Oper2Types types,
			CSharp_Expression leftExpr, AdditiveEnum oper,
			CSharp_Expression rightExpr, AbstractToken source)
	{
		// Don't bother if both are constants, just use the sum (or difference) directly
		AbstractToken whichLeft = leftExpr.getWhich();
		if (whichLeft instanceof CSharp_Number)
		{
			AbstractToken whichRight = rightExpr.getWhich();
			if (whichRight instanceof CSharp_Number)
			{
				CSharp_Number leftNum = (CSharp_Number) whichLeft;
				CSharp_Number rightNum = (CSharp_Number) whichRight;
				try
				{
					int left = Integer.parseInt(leftNum.getValue());
					int right = Integer.parseInt(rightNum.getValue());
					switch (oper)
					{
					case PLUS:
						return CSharp_Generator.wrapExpression(CSharp_Number.createNumber(left + right));
					case MINUS:
						return CSharp_Generator.wrapExpression(CSharp_Number.createNumber(left - right));
					default:
						// Ignore this case
					}
				}
				catch (Exception ex)
				{
					// Ignore errors
				}
			}
		}

		CSharp_AdditiveExpression addExpr = new CSharp_AdditiveExpression();
		addExpr.left = leftExpr;
		addExpr.right = rightExpr;
		switch (oper)
		{
		case PLUS:
			addExpr.operator.setValue("+");
			break;
		case MINUS:
			addExpr.operator.setValue("-");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper);
		}
		addExpr.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(addExpr);
	}
}
