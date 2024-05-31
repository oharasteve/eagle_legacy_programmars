// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp.Operators;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_MultiplicationOperator extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Lisp_PunctuationChoice TIMES = new Lisp_PunctuationChoice("*", "**", "***", "/", "//", "///");
	public @S(30) TokenList<Lisp_SExpr> exprs;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (TIMES.getValue())
		{
		case "*":
			int product = 1;
			for (Lisp_SExpr expr : exprs._elements)
			{
				product *= interpreter.getIntValue(expr);
			}
			interpreter.pushInt(product);
			return;
		case "/":
			int quotient = 0;
			boolean first = true;
			for (Lisp_SExpr expr : exprs._elements)
			{
				if (first)
				{
					quotient = interpreter.getIntValue(expr);
					first = false;
				}
				else
				{
					quotient /= interpreter.getIntValue(expr);
				}
			}
			interpreter.pushInt(quotient);
			return;
		default:
			throw new RuntimeException("Unable to handle operator: " + TIMES.getValue());
		}
	}
}
