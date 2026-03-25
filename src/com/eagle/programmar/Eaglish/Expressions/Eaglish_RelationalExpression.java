// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Eaglish_RelationalExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Eaglish_RelationalOperator operator;
	public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);

	public static class Eaglish_RelationalOperator extends TokenChooser
	{
		public @CHOICE Eaglish_PunctuationChoice XXoperSymbol = new Eaglish_PunctuationChoice("=", "<", ">", "<=",
				">=");
		public @CHOICE Eaglish_KeywordChoice XXoperWord = new Eaglish_KeywordChoice("EQUALS", "NOT_EQUALS");
	}

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.getWhich().toString();

		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, operator.getWhich(), oper);
		}
		_metrics.operated(leftValue.getType(), rightValue.getType());

		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = interpreter.getStrValue(left);
			String rightStr = interpreter.getStrValue(right);
			switch (oper.toUpperCase())
			{
			case "=", "EQUALS":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "NOT_EQUALS":
				interpreter.pushBool(!leftStr.equals(rightStr));
				return;
			default:
				throw new RuntimeException("Unable to handle " + oper + " with strings");
			}
		}

		if (leftValue.isInteger() || rightValue.isInteger())
		{
			int leftInt = interpreter.getIntValue(left);
			int rightInt = interpreter.getIntValue(right);
			switch (oper.toUpperCase())
			{
			case "=", "EQUALS":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "NOT_EQUALS":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "<":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "<=":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case ">":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case ">=":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			default:
				throw new RuntimeException("Unable to handle " + oper + " with integers");
			}
		}

		if (leftValue.isBoolean() || rightValue.isBoolean())
		{
			boolean leftBool = interpreter.getBoolValue(left);
			boolean rightBool = interpreter.getBoolValue(right);
			switch (oper.toUpperCase())
			{
			case "=", "EQUALS":
				interpreter.pushBool(leftBool == rightBool);
				return;
			case "NOT_EQUALS":
				interpreter.pushBool(leftBool != rightBool);
				return;
			}
		}

		throw new RuntimeException("Unexpected operator: " + oper);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator.getWhich());
		String oper = operator.getWhich().toString();

		switch (oper.toUpperCase())
		{
		case "=", "EQUALS":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.EQUALS, rightExpr, this);
		case "NOT_EQUALS":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.NOT_EQUALS, rightExpr, this);
		case "<":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.LESS_THAN, rightExpr, this);
		case "<=":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.LESS_EQUALS, rightExpr, this);
		case ">":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.GREATER_THAN, rightExpr, this);
		case ">=":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.GREATER_EQUALS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected relational operator: " + oper);
		}
	}
}