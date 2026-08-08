// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.SQL.SQL_Statement;
import com.eagle.programmar.SQL.SQL_Syntax;
import com.eagle.programmar.SQL.Terminals.SQL_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class SQLite_Program extends AbstractLanguage
{
	public static final String SQLITE = "SQLite";

	public SQLite_Program()
	{
		super(SQLITE, new SQLite_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<SQLite_StatementOrComment> statements;

	public static class SQLite_StatementOrComment extends TokenChooser
	{
		public @CHOICE SQLite_Statement XXdotStmt;
		public @CHOICE @SYNTAX(SQL_Syntax.class) SQL_Statement XXstatement;
		public @CHOICE SQL_Comment XXcomment;
	}
}
