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

public class Lisp_SubseqOperator extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Lisp_Keyword SUBSEQ = new Lisp_Keyword("SUBSEQ");
	public @S(30) Lisp_Expression expr;
	public @S(40) Lisp_Expression scExpr;
	public @S(50) Lisp_Expression ecExpr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(scExpr);
		int ec = interpreter.getIntValue(ecExpr);
		interpreter.pushStr(str.substring(sc, ec));
	}
}
