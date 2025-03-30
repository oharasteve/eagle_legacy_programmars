// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_IfStatement extends TokenSequence
{
	public @S(10) SQL_Keyword IF1 = new SQL_Keyword("IF");
	public @S(20) SQL_Expression condition;
	public @S(30) SQL_Keyword THEN = new SQL_Keyword("THEN");
	public @S(40) TokenList<SQL_StatementOrComment> statements;
	public @S(50) @OPT SQL_IfElseClause elseClause;
	public @S(60) SQL_Keyword END = new SQL_Keyword("END");
	public @S(70) SQL_Keyword IF2 = new SQL_Keyword("IF");
	public @S(80) PunctuationSemicolon semicolon;
	
	public static class SQL_IfElseClause extends TokenSequence
	{
		public @S(10) SQL_Keyword ELSE = new SQL_Keyword("ELSE");
		public @S(20) TokenList<SQL_StatementOrComment> statements;
	}
}
