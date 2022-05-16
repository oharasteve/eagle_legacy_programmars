// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class AWK_ArgumentList extends TokenSequence
{
	public @S(10) AWK_Expression expr;
	public @S(20) @OPT TokenList<AWK_MoreArguments> more;
	
	public static class AWK_MoreArguments extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT AWK_EndOfLine eoln;
		public @S(30) AWK_Expression expr;
	}
}
