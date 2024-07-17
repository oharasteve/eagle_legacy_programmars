// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_BuiltInFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Scala_KeywordChoice builtin = new Scala_KeywordChoice("equals", "List", "println");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Scala_Expression,PunctuationComma> parameters;
	public @S(40) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = builtin.getValue();
		switch (name)
		{
		case "List":
			EagleArray array = new EagleArray();
			for (int i = 0; i < parameters.getPrimaryCount(); i++)
			{
				EagleValue val = interpreter.getEagleValue(parameters.getPrimaryElement(i));
				array.addValue(val);
			}
			interpreter.pushEagleValue(array);
			return;
		}
		
		throw new RuntimeException("Can't handle BuiltIn method: " + name);
	}
}
