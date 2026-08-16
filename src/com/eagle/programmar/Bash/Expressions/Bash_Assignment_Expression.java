// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Bash_Assignment_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Bash_PunctuationChoice equals = new Bash_PunctuationChoice("=");
	public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(left.getWhich() instanceof Bash_VariableExpression))
		{
			throw new RuntimeException("Unexpected assignment expression: " + right.getWhich());
		}
		Bash_VariableExpression varExp = (Bash_VariableExpression) left.getWhich();
		Bash_Variable var = varExp.variable;
//		if (!(right.getWhich() instanceof Bash_Expression))
//		{
//			throw new RuntimeException("Unexpected assignment expression: " + right.getWhich());
//		}
//		Bash_Expression expr = (Bash_Expression) right.getWhich();

		switch (equals.getValue())
		{
		case "=":
			EagleValue val = interpreter.getEagleValue(right);
			interpreter.setSymbol(var, var.id.getValue(), val);
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + equals.getValue());
		}
	}
}
