// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2022

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_Identifier;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_Function extends TokenSequence
{
	public @S(10) AWK_Keyword FUNCTION = new AWK_Keyword("function");
	public @S(20) AWK_Identifier name;
	public @S(30) AWK_Function_ParameterDefs parameters;
	public @S(40) @OPT TokenList<AWK_Comment> comments;
	public @S(50) AWK_FunctionBody body;
	
	public static class AWK_Function_ParameterDefs extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationLeftParen leftParen;
		public @S(20) @OPT AWK_Comment comment1;
		public @S(30) @OPT @NOSPACE AWK_Identifier param;
		public @S(40) @OPT AWK_Comment comment2;
		public @S(50) @OPT @NOSPACE TokenList<AWK_MoreParameterDefs> moreParams;
		public @S(60) @NOSPACE PunctuationRightParen rightParen;
	}

	public static class AWK_MoreParameterDefs extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationComma comma;
		public @S(20) @OPT AWK_Comment comment;
		public @S(30) AWK_Identifier param;
	}
	
	public static class AWK_FunctionBody extends TokenSequence
	{
		public @S(10) @INDENT PunctuationLeftBrace leftBrace;
		public @S(20) @OPT TokenList<AWK_StatementOrComment> elements;
		public @S(30) @OUTDENT PunctuationRightBrace rightBrace;
	}
}
