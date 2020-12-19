// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_IfStatement extends TokenSequence
{
	public @S(10) @DOC("control-structures.if.php") Perl_Keyword IF = new Perl_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Perl_IfWhat condition;
	public @S(40) @OPT TokenList<Perl_Comment> comments1;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) @OPT TokenList<Perl_Comment> comments2;
	public @S(70) Perl_Statement thenStatement;
	public @S(80) @OPT TokenList<Perl_Comment> comments3;
	public @S(90) @OPT TokenList<Perl_IfElseIfClause> elseIfClauses;
	public @S(100) @OPT TokenList<Perl_Comment> comments4;
	public @S(110) @OPT Perl_IfElseClause elseClause;
	
	public static class Perl_IfWhat extends TokenChooser
	{
		public @LAST Perl_Expression condition;
		
		public @CHOICE static class Perl_IfExists extends TokenSequence
		{
			public @S(10) PunctuationHyphen minus;
			public @S(20) Perl_Keyword F = new Perl_Keyword("f");
			public @S(30) Perl_Expression expr;
		}
	}
	
	public static class Perl_IfElseIfClause extends TokenSequence
	{
		public @S(10) Perl_KeywordChoice ELSEIF = new Perl_KeywordChoice("elseif", "elsif");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Perl_Expression condition;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) @OPT TokenList<Perl_Comment> comments;
		public @S(60) Perl_Statement elseIfStatement;
	}
	
	public static class Perl_IfElseClause extends TokenSequence
	{
		public @S(10) Perl_Keyword ELSE = new Perl_Keyword("else");
		public @S(20) @OPT TokenList<Perl_Comment> comments;
		public @S(30) Perl_Statement elseStatement;
	}
}
