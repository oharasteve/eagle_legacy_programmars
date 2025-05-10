// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_Variable;
import com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
import com.eagle.programmar.Basic.Terminals.Basic_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Basic_InputStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_KeywordChoice INPUT = new Basic_KeywordChoice("INPUT", "INP");
	public @S(20) @OPT Basic_Literal prompt;
	public @S(30) @OPT PunctuationSemicolon semicolon;
	public @S(40) SeparatedList<Basic_Variable,PunctuationComma> var;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		throw new RuntimeException("Need to implement");
	}
}
