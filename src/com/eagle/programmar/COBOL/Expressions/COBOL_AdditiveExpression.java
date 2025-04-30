// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class COBOL_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) COBOL_PunctuationChoice operator = new COBOL_PunctuationChoice("+", "-");
	public @S(30) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

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
			break;
		case "-":
			interpreter.pushInt(leftInt - rightInt);
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper);
		}
	}
}
