// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Powershell_AssignmentExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Powershell_Expression var = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Powershell_PunctuationChoice equals = new Powershell_PunctuationChoice("=", "*=", "/=", "%=", "+=",
			"-=");
	public @S(30) Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (var.getWhich() instanceof Powershell_VariableExpression)
		{
			Powershell_VariableExpression pVar = (Powershell_VariableExpression) var.getWhich();
			switch (equals.getValue())
			{
			case "=":
				EagleValue value = interpreter.getEagleValue(expr);
				interpreter.setSymbol(pVar, pVar.variable.id.getValue(), value);
				break;
			case "+=":
				int newVal = interpreter.getIntValue(expr);
				EagleValue oldVar = interpreter.findSymbol(pVar.variable.id.toString());
				EagleInteger newValue = new EagleInteger(newVal + oldVar.forceIntegerValue());
				interpreter.setSymbol(var, pVar.variable.id.getValue(), newValue);
				break;
			default:
				throw new RuntimeException("Unexpected assignment operator: " + equals.getValue());
			}
		}
	}
}
