// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Fortran_BracketExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) SeparatedList<Fortran_Expression, PunctuationComma> expressions;
	public @S(30) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		for (int i = 0; i < expressions.getPrimaryCount(); i++)
		{
			Fortran_Expression expr = expressions.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			array.addValue(val);
		}
		interpreter.pushEagleValue(array);
	}
}
