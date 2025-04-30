// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Eaglish_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Eaglish_MultiplicationOperator operator;
	public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);

	public static class Eaglish_MultiplicationOperator extends TokenChooser
	{
		public @CHOICE Eaglish_PunctuationChoice XXoperSymbol = new Eaglish_PunctuationChoice("*");
		public @CHOICE Eaglish_KeywordChoice XXoperWord = new Eaglish_KeywordChoice("DIVIDE_TRUNCATE", "REMAINDER");
	}

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.getWhich().toString();
		
		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, this, oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());
		
		int leftInt = leftValue.forceIntegerValue();
		int rightInt = rightValue.forceIntegerValue();
		switch (oper.toUpperCase())
		{
		case "*":
			interpreter.pushInt(leftInt * rightInt);
			return;
		case "DIVIDE_TRUNCATE":
			interpreter.pushInt(leftInt / rightInt);
			return;
		case "REMAINDER":
			interpreter.pushInt(leftInt % rightInt);
			return;
		}
		throw new RuntimeException("Unable to handle: " + oper);
	}
}
