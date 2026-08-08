// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class SQLite_ModeStatement extends TokenSequence
{
	public @S(10) SQL_Keyword MODE = new SQL_Keyword(".mode");
	public @S(20) SQLite_ModeWhich which;
	
	public static class SQLite_ModeWhich extends TokenChooser
	{
		public @CHOICE SQL_KeywordChoice LIST = new SQL_KeywordChoice(
				"list",
				"quote",
				"tty");
		
		public @CHOICE static class SQLite_ModeBox extends TokenSequence
		{
			public @S(10) SQL_Keyword BOX = new SQL_Keyword("box");
			public @S(20) @OPT SQLite_ModeBoxOption option;
			
			public static class SQLite_ModeBoxOption extends TokenChooser
			{
				public @CHOICE SQL_Keyword RESET = new SQL_Keyword("-reset");
				
				public @CHOICE static class SQLite_ModeBoxEscape extends TokenSequence
				{
					public @S(10) SQL_Keyword ESCAPE = new SQL_Keyword("-escape");
					public @S(20) SQL_Keyword OFF = new SQL_Keyword("off");
				}
			}
		}
	}
}
