// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_Include extends TokenSequence
{
	public @S(10) Perl_Keyword INCLUDE = new Perl_Keyword("include");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Perl_Literal fileName;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationSemicolon semicolon;
}
