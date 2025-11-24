// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.RelationalEnum;

public class Javascript_EqualityExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("!==", "===", "==", "!=");
	public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);
	public @S(40) @OPT Javascript_Comment comment;

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
			case "==", "===":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=", "!==":
				interpreter.pushBool(!leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper)
			{
			case "==", "===":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "!=", "!==":
				interpreter.pushBool(leftInt != rightInt);
				return;
			}
		}

		throw new RuntimeException("Unexpected equality operator: " + oper);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);

		switch (operator.toString())
		{
		case "==", "===":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.EQUALS, rightExpr, this);
		case "!=", "!==":
			return generator.newRelationalExpression(types, leftExpr, RelationalEnum.NOT_EQUALS, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected relational operator: " + operator);
		}
	}
}
