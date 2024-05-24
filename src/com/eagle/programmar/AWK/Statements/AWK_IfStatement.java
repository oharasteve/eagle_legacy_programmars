// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.AWK_Action;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_IfStatement extends TokenSequence
{
	public @S(10) @DOC("#index-if-statement-2") AWK_Keyword IF = new AWK_Keyword("if");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Expression condition;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT AWK_EndOfLine eoln;
	public @S(60) AWK_IfBlock block;
	public @S(70) @OPT AWK_EndOfLine endOfLine;
	public @S(80) @OPT AWK_IfElse ifelse;

	public static class AWK_IfElse extends TokenSequence
	{
		public @S(10) AWK_Keyword ELSE = new AWK_Keyword("else");
		public @S(20) @OPT AWK_EndOfLine eoln;
		public @S(30) AWK_IfBlock block;
	}

	public static class AWK_IfBlock extends TokenChooser
	{
		public @CHOICE AWK_Statement stmt;
		public @CHOICE AWK_Action action;
	}
}
