// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp.Operators;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
import com.eagle.programmar.Lisp.Terminals.Lisp_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_RelationalOperator extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Lisp_RelOp relOp;
	public @S(30) TokenList<Lisp_Expression> exprs;
	public @S(40) PunctuationRightParen rightParen;

	public static class Lisp_RelOp extends TokenChooser
	{
		public @CHOICE Lisp_PunctuationChoice XXLESS = new Lisp_PunctuationChoice(">", ">=", "=", "/=", "<", "<=");
		public @CHOICE Lisp_KeywordChoice XXEQUAL = new Lisp_KeywordChoice("EQ", "EQUAL");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue firstValue = interpreter.getEagleValue(exprs._elements.get(0));
		boolean first = true;

		// String compare
		if (firstValue.isString())
		{
			String prevStr = firstValue.forceStringValue();
			for (Lisp_Expression expr : exprs._elements)
			{
				if (first)
				{
					first = false;
				}
				else
				{
					String currStr = interpreter.getStrValue(expr);
					switch (relOp.getWhich().toString())
					{
					case "=", "EQ", "EQUAL":
						if (!currStr.equals(prevStr))
						{
							interpreter.pushBool(false);
							return;
						}
						break;
					default:
						throw new RuntimeException("Unable to handle string operator: " + relOp.getWhich());
					}
					prevStr = currStr;
				}
			}
			interpreter.pushBool(true);
			return;
		}

		// Integer compare
		int previous = firstValue.forceIntegerValue();
		for (Lisp_Expression expr : exprs._elements)
		{
			if (first)
			{
				first = false;
			}
			else
			{
				int current = interpreter.getIntValue(expr);
				boolean test;
				switch (relOp.getWhich().toString())
				{
				case "<":
					test = previous < current;
					break;
				case "<=":
					test = previous <= current;
					break;
				case "=", "EQ", "EQUAL":
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
					throw new RuntimeException("Unable to handle int operator: " + relOp.getWhich());
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
