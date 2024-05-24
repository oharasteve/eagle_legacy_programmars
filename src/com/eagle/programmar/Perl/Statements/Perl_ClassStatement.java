// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Program;
import com.eagle.programmar.Perl.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Symbols.Perl_Class_Definition;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_ClassStatement extends TokenSequence
{
	public @S(10) @OPT TokenList<Perl_ClassPrefix> prefix;
	public @S(20) Perl_Keyword CLASS = new Perl_Keyword("class");
	public @S(30) Perl_Class_Definition cls;
	public @S(40) @OPT Perl_ClassExtends extend;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) @OPT TokenList<Perl_StatementOrComment> stmts;
	public @S(70) PunctuationRightBrace rightBrace;
	public @S(80) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;

	public static class Perl_ClassPrefix extends TokenSequence
	{
		public @S(10) Perl_KeywordChoice modifier = new Perl_KeywordChoice(Perl_Program.MODIFIERS);
	}

	public static class Perl_ClassExtends extends TokenSequence
	{
		public @S(10) Perl_KeywordChoice EXTENDS = new Perl_KeywordChoice("extends", "implements");
		public @S(20) TokenList<Perl_ExtendsName> extendsName;
		public @S(30) @OPT TokenList<Perl_MoreExtends> moreExtends;

		public static class Perl_ExtendsName extends TokenSequence
		{
			public @S(10) @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public @S(20) Perl_Identifier_Reference id;
		}

		public static class Perl_MoreExtends extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) TokenList<Perl_ExtendsName> extendsName;
		}
	}
}
