// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 2, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.programmar.Haskell.Terminals.Haskell_KeywordChoice;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Haskell_PutStrStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) Haskell_KeywordChoice PUTSTR = new Haskell_KeywordChoice("putStr", "putStrLn");
	public @S(20) @OPT Haskell_Punctuation dollar = new Haskell_Punctuation("$");
	public @S(30) Haskell_Expression expression;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String txt = interpreter.getStrValue(expression);
		System.out.println(txt);
	}
}
