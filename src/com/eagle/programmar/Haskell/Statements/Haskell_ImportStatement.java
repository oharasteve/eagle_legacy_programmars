// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 4, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_Variable;
import com.eagle.programmar.Haskell.Terminals.Haskell_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Haskell_ImportStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @NEWLINE Haskell_Keyword IMPORT = new Haskell_Keyword("import");
	public @S(20) SeparatedList<Haskell_Variable,PunctuationPeriod> from;
	public @S(30) @OPT Haskell_ImportWhat item;
	
	public static class Haskell_ImportWhat extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Haskell_Keyword id;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// No action needed
	}
}
