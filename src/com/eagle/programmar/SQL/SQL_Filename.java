// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2011

package com.eagle.programmar.SQL;

import com.eagle.programmar.SQL.Terminals.SQL_Identifier;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class SQL_Filename extends TokenSequence
{
	public @S(10) SQL_Identifier file;
	public @S(20) PunctuationPeriod dot;
	public @S(30) SQL_Identifier ext;
}
