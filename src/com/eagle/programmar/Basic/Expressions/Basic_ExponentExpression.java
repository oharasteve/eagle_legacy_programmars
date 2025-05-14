// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Terminals.Basic_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Basic_ExponentExpression extends PrecedenceOperator
		implements EagleRunnable
{
	public @S(10) Basic_Expression left = new Basic_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Basic_Punctuation operator = new Basic_Punctuation("^");
	public @S(30) Basic_Expression right = new Basic_Expression(this, AllowedPrecedence.ATLEAST);

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.toString();
		
		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, this, oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());
		
		if (leftValue.isDouble() || rightValue.isDouble())
		{
			double leftDouble = leftValue.forceDoubleValue();
			double rightDouble = rightValue.forceDoubleValue();
			interpreter.pushDouble(Math.pow(leftDouble, rightDouble));
		}
		else
		{
			int leftInteger = leftValue.forceIntegerValue();
			int rightInteger = rightValue.forceIntegerValue();
			interpreter.pushInt((int) Math.pow(leftInteger, rightInteger));
		}
	}
}
