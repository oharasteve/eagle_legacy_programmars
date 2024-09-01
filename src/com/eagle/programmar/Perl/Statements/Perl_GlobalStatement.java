// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Perl_GlobalStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) Perl_KeywordChoice GLOBAL = new Perl_KeywordChoice("global", "local", "our");
	public @S(20) SeparatedList<Perl_Variable, PunctuationComma> vars;
	public @S(30) @OPT Perl_Global_Init init;

	public static class Perl_Global_Init extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Perl_Expression initVal;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init != null && init.isPresent())
		{
			throw new RuntimeException("Need to implement");
		}
	}
}
