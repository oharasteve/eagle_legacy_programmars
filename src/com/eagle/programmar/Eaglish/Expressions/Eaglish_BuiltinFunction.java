// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 4, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Eaglish_BuiltinFunction  extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Eaglish_KeywordChoice func = new Eaglish_KeywordChoice("LENGTH");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Eaglish_Expression, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String which = func.getValue();
		if (interpreter._TRACE) System.err.println("*** Calling " + which + "()");

		// Just get desired arg count
		int paramCount;
		switch (which)
		{
		case "LENGTH":
			paramCount = 1;
			break;
		default:
			throw new RuntimeException("Unexpected function " + which);
		}
		
		// Verify arg count
		int argCount = args.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException("Function " + which + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Call the function
		switch (which)
		{
		case "LENGTH":
			Eaglish_Expression param = args.getPrimaryElement(0);
			String val = interpreter.getStrValue(param);
			interpreter.pushInt(val.length());
			break;
		default:
			throw new RuntimeException("Unexpected function " + which);
		}
	}
}

