// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Expressions;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_CurrentTimeStamp extends PrimaryOperator
{
	// For some reason, this sometimes has parens after it
	public @S(10) SQL_Keyword TIMESTAMP = new SQL_Keyword("CURRENT_TIMESTAMP");
	public @S(20) @OPT SQL_CurrentTimeStampFunction func;
	
	public static class SQL_CurrentTimeStampFunction extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) PunctuationRightParen rightParen;
	}
}

