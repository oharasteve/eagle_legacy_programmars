// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Statements;

import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_SplitStatement extends TokenSequence
{
	public @S(10) @DOC("#index-split_0028_0029-function-1") AWK_KeywordChoice SPLIT = new AWK_KeywordChoice("split");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_ArgumentList argList;
	public @S(40) PunctuationRightParen rightParen;
}
