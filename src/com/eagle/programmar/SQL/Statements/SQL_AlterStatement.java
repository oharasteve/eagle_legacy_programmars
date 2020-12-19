// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Constraint;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_AlterStatement extends TokenSequence
{
	public @S(10) SQL_Keyword ALTER = new SQL_Keyword("ALTER");
	public @S(20) SQL_AlterWhat what;
	public @S(30) PunctuationSemicolon semicolon;
	
	public static class SQL_AlterWhat extends TokenChooser
	{
		public @CHOICE static class SQL_AlterTable extends TokenSequence
		{
			public @S(10) SQL_Keyword TABLE = new SQL_Keyword("TABLE");
			public @S(20) SQL_Identifier_Reference table;
			public @S(30) SQL_KeywordChoice ADD = new SQL_KeywordChoice("ADD");
			public @S(40) SQL_Constraint constraint;
			public @S(50) @OPT SQL_OnDelete onDelete;
			
			public static class SQL_OnDelete extends TokenSequence
			{
				public @S(10) SQL_Keyword ON = new SQL_Keyword("ON");
				public @S(20) SQL_Keyword DELETE = new SQL_Keyword("DELETE");
				public @S(30) SQL_Keyword CASCADE = new SQL_Keyword("CASCADE");
			}
		}
	}
}
