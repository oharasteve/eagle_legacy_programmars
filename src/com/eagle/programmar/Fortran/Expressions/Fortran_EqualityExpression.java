// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_KeywordChoice;
import com.eagle.programmar.Fortran.Terminals.Fortran_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Fortran_EqualityExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Fortran_Expression left = new Fortran_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Fortran_EqOper oper;
	public @S(30) Fortran_Expression right = new Fortran_Expression(this, AllowedPrecedence.HIGHER);

	public static class Fortran_EqOper extends TokenChooser
	{
		public @CHOICE Fortran_KeywordChoice XXEQ = new Fortran_KeywordChoice(".EQ.", ".NE.");
		public @CHOICE Fortran_PunctuationChoice XXoper = new Fortran_PunctuationChoice("=", "/=");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (oper.getWhich().toString())
		{
		case ".EQ.", "=":
			interpreter.pushBool(leftValue == rightValue);
			return;
		case ".NE.", "/=":
			interpreter.pushBool(leftValue != rightValue);
			return;
		}
		throw new RuntimeException("Unexpected equality operator: " + oper.getWhich());
	}
}
