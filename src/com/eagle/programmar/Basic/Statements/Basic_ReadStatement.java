// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_Variable;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Basic_ReadStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_Keyword READ = new Basic_Keyword("READ");
	public @S(20) SeparatedList<Basic_Variable,PunctuationComma> var;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		throw new RuntimeException("Need to implement");
	}
}
