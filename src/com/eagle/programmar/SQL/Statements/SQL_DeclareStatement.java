// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 6, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Type;
import com.eagle.programmar.SQL.Symbols.SQL_Declare_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_DeclareStatement extends TokenSequence
{
	public @S(10) SQL_Keyword DECLARE = new SQL_Keyword("DECLARE");
	public @S(20) TokenList<SQL_Declaration> declarations;

	public static class SQL_Declaration extends TokenSequence
	{
		public @S(10) SQL_Declare_Definition definition;
		public @S(20) SQL_Type type;
		public @S(30) @OPT SQL_Punctuation colonEquals = new SQL_Punctuation(":=");
		public @S(40) @OPT SQL_Expression value;
		public @S(50) PunctuationSemicolon semicolon;
	}
}
