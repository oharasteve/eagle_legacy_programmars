// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 19, 2024

package com.eagle.programmar.CMD.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class CMD_NegativeExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CMD_PunctuationChoice operator = new CMD_PunctuationChoice("-");
	public @S(20) CMD_Expression expr;

	private @SKIP Operator1Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue value = interpreter.getEagleValue(expr);
		String oper = operator.getValue();
		
		if (_metrics == null)
		{
			_metrics = new Operator1Metrics(interpreter._metrics, operator, oper);
		}
		_metrics.operated(value.typeName());

		int val = value.forceIntegerValue();
		switch (oper)
		{
		case "-":
			interpreter.pushInt(-val);
			break;
		default:
			throw new RuntimeException("Unexpected negation operator: " + oper);
		}
	}
}
