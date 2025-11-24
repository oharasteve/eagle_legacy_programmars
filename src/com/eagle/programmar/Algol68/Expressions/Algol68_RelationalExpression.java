// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.RelationalEnum;

public class Algol68_RelationalExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Algol68_Expression left = new Algol68_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Algol68_RelOp operator;
	public @S(30) Algol68_Expression right = new Algol68_Expression(this, AllowedPrecedence.HIGHER);

	public static class Algol68_RelOp extends TokenChooser
	{
		public @CHOICE Algol68_PunctuationChoice XXsymbol = new Algol68_PunctuationChoice(
				"<", ">", "<=", ">=", "=", "~=", "/=");
		public @CHOICE Algol68_KeywordChoice XXword = new Algol68_KeywordChoice(
				"LT", "LE", "EQ", "NE", "GE", "GT");
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
		_metrics.operated(leftValue.typeName(), rightValue.typeName());

		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper)
			{
			case "=", "EQ":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "~=", "/=", "NE":
				interpreter.pushBool(!leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper.toUpperCase())
			{
			case "=", "EQ":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "~=", "/=", "NE":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "<", "LT":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "<=", "LE":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case ">", "GT":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case ">=", "GE":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			}
		}
		throw new RuntimeException("Unexpected relational operator: " + oper);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);
		String oper = operator.getWhich().toString();

		switch (oper.toUpperCase())
		{
		case "=", "EQ":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.EQUALS, rightExpr, this);
		case "~=", "/=", "NE":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.NOT_EQUALS, rightExpr, this);
		case "<", "LT":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.LESS_THAN, rightExpr, this);
		case "<=", "LE":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.LESS_EQUALS, rightExpr, this);
		case ">", "GT":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.GREATER_THAN, rightExpr, this);
		case ">=", "GE":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.GREATER_EQUALS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected relational operator: " + oper);
		}
	}
}
