// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Julia_EqualityExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Julia_Expression left = new Julia_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Julia_PunctuationChoice operator = new Julia_PunctuationChoice("==", "!=");
	public @S(30) Julia_Expression right = new Julia_Expression(this, AllowedPrecedence.HIGHER);

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
		
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (oper)
			{
			case "==":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (oper)
			{
			case "==":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "!=":
				interpreter.pushBool(leftInt != rightInt);
				return;
			}
		}

		throw new RuntimeException("Unexpected relational operator: " + oper);
	}
}
