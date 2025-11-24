// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 4, 2024

package com.eagle.programmar.CMD.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.CMD_Format;
import com.eagle.programmar.CMD.Terminals.CMD_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.RelationalEnum;

public class CMD_RelationalExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CMD_Expression left = new CMD_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CMD_KeywordChoice operator = new CMD_KeywordChoice("gtr", "leq", "lss", "geq");
	public @S(30) CMD_Expression right = new CMD_Expression(this, AllowedPrecedence.HIGHER);

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

		String leftStr = leftValue.forceStringValue();
		String leftVal = CMD_Format.format(interpreter, leftStr);
		int leftInt = Integer.parseInt(leftVal);
		String rightStr = rightValue.forceStringValue();
		String rightVal = CMD_Format.format(interpreter, rightStr);
		int rightInt = Integer.parseInt(rightVal);
		switch (oper.toLowerCase())
		{
		case "lss":
			interpreter.pushBool(leftInt < rightInt);
			return;
		case "leq":
			interpreter.pushBool(leftInt <= rightInt);
			return;
		case "gtr":
			interpreter.pushBool(leftInt > rightInt);
			return;
		case "geq":
			interpreter.pushBool(leftInt >= rightInt);
			return;
		default:
			throw new RuntimeException("Cannot handle relational operator: " + oper);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);
		String oper = operator.toString();

		switch (oper.toLowerCase())
		{
		case "lss":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.LESS_THAN, rightExpr, this);
		case "leq":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.LESS_EQUALS, rightExpr, this);
		case "gtr":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.GREATER_THAN, rightExpr, this);
		case "geq":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.GREATER_EQUALS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected relational operator: " + oper);
		}
	}
}
