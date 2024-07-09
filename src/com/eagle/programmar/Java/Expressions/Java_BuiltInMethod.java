// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_BuiltInMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Java_KeywordChoice builtin = new Java_KeywordChoice("equals", "length", "startsWith");
	public @S(40) @OPT Java_BuiltinParams paramList;
	
	public static class Java_BuiltinParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Java_Expression,PunctuationComma> params;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		String name = builtin.getValue();
		int numArgs = paramList.params.getPrimaryCount();
		switch (name)
		{
		case "equals":
			if (numArgs != 1) throw new RuntimeException(name + " requires one argument");
			String other = interpreter.getStrValue(paramList.params.getPrimaryElement(0));
			interpreter.pushBool(leftStr.equals(other));
			return;
		case "length":
			if (numArgs != 0) throw new RuntimeException(name + " requires zero arguments");
			interpreter.pushInt(leftStr.length());
			return;
		case "startsWith":
			if (numArgs < 1 || numArgs > 2) throw new RuntimeException(name + " requires one or two arguments");
			String pattern = interpreter.getStrValue(paramList.params.getPrimaryElement(0));
			if (numArgs == 1)
			{
				interpreter.pushBool(leftStr.startsWith(pattern));
			}
			else
			{
				int sc = interpreter.getIntValue(paramList.params.getPrimaryElement(1));
				interpreter.pushBool(leftStr.startsWith(pattern, sc));
			}
			return;
		}
		
		throw new RuntimeException("Can't handle BuiltIn method: " + name);
	}
}
