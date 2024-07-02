// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_OpenStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Perl_Keyword OPEN = new Perl_Keyword("open");
	public @S(20) Perl_OpenWhat what;

	public static class Perl_OpenWhat extends TokenChooser
	{
		public @CHOICE static class Perl_OpenWithDirection extends TokenSequence
		{
			public @S(10) Perl_Variable handle;
			public @S(20) PunctuationComma comma1;
			public @S(30) Perl_Literal direction;
			public @S(40) PunctuationComma comma2;
			public @S(50) Perl_Expression filename;
		}

		public @CHOICE static class Perl_OpenWithoutDirection extends TokenSequence
		{
			public @S(10) @OPT PunctuationLeftParen leftParen;
			public @S(20) Perl_Variable handle;
			public @S(30) PunctuationComma comma2;
			public @S(40) Perl_Expression filename;
			public @S(50) @OPT PunctuationRightParen rightParen;
		}
	}
}
