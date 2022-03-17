// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2015

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_PragmaStatement extends TokenSequence
{
	public @S(10) SQL_Keyword PRAGMA = new SQL_Keyword("PRAGMA");
	public @S(20) TokenList<SQL_PragmaClause> clauses;
	public @S(30) PunctuationSemicolon semicolon;
	
	public static class SQL_PragmaClause extends TokenChooser
	{
		public @CHOICE static class SQL_Pragma_ForeignKeys extends TokenSequence
		{
			public @S(10) SQL_Keyword FOREIGN_KEYS = new SQL_Keyword("FOREIGN_KEYS");
			public @S(20) PunctuationEquals equals;
			public @S(30) SQL_Pragma_ForeignKey foreignKey;
			
			public static class SQL_Pragma_ForeignKey extends TokenChooser
			{
				public @CHOICE SQL_Number number;
				public @CHOICE SQL_Keyword OFF = new SQL_Keyword("OFF");
			}
		}
		
		public @CHOICE static class SQL_Pragma_JournalMode extends TokenSequence
		{
			public @S(10) SQL_Keyword JOURNAL_MODE = new SQL_Keyword("JOURNAL_MODE");
			public @S(20) PunctuationEquals equals;
			public @S(30) SQL_Keyword OFF = new SQL_Keyword("OFF");
		}
	}
}
