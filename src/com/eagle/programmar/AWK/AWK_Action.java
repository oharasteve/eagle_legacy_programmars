// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class AWK_Action extends TokenSequence
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT TokenList<AWK_StatementOrComment> statements;
	public @S(30) PunctuationRightBrace rightBrace;
	
	public static class AWK_StatementOrComment extends TokenChooser
	{
		public @CHOICE AWK_Statements statements;
		public @CHOICE AWK_Comment comment;
	}
}
