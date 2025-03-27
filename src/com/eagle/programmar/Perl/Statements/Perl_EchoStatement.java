// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_EchoStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Perl_Keyword ECHO = new Perl_Keyword("echo");
	public @S(20) SeparatedList<Perl_Expression, PunctuationComma> exprs;
}
