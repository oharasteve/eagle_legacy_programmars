// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.tokens.AbstractToken;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.generate.RelationalEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_RelationalExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_Expression left = new Javascript_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("<", ">", "<=", ">=");
	public @S(30) Javascript_Expression right = new Javascript_Expression(this, AllowedPrecedence.HIGHER);

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);

		switch (operator.toString())
		{
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

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		EagleValue a = rt.eval(left);
		EagleValue b = rt.eval(right);
		String oper = operator.toString();
		if (_metrics == null) _metrics = new Operator2Metrics(interpreter._metrics, operator, oper);
		_metrics.operated(a.getType(), b.getType());
		int c;
		if (a.isString() && b.isString()) c = a.forceStringValue().compareTo(b.forceStringValue());
		else
		{
			double x = JsValues.toNumber(a), y = JsValues.toNumber(b);
			if (Double.isNaN(x) || Double.isNaN(y)) { interpreter.pushBool(false); return; }
			c = Double.compare(x, y);
		}
		switch (oper)
		{
		case "<": interpreter.pushBool(c < 0); return;
		case "<=": interpreter.pushBool(c <= 0); return;
		case ">": interpreter.pushBool(c > 0); return;
		case ">=": interpreter.pushBool(c >= 0); return;
		}
		throw new RuntimeException("Unexpected relational operator: " + oper);
	}
}
