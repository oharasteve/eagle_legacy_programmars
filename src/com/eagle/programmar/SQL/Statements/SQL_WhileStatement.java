// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Label_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_WhileStatement extends TokenSequence
{
	public @S(10) @OPT SQL_WhileLabel label1;
	public @S(20) SQL_Keyword WHILE1 = new SQL_Keyword("WHILE");
	public @S(30) SQL_Expression condition;
	public @S(40) SQL_Keyword DO = new SQL_Keyword("DO");
	public @S(50) TokenList<SQL_StatementOrComment> statements;
	public @S(60) SQL_Keyword END = new SQL_Keyword("END");
	public @S(70) SQL_Keyword WHILE2 = new SQL_Keyword("WHILE");
	public @S(80) @OPT SQL_Identifier_Reference label2;
	public @S(90) PunctuationSemicolon semicolon;
	
	public static class SQL_WhileLabel extends TokenSequence
	{
		public @S(10) SQL_Label_Definition label1;
		public @S(20) PunctuationColon colon;
	}
}
