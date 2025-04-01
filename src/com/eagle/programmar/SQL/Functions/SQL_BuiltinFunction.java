// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

package com.eagle.programmar.SQL.Functions;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_Variable;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_BuiltinFunction extends TokenSequence
{
	public @S(10) SQL_FunctionName funcName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<SQL_FunctionArg, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;

	public static class SQL_FunctionArg extends TokenChooser
	{
		public @CHOICE SQL_Expression XXarg;

		public @CHOICE static class SQL_FunctionColonParam extends TokenSequence
		{
			public @S(10) PunctuationColon colon;
			public @S(20) SQL_Expression arg;
		}

		public @FIRST static class SQL_FunctionNamedArg extends TokenSequence
		{
			public @S(10) SQL_Identifier_Reference parameterName;
			public @S(20) SQL_Punctuation equalsGreater = new SQL_Punctuation("=>");
			public @S(30) SQL_Expression arg;
		}
	}

	public static class SQL_FunctionName extends TokenChooser
	{
		public @LAST SQL_Variable XXvar;

		public @FIRST SQL_KeywordChoice XXfnName = new SQL_KeywordChoice(
				"COALESCE", "CONCAT", "COUNT", "CURRENT_TIMESTAMP", "MIN",
				"RUN_METRIC", "SUBSTRING", "SYS_EXTRACT_UTC", "SYS_GUID");

		public @CHOICE static class SQL_FunctionSCHEDULER extends TokenSequence
		{
			public @S(10) SQL_Keyword DBMSSCHEDULER = new SQL_Keyword("DBMS_SCHEDULER");
			public @S(20) PunctuationPeriod dot;
			public @S(30) SQL_KeywordChoice DROPJOB = new SQL_KeywordChoice("CREATE_JOB", "DROP_JOB");
		}

		public @CHOICE static class SQL_FunctionLOB extends TokenSequence
		{
			public @S(10) SQL_Keyword DBMSLOB = new SQL_Keyword("DBMS_LOB");
			public @S(20) PunctuationPeriod dot;
			public @S(30) SQL_Keyword GETLENGTH = new SQL_Keyword("GETLENGTH");
		}

		public @CHOICE static class SQL_FunctionJOB extends TokenSequence
		{
			public @S(10) SQL_Keyword DBMSJOB = new SQL_Keyword("DBMS_JOB");
			public @S(20) PunctuationPeriod dot;
			public @S(30) SQL_Keyword REMOVE = new SQL_Keyword("REMOVE");
		}
	}
}
