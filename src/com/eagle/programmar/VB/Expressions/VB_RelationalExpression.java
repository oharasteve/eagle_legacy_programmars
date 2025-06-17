// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.RelationalEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class VB_RelationalExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @DOC("operators/comparison-operators") VB_PunctuationChoice operator =
			new VB_PunctuationChoice("=", "<=", ">=", "<>", "<", ">");
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

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
		
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper)
			{
			case "=":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "<>":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else if (leftValue.isDouble() || rightValue.isDouble())
		{
			double leftDbl = leftValue.forceDoubleValue();
			double rightDbl = rightValue.forceDoubleValue();
			switch (oper)
			{
			case "=":
				interpreter.pushBool(leftDbl == rightDbl);
				return;
			case "<>":
				interpreter.pushBool(leftDbl != rightDbl);
				return;
			case "<":
				interpreter.pushBool(leftDbl < rightDbl);
				return;
			case "<=":
				interpreter.pushBool(leftDbl <= rightDbl);
				return;
			case ">":
				interpreter.pushBool(leftDbl > rightDbl);
				return;
			case ">=":
				interpreter.pushBool(leftDbl >= rightDbl);
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper)
			{
			case "=":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "<>":
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

		switch (operator.toString())
		{
		case "=":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.EQUALS, rightExpr, this);
		case "<>":
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
			throw new RuntimeException("Unexpected relational operator: " + operator);
		}
	}
}
