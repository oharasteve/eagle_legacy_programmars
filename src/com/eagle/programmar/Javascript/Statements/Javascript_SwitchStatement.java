// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_SwitchStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("js_switch.asp") Javascript_Keyword SWITCH = new Javascript_Keyword("switch");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Javascript_Expression val;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) TokenList<Javascript_CaseClause> caseClause;
	public @S(70) @OPT Javascript_DefaultClause elseClause;
	public @S(80) PunctuationRightBrace rightBrace;

	public static class Javascript_CaseClause extends TokenSequence
	{
		public @S(10) Javascript_Keyword CASE = new Javascript_Keyword("case");
		public @S(20) Javascript_Expression expr;
		public @S(30) PunctuationColon colon;
		public @S(40) @OPT TokenList<Javascript_StatementOrComment> statements;
	}

	public static class Javascript_DefaultClause extends TokenSequence
	{
		public @S(10) Javascript_Keyword DEFAULT = new Javascript_Keyword("default");
		public @S(20) PunctuationColon colon;
		public @S(30) TokenList<Javascript_StatementOrComment> statements;
	}
}
