// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class TCL_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) TCL_PunctuationChoice operator = new TCL_PunctuationChoice("+", "-");
	public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);

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
		
		int leftInt = leftValue.forceIntegerValue();
		int rightInt = rightValue.forceIntegerValue();
		switch (oper)
		{
		case "+":
			interpreter.pushInt(leftInt + rightInt);
			return;
		case "-":
			interpreter.pushInt(leftInt - rightInt);
			return;
		}
		throw new RuntimeException("Unexpected additive operator: " + oper);
	}
}
