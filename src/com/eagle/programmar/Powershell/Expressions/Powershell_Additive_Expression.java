// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.programmar.Powershell.Terminals.Powershell_RealEndOfLine;
import com.eagle.tokens.PrecedenceOperator;

public class Powershell_Additive_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice("+", "-");
	public @S(30) @OPT Powershell_RealEndOfLine eoln;
	public @S(40) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "+" :
			interpreter.pushInt(leftValue + rightValue);
			return;
		case "-" :
			interpreter.pushInt(leftValue - rightValue);
			return;
		}
		throw new RuntimeException("Unexpected additive operator: " + operator);
	}
}
