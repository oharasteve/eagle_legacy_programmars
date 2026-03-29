// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_DisplayColumn = com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement.COBOL_DisplayColumn;
	using COBOL_DisplayLine = com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement.COBOL_DisplayLine;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class COBOL_DisplayOptions : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_DisplayLine XXline;
		public COBOL_DisplayLine XXline;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_DisplayColumn XXcolumn;
		public COBOL_DisplayColumn XXcolumn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationComma XXcomma;
		public PunctuationComma XXcomma;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_DisplayUpon extends com.eagle.tokens.TokenSequence
		public class COBOL_DisplayUpon : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword UPON = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("UPON");
			public COBOL_Keyword UPON = new COBOL_Keyword("UPON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference upon;
			public COBOL_Identifier_Reference upon;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class COBOL_DisplayAt extends com.eagle.tokens.TokenSequence
		public class COBOL_DisplayAt : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword AT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("AT");
			public COBOL_Keyword AT = new COBOL_Keyword("AT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_DisplayLine line;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_DisplayColumn column;
			public  OPT;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_DisplayWith extends com.eagle.tokens.TokenSequence
		public class COBOL_DisplayWith : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword WITH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WITH");
			public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<COBOL_DisplayColor> colors;
			public TokenList<COBOL_DisplayColor> colors;

			public class COBOL_DisplayColor : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice color = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("FOREGROUND-COLOR", "BACKGROUND-COLOR", "HBCKGROUND-COLOR", "HIGHLIGHT", "REVERSE-VIDEO");
				public COBOL_KeywordChoice color = new COBOL_KeywordChoice("FOREGROUND-COLOR", "BACKGROUND-COLOR", "HBCKGROUND-COLOR", "HIGHLIGHT", "REVERSE-VIDEO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Number fg;
				public  OPT;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_DisplayWithNoAdvancing extends com.eagle.tokens.TokenSequence
		public class COBOL_DisplayWithNoAdvancing : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT COBOL_Keyword WITH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WITH");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword NO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("NO");
			public COBOL_Keyword NO = new COBOL_Keyword("NO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ADVANCING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ADVANCING");
			public COBOL_Keyword ADVANCING = new COBOL_Keyword("ADVANCING");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_DisplayWithControl extends com.eagle.tokens.TokenSequence
		public class COBOL_DisplayWithControl : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword WITH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WITH");
			public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CONTROL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CONTROL");
			public COBOL_Keyword CONTROL = new COBOL_Keyword("CONTROL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference control;
			public COBOL_Identifier_Reference control;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_DisplayLines extends com.eagle.tokens.TokenSequence
		public class COBOL_DisplayLines : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LINES = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LINES");
			public COBOL_Keyword LINES = new COBOL_Keyword("LINES");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression lines;
			public COBOL_Expression lines;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_DisplaySize extends com.eagle.tokens.TokenSequence
		public class COBOL_DisplaySize : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SIZE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SIZE");
			public COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.COBOL_Expression size;
			public COBOL_Expression size;
		}
	}

}
