// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class SQL_NegativeExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) SQL_PunctuationChoice operator = new SQL_PunctuationChoice("-", "+");
	public @S(20) @NOSPACE SQL_Expression expr;

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
