// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.SQL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class SQL_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) SQL_Expression left = new SQL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) SQL_PunctuationChoice operator = new SQL_PunctuationChoice("+", "-");
	public @S(30) SQL_Expression right = new SQL_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftInt = interpreter.getIntValue(left);
		int rightInt = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "+":
			interpreter.pushInt(leftInt + rightInt);
			break;
		case "-":
			interpreter.pushInt(leftInt - rightInt);
			break;
		default:
			throw new RuntimeException("Unexpected additive operator: " + operator);
		}
	}
}
