// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2026

package com.eagle.programmar.Haskell.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Haskell_TupleExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Haskell_Expression first;
	public @S(30) PunctuationComma comma;
	public @S(40) SeparatedList<Haskell_Expression,PunctuationComma> more;
	public @S(50) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		EagleValue val = interpreter.getEagleValue(first);
		array.addValue(val);
		int nValues = more.getPrimaryCount();
		for (int i = 0; i < nValues; i++)
		{
			val = interpreter.getEagleValue(more.getPrimaryElement(i));
			array.addValue(val);
		}
		interpreter.pushEagleValue(array);
	}
}
