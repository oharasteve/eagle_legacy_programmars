// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Perl_DotExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = dot.toString();

		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, dot, oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());

		String leftStr = leftValue.forceStringValue();
		String rightStr = rightValue.forceStringValue();
		interpreter.pushStr(leftStr + rightStr);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(dot);
		return generator.newAppendExpression(types, leftExpr, rightExpr, this);
	}
}
