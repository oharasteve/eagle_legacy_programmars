// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Table_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_SelectStatement extends TokenSequence
{
	public @S(10) @DOC("sql_select.asp") SQL_Keyword SELECT = new SQL_Keyword("SELECT");
	public @S(20) SeparatedList<SQL_SelectWhat,PunctuationComma> what;
	public @S(30) @OPT SQL_SelectInto into;
	public @S(40) @OPT SQL_SelectFrom from;
	public @S(50) @OPT SQL_SelectWhere where;
	public @S(60) @OPT SQL_SelectReadOnly readOnly;
	public @S(70) PunctuationSemicolon semicolon;
	
	public static class SQL_SelectWhat extends TokenChooser
	{
		public @LAST SQL_Expression expr;
		
		public @CHOICE static class SQL_SelectExpression extends TokenSequence
		{
			public @S(10) SQL_Expression what;
			public @S(20) @OPT SQL_Keyword AS = new SQL_Keyword("AS");
			public @S(30) SQL_Identifier_Reference name;
		}
	}
	
	public static class SQL_SelectInto extends TokenSequence
	{
		public @S(10) SQL_Keyword INTO = new SQL_Keyword("INTO");
		public @S(20) SQL_Identifier_Reference table;
	}
	
	public static class SQL_SelectFrom extends TokenSequence
	{
		public @S(10) SQL_Keyword FROM = new SQL_Keyword("FROM");
		public @S(20) SQL_Identifier_Reference table;
		public @S(30) @OPT SQL_Table_Definition tableName;
		public @S(40) @OPT TokenList<SQL_SelectFromMore> more;
		
		public static class SQL_SelectFromMore extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) SQL_Identifier_Reference table;
			public @S(30) @OPT SQL_Table_Definition tableName;
		}
	}
	
	public static class SQL_SelectWhere extends TokenSequence
	{
		public @S(10) SQL_Keyword WHERE = new SQL_Keyword("WHERE");
		public @S(20) SQL_Expression condition;
	}
	
	public static class SQL_SelectReadOnly extends TokenSequence
	{
		public @S(10) SQL_Keyword WITH = new SQL_Keyword("WITH");
		public @S(20) SQL_Keyword READ = new SQL_Keyword("READ");
		public @S(30) SQL_Keyword ONLY = new SQL_Keyword("ONLY");
	}
}
