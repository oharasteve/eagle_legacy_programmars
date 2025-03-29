// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_DropStatement extends TokenSequence
{
	public @S(10) SQL_Keyword DROP = new SQL_Keyword("DROP");
	public @S(20) @OPT SQL_Keyword PUBLIC = new SQL_Keyword("PUBLIC");
	public @S(30) SQL_KeywordChoice what = new SQL_KeywordChoice(
			"FUNCTION",
			"PACKAGE",
			"PROCEDURE",
			"ROLE",
			"SYNONYM",
			"TABLE",
			"VIEW"
		);
	public @S(40) @OPT SQL_DropCondition condition;
	public @S(50) SQL_Identifier_Reference id;
	public @S(60) PunctuationSemicolon semicolon;

	public static class SQL_DropCondition extends TokenSequence
	{
		public @S(10) SQL_Keyword IF = new SQL_Keyword("IF");
		public @S(20) SQL_Keyword EXISTS = new SQL_Keyword("EXISTS");
	}
}
