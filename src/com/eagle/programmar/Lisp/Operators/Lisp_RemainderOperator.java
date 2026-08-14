// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp.Operators;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_RemainderOperator extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Lisp_KeywordChoice REM = new Lisp_KeywordChoice("MOD", "REM");
	public @S(30) Lisp_Expression leftExpr;
	public @S(40) Lisp_Expression rightExpr;
	public @S(50) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int left = interpreter.getIntValue(leftExpr);
		int right = interpreter.getIntValue(rightExpr);
		switch (REM.getValue())
		{
		case "MOD":
			interpreter.pushInt(Math.floorMod(left, right));
			break;
		case "REM":
			interpreter.pushInt(left % right);
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + REM.getValue());
		}
	}
}
