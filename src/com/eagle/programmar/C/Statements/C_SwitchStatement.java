// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C.Statements;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_SwitchStatement extends TokenSequence
{
	public @S(10) @DOC("#The-switch-Statement") C_Keyword SWITCH = new C_Keyword("switch");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression val;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT C_Comment comment;
	public @S(60) PunctuationLeftBrace leftBrace;
	public @S(70) @OPT TokenList<C_SwitchClause> switchClause;
	public @S(80) PunctuationRightBrace rightBrace;

	public static class C_SwitchClause extends TokenChooser
	{
		public @CHOICE C_Comment comment;
		public @CHOICE C_CaseClause caseClause;
		public @CHOICE C_DefaultClause defaultClause;
	}

	public static class C_CaseClause extends TokenSequence
	{
		public @S(10) C_Keyword CASE = new C_Keyword("case");
		public @S(20) C_Expression expr;
		public @S(30) PunctuationColon colon;
		public @S(40) @OPT TokenList<C_StatementOrComment> statements;
	}

	public static class C_DefaultClause extends TokenSequence
	{
		public @S(10) C_Keyword DEFAULT = new C_Keyword("default");
		public @S(20) PunctuationColon colon;
		public @S(30) @OPT TokenList<C_StatementOrComment> statements;
	}
}
