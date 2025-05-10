// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Basic_TrigFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Basic_KeywordChoice TRIG = new Basic_KeywordChoice("SIN", "COS");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Basic_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double x = interpreter.getDoubleValue(expr);
		switch (TRIG.getValue().toUpperCase())
		{
		case "SIN":
			interpreter.pushDouble(Math.sin(x));
			return;
		case "COS":
			interpreter.pushDouble(Math.cos(x));
			return;
		default:
			throw new RuntimeException("Unexpected Trig function: " + TRIG.getValue());
		}
	}
}
