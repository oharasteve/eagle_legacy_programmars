// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2024

package com.eagle.programmar.Scala.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_ListFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Scala_Keyword LIST = new Scala_Keyword("List");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Scala_Expression,PunctuationComma> parameters;
	public @S(40) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		for (int i = 0; i < parameters.getPrimaryCount(); i++)
		{
			EagleValue val = interpreter.getEagleValue(parameters.getPrimaryElement(i));
			array.addValue(val);
		}
		interpreter.pushEagleValue(array);
	}
}
