// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

package com.eagle.programmar.SQL.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.SQL.SQL_FunctionArg;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_BuiltinFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) SQL_FunctionName funcName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<SQL_FunctionArg, PunctuationComma> args;
	public @S(40) PunctuationRightParen rightParen;

	public static class SQL_FunctionName extends TokenChooser
	{
		public @FIRST SQL_KeywordChoice XXfnName = new SQL_KeywordChoice(
				"COALESCE",
				"COUNT",
				"CURRENT_TIMESTAMP",
				"MIN",
				"RUN_METRIC",
				"SYS_EXTRACT_UTC",
				"SYS_GUID");

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
			public @S(30) SQL_KeywordChoice REMOVE = new SQL_KeywordChoice("REMOVE", "SUBMIT");
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String func = funcName.getWhich().toString();
		switch (func.toUpperCase())
		{
		default:
			throw new RuntimeException("Unable to handle: " + func);
		}
	}
}
