// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_ArgumentList;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_ClassCreationExpression extends PrimaryOperator
{
	public @S(10) Perl_Keyword NEW = new Perl_Keyword("new");
	public @S(20) @OPT Perl_Punctuation dollar = new Perl_Punctuation('$');
	public @S(30) @OPT TokenList<Perl_MoreNamespace> namespace;
	public @S(40) @OPT Perl_ClassCreationParams params;

	public static class Perl_ClassCreationParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT TokenList<Perl_Comment> comments;
		public @S(30) @OPT Perl_ArgumentList argList;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static class Perl_MoreNamespace extends TokenSequence
	{
		public @S(10) @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public @S(20) Perl_Identifier_Reference id;
	}
}
