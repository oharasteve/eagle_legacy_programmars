// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Basic_PrintStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_KeywordChoice PRINT = new Basic_KeywordChoice("PRINT", "PRI");
	public @S(20) @OPT TokenList<Basic_Expression> exprs;
	public @S(30) @OPT PunctuationSemicolon semicolon;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (exprs != null && exprs.size() > 0)
		{
			for (Basic_Expression expr : exprs._elements)
			{
				String piece = interpreter.getStrValue(expr);
				System.out.print(piece);
			}
		}
		
		if (semicolon == null || ! semicolon.isPresent())
		{
			System.out.println();
		}
	}
}
