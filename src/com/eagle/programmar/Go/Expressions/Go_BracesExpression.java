// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Go_BracesExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) SeparatedList<Go_Expression, PunctuationComma> expressions;
	public @S(30) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		for (int i = 0; i < expressions.getPrimaryCount(); i++)
		{
			EagleValue val = interpreter.getEagleValue(expressions.getPrimaryElement(i));
			array.addValue(val);
		}
		interpreter.pushEagleValue(array);
	}
}
