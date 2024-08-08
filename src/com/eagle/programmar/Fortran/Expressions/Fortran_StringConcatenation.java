// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Fortran_StringConcatenation extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Fortran_Punctuation operator = new Fortran_Punctuation("//");
	public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftValue = interpreter.getStrValue(left);
		String rightValue = interpreter.getStrValue(right);
		interpreter.pushStr(leftValue + rightValue);
	}
}
