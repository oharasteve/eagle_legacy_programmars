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
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_SelectStatement extends TokenSequence
{
	public @S(10) SQL_SelectStmt selectStatement;
	public @S(20) @OPT TokenList<SQL_SelectUnion> more;
	
	public static class SQL_SelectUnion extends TokenSequence
	{
		public @S(10) SQL_Keyword UNION = new SQL_Keyword("UNION");
		public @S(20) @OPT SQL_Keyword ALL = new SQL_Keyword("ALL");
		public @S(30) SQL_SelectStmt selectStatement;
	}
	
	public static class SQL_SelectStmt extends TokenSequence
	{
		public @S(10) @DOC("sql_select.asp") SQL_Keyword SELECT = new SQL_Keyword("SELECT");
		public @S(20) SeparatedList<SQL_SelectWhat,PunctuationComma> what;
		public @S(30) @OPT TokenList<SQL_SelectClause> clauses;
		public @S(40) @OPT PunctuationSemicolon semicolon;
		
		public static class SQL_SelectClause extends TokenChooser
		{
			public @CHOICE static class SQL_SelectInto extends TokenSequence
			{
				public @S(10) SQL_Keyword INTO = new SQL_Keyword("INTO");
				public @S(20) SQL_Identifier_Reference table;
			}
			
			public @CHOICE static class SQL_SelectFrom extends TokenSequence
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
			
			public @CHOICE static class SQL_SelectJoin extends TokenSequence
			{
				public @S(10) @OPT SQL_Keyword INNER = new SQL_Keyword("INNER");
				public @S(20) SQL_Keyword JOIN = new SQL_Keyword("JOIN");
				public @S(30) SQL_Identifier_Reference id;
				public @S(40) SQL_SelectJoinClause clause;
				
				public static class SQL_SelectJoinClause extends TokenChooser
				{
					public @CHOICE static class SQL_SelectJoinOn extends TokenSequence
					{
						public @S(10) SQL_Keyword ON = new SQL_Keyword("ON");
						public @S(20) SQL_Expression condition;
					}
					
					public @CHOICE static class SQL_SelectJoinUsing extends TokenSequence
					{
						public @S(10) SQL_Keyword USING = new SQL_Keyword("USING");
						public @S(20) PunctuationLeftParen leftParen;
						public @S(30) SQL_Identifier_Reference id;
						public @S(40) PunctuationRightParen rightParen;
					}
				}
			}
			
			public @CHOICE static class SQL_SelectWhere extends TokenSequence
			{
				public @S(10) SQL_Keyword WHERE = new SQL_Keyword("WHERE");
				public @S(20) SQL_Expression condition;
			}
			
			public @CHOICE static class SQL_SelectReadOnly extends TokenSequence
			{
				public @S(10) SQL_Keyword WITH = new SQL_Keyword("WITH");
				public @S(20) SQL_Keyword READ = new SQL_Keyword("READ");
				public @S(30) SQL_Keyword ONLY = new SQL_Keyword("ONLY");
			}
			
			public @CHOICE static class SQL_SelectGroup extends TokenSequence
			{
				public @S(10) SQL_Keyword GROUP = new SQL_Keyword("GROUP");
				public @S(20) SQL_Keyword BY = new SQL_Keyword("BY");
				public @S(30) SQL_Identifier_Reference name;
			}
		}
		
		public static class SQL_SelectWhat extends TokenChooser
		{
			public @LAST SQL_Expression expr;
			
			public @CHOICE static class SQL_SelectExpression extends TokenSequence
			{
				public @S(10) SQL_Expression what;
				public @S(20) @OPT SQL_SelectAs as;
				
				public static class SQL_SelectAs extends TokenSequence
				{
					public @S(10) @OPT SQL_Keyword AS = new SQL_Keyword("AS");
					public @S(20) SQL_Identifier_Reference name;
				}
			}
		}
	}
}
