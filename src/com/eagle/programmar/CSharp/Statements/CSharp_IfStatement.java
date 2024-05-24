// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_IfStatement extends TokenSequence
{
	public @S(10) @NEWLINE @DOC("statements.html#14.9") CSharp_Keyword IF = new CSharp_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression condition;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @OPT TokenList<CSharp_Comment> comments1;
	public @S(60) CSharp_Statement thenStatement;
	public @S(70) @OPT CSharp_IfElseClause elseClause;

	public static class CSharp_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<CSharp_Comment> comments2;
		public @S(20) @NEWLINE CSharp_Keyword ELSE = new CSharp_Keyword("else");
		public @S(30) @OPT TokenList<CSharp_Comment> comments3;
		public @S(40) CSharp_Statement elseStatement;
	}
}
