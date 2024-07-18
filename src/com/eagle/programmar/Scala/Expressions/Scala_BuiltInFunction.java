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
	public @S(10) Scala_KeywordChoice builtin = new Scala_KeywordChoice("equals", "length", "List", "println", "startsWith");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Scala_Expression,PunctuationComma> parameters;
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
		case "println":
			String line = interpreter.getStrValue(parameters.first());
			System.out.println(line);
			return;
		}
		
		throw new RuntimeException("Can't handle BuiltIn function: " + name);
	}
	
	// Called directly from Scala_Subfield
	public void processSubfield(EagleInterpreter interpreter, Scala_Expression left)
	{
		switch (builtin.getValue())
		{
		case "equals":
			String leftStr1 = interpreter.getStrValue(left);
			String rightStr1 = interpreter.getStrValue(parameters.first());
			interpreter.pushBool(leftStr1.equals(rightStr1));
			return;
		case "startsWith":
			String leftStr2 = interpreter.getStrValue(left);
			String rightStr2 = interpreter.getStrValue(parameters.first());
			if (parameters.getPrimaryCount() > 1)
			{
				int sc = interpreter.getIntValue(parameters.getPrimaryElement(1));
				interpreter.pushBool(leftStr2.startsWith(rightStr2, sc));
			}
			else
			{
				interpreter.pushBool(leftStr2.startsWith(rightStr2));
			}
			return;
		case "length":
			String leftStr3 = interpreter.getStrValue(left);
			interpreter.pushInt(leftStr3.length());
			return;
		}
	}
}
