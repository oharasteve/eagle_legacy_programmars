// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Subscript = com.eagle.programmar.COBOL.COBOL_Subscript;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class COBOL_AcceptStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpsacce.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ACCEPT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ACCEPT");
		public @DOC("rlpsacce.htm") COBOL_Keyword ACCEPT = new COBOL_Keyword("ACCEPT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_AcceptPosition position;
		public @OPT COBOL_AcceptPosition position;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference var;
		public COBOL_Identifier_Reference var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Subscript subscript;
		public @OPT COBOL_Subscript subscript;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<COBOL_AcceptOption> options;
		public @OPT TokenList<COBOL_AcceptOption> options;

		public static class COBOL_AcceptPosition extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression line;
			public COBOL_Expression line;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.COBOL_Expression column;
			public COBOL_Expression column;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public static class COBOL_AcceptOption extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_KeywordChoice XXoption = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("AUTO", "AUTO-SKIP", "FULL", "NO-ECHO", "PROMPT", "SECURE", "UPDATE");
			public COBOL_KeywordChoice XXoption = new COBOL_KeywordChoice("AUTO", "AUTO-SKIP", "FULL", "NO-ECHO", "PROMPT", "SECURE", "UPDATE");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_AcceptFrom extends com.eagle.tokens.TokenSequence
			public static class COBOL_AcceptFrom extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FROM = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FROM");
				public COBOL_Keyword FROM = new COBOL_Keyword("FROM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice time = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("DATE", "DAY", "TIME");
				public COBOL_KeywordChoice time = new COBOL_KeywordChoice("DATE", "DAY", "TIME");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_KeywordChoice format = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("YYYYMMDD", "YYYYDDD");
				public @OPT COBOL_KeywordChoice format = new COBOL_KeywordChoice("YYYYMMDD", "YYYYDDD");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_AcceptCommand extends com.eagle.tokens.TokenSequence
			public static class COBOL_AcceptCommand extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FROM = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FROM");
				public COBOL_Keyword FROM = new COBOL_Keyword("FROM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword COMMANDLINE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("COMMAND-LINE");
				public COBOL_Keyword COMMANDLINE = new COBOL_Keyword("COMMAND-LINE");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_AcceptAt extends com.eagle.tokens.TokenSequence
			public static class COBOL_AcceptAt extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword AT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("AT");
				public COBOL_Keyword AT = new COBOL_Keyword("AT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword LINE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LINE");
				public @OPT COBOL_Keyword LINE = new COBOL_Keyword("LINE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.COBOL_Expression location;
				public COBOL_Expression location;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_AcceptColumn extends com.eagle.tokens.TokenSequence
			public static class COBOL_AcceptColumn extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword COLUMN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("COLUMN");
				public COBOL_Keyword COLUMN = new COBOL_Keyword("COLUMN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression column;
				public COBOL_Expression column;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_AcceptWithColors extends com.eagle.tokens.TokenSequence
			public static class COBOL_AcceptWithColors extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword WITH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WITH");
				public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword UPDATE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("UPDATE");
				public @OPT COBOL_Keyword UPDATE = new COBOL_Keyword("UPDATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<COBOL_AcceptColor> colors;
				public TokenList<COBOL_AcceptColor> colors;

				public static class COBOL_AcceptColor extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice color = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("AUTO", "AUTO-SKIP", "FOREGROUND-COLOR", "BACKGROUND-COLOR", "HIGHLIGHT");
					public COBOL_KeywordChoice color = new COBOL_KeywordChoice("AUTO", "AUTO-SKIP", "FOREGROUND-COLOR", "BACKGROUND-COLOR", "HIGHLIGHT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Number fg;
					public @OPT COBOL_Number fg;
				}
			}
		}
	}

}
