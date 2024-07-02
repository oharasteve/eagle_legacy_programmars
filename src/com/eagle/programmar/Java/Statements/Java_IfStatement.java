// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Label;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_IfStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @OPT @NEWLINE Java_Label label;
	public @S(20) @DOC("statements.html#14.9") Java_Keyword IF = new Java_Keyword("if");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @NOSPACE Java_Expression condition;
	public @S(50) @OPT TokenList<Java_Comment> comment1;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;
	public @S(70) @OPT TokenList<Java_Comment> comment2;
	public @S(80) Java_Statement thenStatement;
	public @S(90) @OPT Java_IfElseClause elseClause;

	public static class Java_IfElseClause extends TokenSequence
	{
		public @S(10) @OPT TokenList<Java_Comment> comment3;
		public @S(20) @NEWLINE Java_Keyword ELSE = new Java_Keyword("else");
		public @S(30) @OPT Java_Comment comment;
		public @S(40) Java_Statement elseStatement;
	}
}
