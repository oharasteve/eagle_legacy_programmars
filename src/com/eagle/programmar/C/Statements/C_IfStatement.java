// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_IfStatement extends TokenSequence
{
	public @S(10) @DOC("#The-if-Statement") C_Keyword IF = new C_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression condition;
	public @S(40) @OPT C_Comment comment1;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) @OPT TokenList<C_Comment> comments;
	public @S(70) C_Statement thenStatement;
	public @S(80) @OPT C_IfElseClause elseClause;

	public static class C_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<C_Comment> comment1;
		public @S(20) C_Keyword ELSE = new C_Keyword("else");
		public @S(30) @OPT TokenList<C_Comment> comment2;
		public @S(40) C_Statement elseStatement;
	}
}
