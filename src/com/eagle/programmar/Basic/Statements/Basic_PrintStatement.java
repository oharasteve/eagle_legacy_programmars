// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Basic_PrintStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_KeywordChoice PRINT = new Basic_KeywordChoice("PRINT", "PRI");
	public @S(20) @OPT TokenList<Basic_PrintItem> items;

	public static class Basic_PrintItem extends TokenChooser
	{
		public @CHOICE Basic_Expression XXexpr;
		public @CHOICE PunctuationSemicolon XXsemicolon;
		public @CHOICE PunctuationComma XXcomma;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (items != null && items.size() > 0)
		{
			AbstractToken previous = null;
			for (Basic_PrintItem item : items._elements)
			{
				AbstractToken which = item.getWhich();
				if (which instanceof Basic_Expression)
				{
					Basic_Expression expr = (Basic_Expression) item.getWhich();
					EagleValue val = interpreter.getEagleValue(expr);
					if (val.isInteger())
					{
						int num = val.forceIntegerValue();
						System.out.print(" " + num + " ");
					}
					else if (val.isDouble())
					{
						double number = val.forceDoubleValue();
						System.out.print(" " + number + " ");
					}
					else // string
					{
						String piece = val.forceStringValue();
						System.out.print(piece);
					}
				}
				else if (which instanceof PunctuationSemicolon)
				{
					// No padding
				}
				else if (which instanceof PunctuationComma)
				{
					System.out.print("    ");
				}
				else
				{
					throw new RuntimeException("Unexpected PRINT item: " + which);
				}
				previous = which;
			}

			if (!(previous instanceof PunctuationSemicolon))
			{
				System.out.println();
			}
		}
		else
		{
			System.out.println();
		}
	}
}
