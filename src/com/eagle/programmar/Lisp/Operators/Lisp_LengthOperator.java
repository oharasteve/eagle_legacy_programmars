// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 28, 2024

package com.eagle.programmar.Lisp.Operators;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_LengthOperator extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Lisp_Keyword LENGTH = new Lisp_Keyword("LENGTH");
	public @S(30) Lisp_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		interpreter.pushInt(str.length());
	}
}
