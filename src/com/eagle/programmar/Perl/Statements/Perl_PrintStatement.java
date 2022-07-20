// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_PrintStatement extends TokenSequence
{
	public @S(10) Perl_KeywordChoice PRINT = new Perl_KeywordChoice("print", "printf");
	public @S(20) SeparatedList<Perl_Expression,PunctuationComma> strings;
}
