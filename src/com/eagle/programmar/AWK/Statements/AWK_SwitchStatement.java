// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_SwitchStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("#index-switch-statement") AWK_Keyword SWITCH = new AWK_Keyword("switch");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Expression val;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT AWK_Comment comment;
	public @S(60) PunctuationLeftBrace leftBrace;
	public @S(70) @OPT AWK_EndOfLine eoln1;
	public @S(80) @OPT TokenList<AWK_SwitchClause> switchClause;
	public @S(90) PunctuationRightBrace rightBrace;
	public @S(100) @OPT AWK_EndOfLine eoln2;

	public static class AWK_SwitchClause extends TokenChooser
	{
		public @CHOICE AWK_Comment comment;
		public @CHOICE AWK_CaseClause caseClause;
		public @CHOICE AWK_DefaultClause defaultClause;
	}

	public static class AWK_CaseClause extends TokenSequence
	{
		public @S(10) AWK_Keyword CASE = new AWK_Keyword("case");
		public @S(20) AWK_Expression expr;
		public @S(30) PunctuationColon colon;
		public @S(40) @OPT AWK_EndOfLine eoln;
		public @S(50) @OPT TokenList<AWK_StatementOrComment> statements;
	}

	public static class AWK_DefaultClause extends TokenSequence
	{
		public @S(10) AWK_Keyword DEFAULT = new AWK_Keyword("default");
		public @S(20) PunctuationColon colon;
		public @S(30) @OPT TokenList<AWK_StatementOrComment> statements;
	}
}
