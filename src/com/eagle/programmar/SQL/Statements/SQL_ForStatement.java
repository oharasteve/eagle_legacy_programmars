// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 6, 2014

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.Symbols.SQL_Declare_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_ForStatement extends TokenSequence
{
	public @S(10) SQL_Keyword FOR = new SQL_Keyword("FOR");
	public @S(20) SQL_Declare_Definition variable;
	public @S(30) SQL_Keyword IN = new SQL_Keyword("IN");
	public @S(40) SQL_Expression start;
	public @S(50) SQL_Punctuation dotDot = new SQL_Punctuation("..");
	public @S(60) SQL_Expression finish;
	public @S(70) SQL_Keyword LOOP1 = new SQL_Keyword("LOOP");
	
	public @S(80) TokenList<SQL_StatementOrComment> statements;

	public @S(90) SQL_Keyword END = new SQL_Keyword("END");
	public @S(100) SQL_Keyword LOOP2 = new SQL_Keyword("LOOP");
	public @S(110) PunctuationSemicolon semicolon;
}
