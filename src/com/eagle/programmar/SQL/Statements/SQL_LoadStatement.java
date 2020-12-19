// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Literal;
import com.eagle.tokens.TokenSequence;

public class SQL_LoadStatement extends TokenSequence
{
	public @S(10) SQL_Keyword LOAD = new SQL_Keyword("LOAD");
	public @S(20) SQL_Keyword DATA = new SQL_Keyword("DATA");
	public @S(30) SQL_Keyword LOCAL = new SQL_Keyword("LOCAL");
	public @S(40) SQL_Keyword INFILE = new SQL_Keyword("INFILE");
	public @S(50) SQL_Literal inFile;
	public @S(60) SQL_Keyword INTO = new SQL_Keyword("INTO");
	public @S(70) SQL_Keyword TABLE = new SQL_Keyword("TABLE");
	public @S(80) SQL_Identifier_Reference table;
	public @S(90) SQL_Keyword FIELDS = new SQL_Keyword("FIELDS");
	public @S(100) SQL_Keyword TERMINATED1 = new SQL_Keyword("TERMINATED");
	public @S(110) SQL_Keyword BY1 = new SQL_Keyword("BY");
	public @S(120) SQL_Literal fieldTerminator;
	public @S(130) SQL_Keyword ENCLOSED = new SQL_Keyword("ENCLOSED");
	public @S(140) SQL_Keyword BY2 = new SQL_Keyword("BY");
	public @S(150) SQL_Literal enclosure;
	public @S(160) SQL_Keyword ESCAPED = new SQL_Keyword("ESCAPED");
	public @S(170) SQL_Keyword BY3 = new SQL_Keyword("BY");
	public @S(180) SQL_Literal escaped;
	public @S(190) SQL_Keyword LINES = new SQL_Keyword("LINES");
	public @S(200) SQL_Keyword TERMINATED2 = new SQL_Keyword("TERMINATED");
	public @S(210) SQL_Keyword BY4 = new SQL_Keyword("BY");
	public @S(220) SQL_Literal lineTerminator;
}

