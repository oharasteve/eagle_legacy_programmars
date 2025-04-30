// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Perl_NegativeExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Perl_PunctuationChoice operator = new Perl_PunctuationChoice("-", "+");
	public @S(20) Perl_Expression expr;

	private @SKIP Operator1Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		String oper = operator.getValue();
		
		if (_metrics == null)
		{
			_metrics = new Operator1Metrics(interpreter._metrics, this, oper);
		}
		_metrics.operated(value.typeName());

		int val = value.forceIntegerValue();
		switch (oper)
		{
		case "+":
			interpreter.pushInt(val);
			break;
		case "-":
			interpreter.pushInt(-val);
			break;
		default:
			throw new RuntimeException("Unexpected negation operator: " + oper);
		}
	}
}
