// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Delphi_Parentheses extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) SeparatedList<Delphi_Expression, PunctuationComma> exprList;
	public @S(30) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (exprList.getPrimaryCount() == 1)
		{
			Delphi_Expression expr = exprList.first();
			interpreter.tryToInterpret(expr);
		}
		else
		{
			EagleArray array = new EagleArray();
			for (int i = 0; i < exprList.getPrimaryCount(); i++)
			{
				EagleValue val = interpreter.getEagleValue(exprList.getPrimaryElement(i));
				array.addValue(val);
			}
			interpreter.pushEagleValue(array);
		}
	}
}
