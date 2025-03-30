// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.SQL_Type;
import com.eagle.programmar.SQL.Symbols.SQL_Parameter_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Procedure_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_CreateProcedureStatement extends TokenSequence
{
	public @S(10) @DOC("sql_create_procedure.asp") SQL_Keyword CREATE = new SQL_Keyword("CREATE");
	public @S(20) @OPT SQL_OrReplaceProcedure replace;
	public @S(30) SQL_Keyword PROCEDURE = new SQL_Keyword("PROCEDURE");
	public @S(40) SQL_Procedure_Definition proc;
	public @S(50) PunctuationLeftParen leftParen;
	public @S(60) @OPT SeparatedList<SQL_ProcedureParameter, PunctuationComma> params;
	public @S(70) PunctuationRightParen rightParen;
	public @S(80) SQL_Keyword BEGIN = new SQL_Keyword("BEGIN");
	public @S(90) TokenList<SQL_StatementOrComment> stmts;
	public @S(100) SQL_Keyword END = new SQL_Keyword("END");
	public @S(110) SQL_PunctuationChoice semicolon = new SQL_PunctuationChoice("//");
	
	public static class SQL_OrReplaceProcedure extends TokenSequence
	{
		public @S(10) SQL_Keyword OR = new SQL_Keyword("OR");
		public @S(20) SQL_Keyword REPLACE = new SQL_Keyword("REPLACE");
	}
	
	public static class SQL_ProcedureParameter extends TokenSequence
	{
		public @S(10) @OPT SQL_KeywordChoice OUT = new SQL_KeywordChoice("IN", "OUT");
		public @S(20) SQL_Parameter_Definition param;
		public @S(30) SQL_Type type;
	}
}