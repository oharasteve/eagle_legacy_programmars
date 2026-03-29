// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

namespace com.eagle.programmar.SQL.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using SQL_FunctionArg = com.eagle.programmar.SQL.SQL_FunctionArg;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_KeywordChoice = com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class SQL_BuiltinFunction : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) SQL_FunctionName funcName;
		public SQL_FunctionName funcName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<com.eagle.programmar.SQL.SQL_FunctionArg, com.eagle.tokens.punctuation.PunctuationComma> args;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public class SQL_FunctionName : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST SQL_KeywordChoice XXfnName = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("COALESCE", "COUNT", "CURRENT_TIMESTAMP", "MIN", "RUN_METRIC", "SYS_EXTRACT_UTC", "SYS_GUID");
			public SQL_KeywordChoice XXfnName = new SQL_KeywordChoice("COALESCE", "COUNT", "CURRENT_TIMESTAMP", "MIN", "RUN_METRIC", "SYS_EXTRACT_UTC", "SYS_GUID");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FunctionSCHEDULER extends com.eagle.tokens.TokenSequence
			public class SQL_FunctionSCHEDULER : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword DBMSSCHEDULER = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("DBMS_SCHEDULER");
				public SQL_Keyword DBMSSCHEDULER = new SQL_Keyword("DBMS_SCHEDULER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice DROPJOB = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("CREATE_JOB", "DROP_JOB");
				public SQL_KeywordChoice DROPJOB = new SQL_KeywordChoice("CREATE_JOB", "DROP_JOB");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FunctionLOB extends com.eagle.tokens.TokenSequence
			public class SQL_FunctionLOB : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword DBMSLOB = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("DBMS_LOB");
				public SQL_Keyword DBMSLOB = new SQL_Keyword("DBMS_LOB");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword GETLENGTH = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("GETLENGTH");
				public SQL_Keyword GETLENGTH = new SQL_Keyword("GETLENGTH");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class SQL_FunctionJOB extends com.eagle.tokens.TokenSequence
			public class SQL_FunctionJOB : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword DBMSJOB = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("DBMS_JOB");
				public SQL_Keyword DBMSJOB = new SQL_Keyword("DBMS_JOB");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice REMOVE = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("REMOVE", "SUBMIT");
				public SQL_KeywordChoice REMOVE = new SQL_KeywordChoice("REMOVE", "SUBMIT");
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string func = funcName.getWhich().ToString();
			switch (func.ToUpper())
			{
			default:
				throw new Exception("Unable to handle: " + func);
			}
		}
	}

}
