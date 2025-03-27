// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 9, 2014

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_AnswerStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) PLI_Keyword ANSWER = new PLI_Keyword("ANSWER");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) PLI_Expression expr;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT TokenList<PLI_AnswerClause> clauses;
	public @S(60) PunctuationSemicolon semicolon;

	public static class PLI_AnswerClause extends TokenChooser
	{
		public @CHOICE PLI_KeywordChoice XXSKIP = new PLI_KeywordChoice("SKIP", "NOSCAN");

		public @CHOICE static class PLI_AnswerCol extends TokenSequence
		{
			public @S(10) PLI_Keyword COL = new PLI_Keyword("COL");
			public @S(20) PLI_Expression column;
		}
	}
}
