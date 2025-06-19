// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.scope.EagleScope;
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
			EagleValue newValue;
			switch (equals.getValue())
			{
			case "=":
				newValue = interpreter.getEagleValue(expr);
				break;
			case "+=":
				int newVal1 = interpreter.getIntValue(expr);
				EagleValue oldVar1 = interpreter.findSymbol(pVar.variable.id.toString());
				newValue = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
				break;
			case "-=":
				int newVal2 = interpreter.getIntValue(expr);
				EagleValue oldVar2 = interpreter.findSymbol(pVar.variable.id.toString());
				newValue = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
				break;
			default:
				throw new RuntimeException("Unexpected assignment operator: " + equals.getValue());
			}

			if (pVar.variable.scope != null && pVar.variable.scope.isPresent())
			{
				// Was calling SetGlobalSymbol()
				EagleScope saveScope = interpreter._symbolTable.getScope();
				interpreter._symbolTable.setScope(interpreter._lang.getScope());	// Smash it :)
				interpreter.setSymbol(var, pVar.variable.id.getValue(), newValue);
				interpreter._symbolTable.setScope(saveScope);
			}
			else
			{
				interpreter.setSymbol(var, pVar.variable.id.getValue(), newValue);
			}
		}
	}
}
