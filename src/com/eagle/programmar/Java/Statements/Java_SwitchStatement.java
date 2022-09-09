// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Syntax;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_SwitchStatement extends TokenSequence implements EagleScopeInterface
{
	public @S(10) @NEWLINE @DOC("statements.html#14.11") Java_Keyword SWITCH = new Java_Keyword("switch");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Java_Expression val;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @INDENT PunctuationLeftBrace leftBrace;
	public @S(60) TokenList<Java_SwitchClause> clause;
	public @S(70) @OUTDENT PunctuationRightBrace rightBrace;
	
	public static class Java_SwitchClause extends TokenChooser
	{
		public @CHOICE Java_Comment comment;
		
		public @CHOICE static class Java_CaseClause extends TokenSequence
		{
			public @S(10) @NEWLINE Java_Keyword CASE = new Java_Keyword("case");
			public @S(20) Java_Expression expr;
			public @S(30) @NOSPACE PunctuationColon colon;
			public @S(40) @OPT TokenList<Java_StatementOrComment> statements;
		}
		
		public @CHOICE static class Java_DefaultClause extends TokenSequence
		{
			public @S(10) @NEWLINE Java_Keyword DEFAULT = new Java_Keyword("default");
			public @S(20) PunctuationColon colon;
			public @S(30) @OPT TokenList<Java_StatementOrComment> statements;
		}
	}
	
	private EagleScope _scope = new EagleScope(this, Java_Syntax.isCaseSensitive);
	
	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
}
