// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Powershell_Relational_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Powershell_KeywordChoice operator = new Powershell_KeywordChoice("-ceq", "-cne", "-eq", "-ge", "-gt",
			"-ieq", "-ine", "-le", "-lt", "-ne");
	public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "-eq":
			interpreter.pushBool(leftValue == rightValue);
			return;
		case "-ne":
			interpreter.pushBool(leftValue != rightValue);
			return;
		case "-lt":
			interpreter.pushBool(leftValue < rightValue);
			return;
		case "-le":
			interpreter.pushBool(leftValue <= rightValue);
			return;
		case "-gt":
			interpreter.pushBool(leftValue > rightValue);
			return;
		case "-ge":
			interpreter.pushBool(leftValue >= rightValue);
			return;
		}
		throw new RuntimeException("Unexpected relational operator: " + operator);
	}
}
