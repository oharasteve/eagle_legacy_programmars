// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Powershell_Relational_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Powershell_KeywordChoice operator = new Powershell_KeywordChoice(
			"-ceq", "-cne", "-eq", "-ge", "-gt", "-ieq", "-ine", "-le", "-lt", "-ne");
	public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (operator.toString())
			{
			case "-eq":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "-ne":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (operator.toString())
			{
			case "-eq":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "-ne":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "-lt":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "-le":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case "-gt":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case "-ge":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			}
			throw new RuntimeException("Unexpected relational operator: " + operator);
		}
	}
}
