// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 15, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_NamespaceStatement extends TokenSequence
{
	public @S(10) Perl_Keyword NAMESPACE = new Perl_Keyword("namespace");
	public @S(20) @OPT Perl_Identifier_Reference id;
	public @S(30) @OPT TokenList<Perl_MoreSpace> more;
	public @S(40) Perl_NamespaceBody body;

	public static class Perl_NamespaceBody extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon XXsemicolon;
		public @CHOICE Perl_StatementBlock XXblock;
	}

	public static class Perl_MoreSpace extends TokenSequence
	{
		public @S(10) Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public @S(20) Perl_Identifier_Reference id;
	}
}
