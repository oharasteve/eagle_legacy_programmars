// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_TryStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Perl_Keyword TRY = new Perl_Keyword("try");
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) TokenList<Perl_Statement> statements;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT TokenList<Perl_Comment> comments;
	public @S(60) @OPT TokenList<Perl_CatchBlock> catchBlocks;
	public @S(70) @OPT Perl_FinallyBlock finallyBlock;

	public static class Perl_CatchBlock extends TokenSequence
	{
		public @S(10) Perl_Keyword CATCH = new Perl_Keyword("catch");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public @S(40) Perl_Identifier_Reference ref;
		public @S(50) @OPT Perl_Punctuation dollar = new Perl_Punctuation('$');
		public @S(60) Perl_Variable_Definition id;
		public @S(70) PunctuationRightParen rightParen;
		public @S(80) Perl_Statement catchStatement;
	}

	public static class Perl_FinallyBlock extends TokenSequence
	{
		public @S(10) Perl_Keyword FINALLY = new Perl_Keyword("finally");
		public @S(20) Perl_Statement finallyStatement;
	}
}
