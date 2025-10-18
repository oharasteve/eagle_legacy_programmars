// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Fortran_StringConcatenation extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Fortran_Punctuation operator = new Fortran_Punctuation("//");
	public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);

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
		String rightStr = rightValue.forceStringValue();
		interpreter.pushStr(leftStr + rightStr);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		Oper2Types types = transformer.findOperator2Metric(operator);
		return generator.newAppendExpression(types, leftExpr, rightExpr, this);
	}
}
