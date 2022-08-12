// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 7, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Perl_NextStatement extends TokenSequence
{
	public @S(10) Perl_Keyword NEXT = new Perl_Keyword("next");
	public @S(20) @OPT Perl_NextUnless unless;
	
	public static class Perl_NextUnless extends TokenSequence
	{
		public @S(10) Perl_Keyword UNLESS = new Perl_Keyword("unless");
		public @S(20) PunctuationHyphen minus;
		public @S(30) Perl_Keyword F = new Perl_Keyword("f");
	}
}
