// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_List extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT TokenList<Lisp_SExpr> exprs;
	public @S(30) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (exprs._elements.isEmpty())
		{
			interpreter.pushBool(false); // () and NIL are identical in Lisp
		}

		Lisp_SExpr first = exprs._elements.get(0);
		switch (first.getWhich().toString().toUpperCase())
		{
		case "+":
			int sum = 0;
			for (int i = 1; i < exprs._elements.size(); i++)
			{
				sum += interpreter.getIntValue(exprs._elements.get(i));
			}
			interpreter.pushInt(sum);
			return;
		case "*":
			int product = 0;
			for (int i = 1; i < exprs._elements.size(); i++)
			{
				product *= interpreter.getIntValue(exprs._elements.get(i));
			}
			interpreter.pushInt(product);
			return;
		case "OR":
			for (int i = 1; i < exprs._elements.size(); i++)
			{
				if (interpreter.getBoolValue(exprs._elements.get(i)))
				{
					interpreter.pushBool(true);
					return;
				}
			}
			interpreter.pushBool(false);
			return;
		case "AND":
			for (int i = 1; i < exprs._elements.size(); i++)
			{
				if (!interpreter.getBoolValue(exprs._elements.get(i)))
				{
					interpreter.pushBool(false);
					return;
				}
			}
			interpreter.pushBool(true);
			return;
		default:
			throw new RuntimeException("Unable to handle function: " + first.getWhich());
		}
	}
}
