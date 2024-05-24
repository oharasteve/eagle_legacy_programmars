// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_BeginStatement extends TokenSequence
{
	public @S(10) SQL_Keyword BEGIN = new SQL_Keyword("BEGIN");
	public @S(20) SQL_BeginWhat what;
	public @S(30) PunctuationSemicolon semicolon;

	public static class SQL_BeginWhat extends TokenChooser
	{
		public @CHOICE static class SQL_BeginEnd extends TokenSequence
		{
			public @S(10) TokenList<SQL_StatementOrComment> statements;
			public @S(20) @OPT SQL_CommitStatement commit;
			public @S(30) SQL_Keyword END = new SQL_Keyword("END");
		}

		public @FIRST static class SQL_BeginTransaction extends TokenSequence
		{
			public @S(10) SQL_Keyword TRANSACTION = new SQL_Keyword("TRANSACTION");
			public @S(20) PunctuationSemicolon semicolon;
			public @S(30) TokenList<SQL_StatementOrComment> statements;
			public @S(40) SQL_Keyword COMMIT = new SQL_Keyword("COMMIT");
		}
	}
}
