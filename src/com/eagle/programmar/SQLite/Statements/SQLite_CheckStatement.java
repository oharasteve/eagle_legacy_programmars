// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.programmar.SQLite.Terminals.SQLite_MultilineString;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationStar;

public class SQLite_CheckStatement extends TokenSequence
{
	public @S(10) SQL_Keyword CHECK = new SQL_Keyword(".check");
	public @S(20) @OPT SQL_KeywordChoice GLOB = new SQL_KeywordChoice("-glob", "--glob");
	public @S(30) SQLite_CheckWhat what;
	
	public static class SQLite_CheckWhat extends TokenChooser
	{
		public @CHOICE SQL_Literal XXliteral;
		public @CHOICE SQL_Number XXnumber;
		public @CHOICE SQL_Keyword XXNULL = new SQL_Keyword("NULL");
		public @CHOICE SQLite_MultilineString XXmultiline;
		
		public @CHOICE static class SQLite_CheckTables extends TokenSequence
		{
			public @S(10) SQL_Identifier_Reference table1;
			public @S(20) PunctuationStar star;
			public @S(30) SQL_Identifier_Reference table2;
		}
	}
}
