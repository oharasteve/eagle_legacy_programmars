// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 8, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_ChmodStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Perl_Keyword CHMOD = new Perl_Keyword("chmod");
	public @S(20) Perl_Expression codes;
	public @S(30) PunctuationComma comma;
	public @S(40) Perl_Expression file;

}
