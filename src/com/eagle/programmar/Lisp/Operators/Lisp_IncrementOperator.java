// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp.Operators;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_IncrementOperator extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Lisp_PunctuationChoice INCR = new Lisp_PunctuationChoice("1+", "1-");
	public @S(30) Lisp_SExpr expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (INCR.getValue())
		{
		case "1+":
			int sum = interpreter.getIntValue(expr) + 1;
			interpreter.pushInt(sum);
			return;
		case "1-":
			int diff = interpreter.getIntValue(expr) - 1;
			interpreter.pushInt(diff);
			return;
		default:
			throw new RuntimeException("Unable to handle operator: " + INCR.getValue());
		}
	}
}
