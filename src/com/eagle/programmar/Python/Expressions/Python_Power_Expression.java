// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Python_Power_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Python_Punctuation stars = new Python_Punctuation("**");
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		interpreter.pushInt((int) Math.round(Math.pow(leftValue, rightValue)));
	}
}