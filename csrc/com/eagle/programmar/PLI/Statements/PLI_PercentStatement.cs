// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

namespace com.eagle.programmar.PLI.Statements
{
	using PLI_Expression = com.eagle.programmar.PLI.PLI_Expression;
	using PLI_Identifier_Reference = com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
	using PLI_Variable_Definition = com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using PLI_KeywordChoice = com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
	using PLI_Literal = com.eagle.programmar.PLI.Terminals.PLI_Literal;
	using PLI_Number = com.eagle.programmar.PLI.Terminals.PLI_Number;
	using PLI_Punctuation = com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class PLI_PercentStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Punctuation percent = new com.eagle.programmar.PLI.Terminals.PLI_Punctuation('%');
		public PLI_Punctuation percent = new PLI_Punctuation('%');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) PLI_PercentWhat what;
		public PLI_PercentWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon1;
		public PunctuationSemicolon semicolon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
		public  OPT;

		public class PLI_PercentWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_PercentAssignment extends com.eagle.tokens.TokenSequence
			public class PLI_PercentAssignment : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition var;
				public PLI_Variable_Definition var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.PLI_Expression expr;
				public PLI_Expression expr;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_PercentProcess extends com.eagle.tokens.TokenSequence
			public class PLI_PercentProcess : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("7.43") com.eagle.programmar.PLI.Terminals.PLI_Keyword PROCESS = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("PROCESS");
				public @DOC("7.43") PLI_Keyword PROCESS = new PLI_Keyword("PROCESS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_Keyword GOSTMT = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("GOSTMT");
				public PLI_Keyword GOSTMT = new PLI_Keyword("GOSTMT");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_PercentInclude extends com.eagle.tokens.TokenSequence
			public static class PLI_PercentInclude extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("7.29") com.eagle.programmar.PLI.Terminals.PLI_Keyword INCLUDE = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("INCLUDE");
				public @DOC("7.29") PLI_Keyword INCLUDE = new PLI_Keyword("INCLUDE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) PLI_ProcessIncludeWhat what;
				public PLI_ProcessIncludeWhat what;

				public static class PLI_ProcessIncludeWhat extends TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_Literal XXliteral;
					public PLI_Literal XXliteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_Identifier_Reference XXvar;
					public PLI_Identifier_Reference XXvar;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_PercentDeclare extends com.eagle.tokens.TokenSequence
			public static class PLI_PercentDeclare extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("7.10") com.eagle.programmar.PLI.Terminals.PLI_Keyword DECLARE = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("DECLARE");
				public @DOC("7.10") PLI_Keyword DECLARE = new PLI_Keyword("DECLARE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationLeftParen leftParen;
				public @OPT PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition, com.eagle.tokens.punctuation.PunctuationComma> vars;
				public SeparatedList<PLI_Variable_Definition, PunctuationComma> vars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationRightParen rightParen;
				public @OPT PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice type = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("FIXED", "CHARACTER");
				public PLI_KeywordChoice type = new PLI_KeywordChoice("FIXED", "CHARACTER");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_PercentActivate extends com.eagle.tokens.TokenSequence
			public static class PLI_PercentActivate extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword ACTIVATE = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("ACTIVATE");
				public PLI_Keyword ACTIVATE = new PLI_Keyword("ACTIVATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationComma> vars;
				public SeparatedList<PLI_Identifier_Reference, PunctuationComma> vars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_Keyword NORESCAN = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("NORESCAN");
				public @OPT PLI_Keyword NORESCAN = new PLI_Keyword("NORESCAN");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_PercentDeactivate extends com.eagle.tokens.TokenSequence
			public static class PLI_PercentDeactivate extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("7.8") com.eagle.programmar.PLI.Terminals.PLI_Keyword DEACTIVATE = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("DEACTIVATE");
				public @DOC("7.8") PLI_Keyword DEACTIVATE = new PLI_Keyword("DEACTIVATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference var;
				public PLI_Identifier_Reference var;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_PercentSkip extends com.eagle.tokens.TokenSequence
			public static class PLI_PercentSkip extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword SKIP = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("SKIP");
				public PLI_Keyword SKIP = new PLI_Keyword("SKIP");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.Terminals.PLI_Number number;
				public PLI_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}
		}
	}

}
