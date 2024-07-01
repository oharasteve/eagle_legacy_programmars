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

public class Lisp_RelationalOperator extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Lisp_PunctuationChoice LESS = new Lisp_PunctuationChoice(">", ">=", "=", "/=", "<", "<=");
	public @S(30) TokenList<Lisp_SExpr> exprs;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean first = true;
		int previous = 0;
		for (Lisp_SExpr expr : exprs._elements)
		{
			if (first)
			{
				previous = interpreter.getIntValue(expr);
				first = false;
			}
			else
			{
				int current = interpreter.getIntValue(expr);
				boolean test;
				switch (LESS.getValue())
				{
				case "<":
					test = previous < current;
					break;
				case "<=":
					test = previous <= current;
					break;
				case "=":
					test = previous == current;
					break;
				case "/=":
					test = previous != current;
					break;
				case ">=":
					test = previous >= current;
					break;
				case ">":
					test = previous > current;
					break;
				default:
					throw new RuntimeException("Unable to handle operator: " + LESS.getValue());
				}

				if (!test)
				{
					interpreter.pushBool(false);
					return;
				}
				previous = current;
			}
		}
		interpreter.pushBool(true);
	}
}
