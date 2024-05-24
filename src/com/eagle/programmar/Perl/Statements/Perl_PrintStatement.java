// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_PrintStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) Perl_KeywordChoice PRINT = new Perl_KeywordChoice("print", "printf");
	public @S(20) SeparatedList<Perl_Expression, PunctuationComma> strings;
	public @S(30) @OPT @CURIOUS("Extra comma") PunctuationComma comma;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (AbstractToken expr : strings._elements)
		{
			String item = interpreter.getStrValue(expr);
			System.out.println(item);
		}
	}
}
