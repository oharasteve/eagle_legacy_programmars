// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Powershell_AssignmentExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Powershell_Expression var = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Powershell_PunctuationChoice equals = new Powershell_PunctuationChoice(
			"=",
			"*=",
			"/=",
			"%=",
			"+=",
			"-=");
	public @S(30) Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (var.getWhich() instanceof Powershell_VariableExpression)
		{
			Powershell_VariableExpression pVar = (Powershell_VariableExpression) var.getWhich();
			EagleValue value = interpreter.getEagleValue(expr);
			interpreter._symbolTable.setSymbol(pVar.getFileName(), pVar.getStartLine(),
					pVar.getStartChar(), pVar.variable.id.getValue(), value);
		}
	}
}
