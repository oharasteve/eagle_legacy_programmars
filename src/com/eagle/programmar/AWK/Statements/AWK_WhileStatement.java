// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 13, 2017

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.AWK_Action;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_WhileStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("#index-while-statement-1") AWK_Keyword WHILE = new AWK_Keyword("while");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Expression condition;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) AWK_WhileBlock block;

	public static class AWK_WhileBlock extends TokenChooser
	{
		public @CHOICE AWK_Statement stmt;
		public @CHOICE AWK_Action actions;
	}
}
