// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 5, 2022

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Symbols.SQL_Synonym_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class SQL_CreateSynonymStatement extends TokenSequence
{
	public @S(10) SQL_Keyword CREATE = new SQL_Keyword("CREATE");
	public @S(20) SQL_Keyword PUBLIC = new SQL_Keyword("PUBLIC");
	public @S(30) SQL_Keyword SYNONYM = new SQL_Keyword("SYNONYM");
	public @S(40) SQL_Synonym_Definition synonym;
	public @S(50) SQL_Keyword FOR = new SQL_Keyword("FOR");
	public @S(60) @OPT SQL_CreateSynonymForWhom whom;
	public @S(70) PunctuationSemicolon semicolon;
	
	public static class SQL_CreateSynonymForWhom extends TokenSequence
	{
		public @S(10) SQL_Punctuation ampersand = new SQL_Punctuation('&');
		public @S(20) SQL_Identifier_Reference user;
		public @S(30) SQL_Punctuation dotDot = new SQL_Punctuation("..");
		public @S(40) SQL_Identifier_Reference group;
	}
}