// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2026

package com.eagle.programmar.SQLite.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.programmar.SQL.Terminals.SQL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class SQLite_ModeStatement extends TokenSequence
{
	public @S(10) SQL_Keyword MODE = new SQL_Keyword(".mode");
	public @S(20) @OPT SQLite_ModeWhich which;
	
	public static class SQLite_ModeWhich extends TokenChooser
	{
		public @CHOICE SQL_KeywordChoice XXLIST = new SQL_KeywordChoice(
				"column",
				// "foo",	// Actually this one is a deliberate error
				"list",
				"qbox",
				"quote",
				"table",
				"tty",
				"-v");
		
		public @CHOICE static class SQLite_ModeNull extends TokenSequence
		{
			public @S(10) SQL_Keyword NULL = new SQL_Keyword("--null");
			public @S(20) SQL_Identifier_Reference id;
		}
		
		public @CHOICE static class SQLite_ModeTTY extends TokenSequence
		{
			public @S(10) SQL_Keyword TTY = new SQL_Keyword("tty");
			public @S(20) TokenList<SQLite_ModeTTYOption> options;
			
			public static class SQLite_ModeTTYOption extends TokenChooser
			{
				public @CHOICE static class SQLine_ModeTTYWrap extends TokenSequence
				{
					public @S(10) SQL_KeywordChoice WRAP = new SQL_KeywordChoice("-wrap", "--wrap");
					public @S(20) SQL_Number number;
				}

				public @CHOICE static class SQLine_ModeTTYLimitsOS extends TokenSequence
				{
					public @S(10) SQL_Keyword LIMITS = new SQL_Keyword("--limits");
					public @S(20) SQL_KeywordChoice OFF = new SQL_KeywordChoice("off");
				}
			}
		}
		
		public @CHOICE static class SQLite_ModeBox extends TokenSequence
		{
			public @S(10) SQL_Keyword BOX = new SQL_Keyword("box");
			public @S(20) @OPT TokenList<SQLite_ModeBoxOption> option;
			
			public static class SQLite_ModeBoxOption extends TokenChooser
			{
				public @CHOICE SQL_Keyword XXRESET = new SQL_Keyword("-reset");
				
				public @CHOICE static class SQLite_ModeBoxEscape extends TokenSequence
				{
					public @S(10) SQL_Keyword ESCAPE = new SQL_Keyword("-escape");
					public @S(20) SQL_KeywordChoice OFF = new SQL_KeywordChoice(
							"ascii",
							"off",
							"symbol");
				}
			}
		}
		
		public @CHOICE static class SQLite_ModeQBox extends TokenSequence
		{
			public @S(10) SQL_Keyword QBOX = new SQL_Keyword("qbox");
			public @S(20) @OPT TokenList<SQLite_ModeQBoxOption> option;
			
			public static class SQLite_ModeQBoxOption extends TokenChooser
			{
				public @CHOICE static class SQLite_ModeQBoxAlign extends TokenSequence
				{
					public @S(10) SQL_Keyword ALIGN = new SQL_Keyword("--align");
					public @S(20) SQL_Literal literal;
				}

				public @CHOICE static class SQLite_ModeQBoxBlob extends TokenSequence
				{
					public @S(10) SQL_Keyword BLOB = new SQL_Keyword("--blob-quote");
					public @S(20) SQL_Keyword AUTO = new SQL_Keyword("auto");
				}

				public @CHOICE static class SQLite_ModeQBoxBorder extends TokenSequence
				{
					public @S(10) SQL_Keyword BORDER = new SQL_Keyword("--border");
					public @S(20) SQL_Keyword ON = new SQL_Keyword("on");
				}

				public @CHOICE static class SQLite_ModeQBoxColSep extends TokenSequence
				{
					public @S(10) SQL_Keyword COLSEP = new SQL_Keyword("--colsep");
					public @S(20) SQL_Literal literal;
				}

				public @CHOICE static class SQLite_ModeQBoxEscape extends TokenSequence
				{
					public @S(10) SQL_Keyword ESCAPE = new SQL_Keyword("--escape");
					public @S(20) SQL_Keyword AUTO = new SQL_Keyword("auto");
				}

				public @CHOICE static class SQLite_ModeQBoxLimits extends TokenSequence
				{
					public @S(10) SQL_Keyword LIMITS = new SQL_Keyword("--limits");
					public @S(20) SQL_Keyword ON = new SQL_Keyword("on");
				}
				
				public @CHOICE static class SQLite_ModeQBoxMulti extends TokenSequence
				{
					public @S(10) SQL_Keyword MULTI = new SQL_Keyword("--multiinsert");
					public @S(20) SQL_Number number;
				}

				public @CHOICE static class SQLite_ModeQBoxQuote extends TokenSequence
				{
					public @S(10) SQL_Keyword QUOTE = new SQL_Keyword("--quote");
					public @S(20) SQL_Keyword RELAXED = new SQL_Keyword("relaxed");
				}
				
				public @CHOICE static class SQLite_ModeQBoxSW extends TokenSequence
				{
					public @S(10) SQL_Keyword SW = new SQL_Keyword("--sw");
					public @S(20) SQL_Keyword AUTO = new SQL_Keyword("auto");
				}
				
				public @CHOICE static class SQLite_ModeQBoxText extends TokenSequence
				{
					public @S(10) SQL_Keyword TEXT = new SQL_Keyword("--textjsonb");
					public @S(20) SQL_Keyword ON = new SQL_Keyword("on");
				}
			}
		}
	}
}
