// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_SwitchStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @NEWLINE @DOC("statements.html#14.11") CSharp_Keyword SWITCH = new CSharp_Keyword("switch");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE CSharp_Expression val;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @INDENT PunctuationLeftBrace leftBrace;
	public @S(60) TokenList<CSharp_SwitchCase> caseClause;
	public @S(70) @OUTDENT PunctuationRightBrace rightBrace;

	public static class CSharp_SwitchCase extends TokenSequence
	{
		public @S(10) @NEWLINE CSharp_CaseType caseType;
		public @S(20) PunctuationColon colon;
		public @S(30) @OPT TokenList<CSharp_StatementOrComment> statements;
	}

	public static class CSharp_CaseType extends TokenChooser
	{
		public @CHOICE CSharp_Keyword DEFAULT = new CSharp_Keyword("default");

		public @CHOICE static class CSharp_CaseClause extends TokenSequence
		{
			public @S(10) @BLANKLINE CSharp_Keyword CASE = new CSharp_Keyword("case");
			public @S(20) CSharp_Expression expr;
		}
	}

//	private EagleScope _scope = new EagleScope(this, CSharp_Syntax.isCaseSensitive);
//
//	@Override
//	public EagleScope getScope()
//	{
//		return _scope;
//	}
}
