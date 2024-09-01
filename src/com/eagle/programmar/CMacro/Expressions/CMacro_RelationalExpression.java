// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.CMacro.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.Terminals.CMacro_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class CMacro_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CMacro_PunctuationChoice operator = new CMacro_PunctuationChoice("<", ">", "<=", ">=");
	public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftVal = interpreter.getIntValue(left);
		int rightVal = interpreter.getIntValue(right);
		String oper = operator.getValue();
		if (oper.equals("<"))
			interpreter.pushBool(leftVal < rightVal);
		else if (oper.equals(">"))
			interpreter.pushBool(leftVal > rightVal);
		else if (oper.equals("<="))
			interpreter.pushBool(leftVal >= rightVal);
		else if (oper.equals(">="))
			interpreter.pushBool(leftVal >= rightVal);
		else
			throw new RuntimeException("Unexpected operator: " + oper);
	}
}
