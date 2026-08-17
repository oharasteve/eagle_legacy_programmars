// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 17, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class SQLite_LimitStatement extends TokenSequence
{
	public @S(10) SQL_Keyword LIMIT = new SQL_Keyword(".limit");
	public @S(20) SQL_LimitWhat what;
	
	public static class SQL_LimitWhat extends TokenChooser
	{
		public @CHOICE static class SQL_LimitLike extends TokenSequence
		{
			public @S(10) SQL_Keyword LIKE = new SQL_Keyword("like_pattern_length");
			public @S(20) SQL_Number number;
		}
	}
}
