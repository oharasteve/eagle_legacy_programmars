// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.programmar.Javascript.Javascript_Statement.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_TryStatement extends TokenSequence
{
	public @S(10) @DOC("js_try_catch.asp") Javascript_Keyword TRY = new Javascript_Keyword("try");
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) @OPT TokenList<Javascript_StatementOrComment> statements;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT Javascript_CatchBlock catchBlock;
	public @S(60) @OPT Javascript_FinallyBlock finallyBlock;

	public static class Javascript_CatchBlock extends TokenSequence
	{
		public @S(10) Javascript_Keyword CATCH = new Javascript_Keyword("catch");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Javascript_Variable_Definition id;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) Javascript_Statement catchStatement;
	}

	public static class Javascript_FinallyBlock extends TokenSequence
	{
		public @S(10) Javascript_Keyword FINALLY = new Javascript_Keyword("finally");
		public @S(20) Javascript_Statement finallyStatement;
	}
}
