// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_ArgumentList extends TokenSequence
{
	public @S(10) Perl_Expression arg;
	public @S(20) @OPT TokenList<Perl_Comment> comments1;
	public @S(30) @OPT TokenList<Perl_MoreArguments> moreArgs;
	public @S(40) @OPT PunctuationComma comma;
	public @S(50) @OPT TokenList<Perl_Comment> comments2;
	
	public static class Perl_MoreArguments extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT TokenList<Perl_Comment> comments1;
		public @S(30) Perl_Expression arg;
		public @S(40) @OPT TokenList<Perl_Comment> comments2;
	}
}
