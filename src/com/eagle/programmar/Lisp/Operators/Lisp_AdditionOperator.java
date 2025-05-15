// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp.Operators;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_AdditionOperator extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Lisp_PunctuationChoice PLUS = new Lisp_PunctuationChoice("+", "++", "+++", "-");
	public @S(30) TokenList<Lisp_Expression> exprs;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (PLUS.getValue())
		{
		case "+":
			int sum = 0;
			for (Lisp_Expression expr : exprs._elements)
			{
				sum += interpreter.getIntValue(expr);
			}
			interpreter.pushInt(sum);
			return;
		case "-":
			int diff = 0;
			boolean first = true;
			if (exprs._elements.size() == 1)
			{
				// Special case, (- 5) is -5, not 5
				diff = -interpreter.getIntValue(exprs.first());
			}
			else
			{
				for (Lisp_Expression expr : exprs._elements)
				{
					if (first)
					{
						diff = interpreter.getIntValue(expr);
						first = false;
					}
					else
					{
						diff -= interpreter.getIntValue(expr);
					}
				}
			}

			interpreter.pushInt(diff);
			return;
		default:
			throw new RuntimeException("Unable to handle operator: " + PLUS.getValue());
		}
	}
}
