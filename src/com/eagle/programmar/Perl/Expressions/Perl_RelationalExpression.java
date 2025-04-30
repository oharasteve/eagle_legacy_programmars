// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Perl_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Perl_PunctuationChoice operator = new Perl_PunctuationChoice("<=", ">=", "<", ">");
	public @S(30) Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);

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
		case "<":
			interpreter.pushBool(leftInt < rightInt);
			return;
		case "<=":
			interpreter.pushBool(leftInt <= rightInt);
			return;
		case ">":
			interpreter.pushBool(leftInt > rightInt);
			return;
		case ">=":
			interpreter.pushBool(leftInt >= rightInt);
			return;
		}
		throw new RuntimeException("Unexpected relational operator: " + oper);
	}
}
