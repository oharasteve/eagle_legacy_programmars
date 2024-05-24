// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Perl_StatementSuffixModifier extends TokenChooser
{
	public @CHOICE static class Perl_StatementIfSuffix extends TokenSequence
	{
		public @S(10) Perl_KeywordChoice IfUnless = new Perl_KeywordChoice("if", "unless", "while");
		public @S(20) @OPT Perl_MinusF minusF;
		public @S(30) Perl_Expression condition;

		public static class Perl_MinusF extends TokenSequence
		{
			public @S(10) PunctuationHyphen minus;
			public @S(20) Perl_KeywordChoice DF = new Perl_KeywordChoice("d", "f");
		}
	}

	public @CHOICE static class Perl_StatementOrSuffix extends TokenSequence
	{
		public @S(10) Perl_Keyword OR = new Perl_Keyword("or");
		public @S(20) Perl_StatementList statement;
	}

	public @CHOICE static class Perl_StatementBarSuffix extends TokenSequence
	{
		public @S(10) Perl_Punctuation barBar = new Perl_Punctuation("||");
		public @S(20) Perl_StatementList statement;
	}
}
