// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2010

namespace com.eagle.programmar.COBOL
{
	using COBOL_Identifier = com.eagle.programmar.COBOL.Terminals.COBOL_Identifier;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using COBOL_Punctuation = com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class COBOL_Directive : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation dollar = new com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation('$');
		public COBOL_Punctuation dollar = new COBOL_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_WhichDirective which;
		public COBOL_WhichDirective which;

		public class COBOL_WhichDirective : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Keyword XXEND = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END");
			public COBOL_Keyword XXEND = new COBOL_Keyword("END");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_IfDefinedDirective extends com.eagle.tokens.TokenSequence
			public class COBOL_IfDefinedDirective : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IF = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IF");
				public COBOL_Keyword IF = new COBOL_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Identifier var;
				public COBOL_Identifier var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword NOT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("NOT");
				public COBOL_Keyword NOT = new COBOL_Keyword("NOT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DEFINED = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DEFINED");
				public COBOL_Keyword DEFINED = new COBOL_Keyword("DEFINED");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class COBOL_IfExprDirective extends com.eagle.tokens.TokenSequence
			public class COBOL_IfExprDirective : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IF = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IF");
				public COBOL_Keyword IF = new COBOL_Keyword("IF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_Expression expr;
				public COBOL_Expression expr;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_SetDirective extends com.eagle.tokens.TokenSequence
			public class COBOL_SetDirective : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SET = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SET");
				public COBOL_Keyword SET = new COBOL_Keyword("SET");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<COBOL_SetWhat> sets;
				public TokenList<COBOL_SetWhat> sets;

				public class COBOL_SetWhat : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_SetString XXsetString;
					public COBOL_SetString XXsetString;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_SetParensPlus XXsetParensPlus;
					public COBOL_SetParensPlus XXsetParensPlus;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_SetParensNumber XXsetParensNumber;
					public COBOL_SetParensNumber XXsetParensNumber;
				}

				public class COBOL_SetString : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice key = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("ANS85", "CASE", "CONSTANT", "DATALIT", "DEFAULTBYTE", "DIALECT", "ISO2002", "KEYCOMPRESS", "LINKCOUNT", "MF", "MFOO", "NESTCALL", "NOALTER", "NOKEYCOMPRESS", "NOOSVS", "NOQUAL", "NOVSC2", "QUAL", "SOURCEFORMAT");
					public COBOL_KeywordChoice key = new COBOL_KeywordChoice("ANS85", "CASE", "CONSTANT", "DATALIT", "DEFAULTBYTE", "DIALECT", "ISO2002", "KEYCOMPRESS", "LINKCOUNT", "MF", "MFOO", "NESTCALL", "NOALTER", "NOKEYCOMPRESS", "NOOSVS", "NOQUAL", "NOVSC2", "QUAL", "SOURCEFORMAT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Literal val;
					public  OPT;
				}

				public class COBOL_SetParensPlus : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice key = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("OOCTRL", "PREPROCESS", "REMOVE");
					public COBOL_KeywordChoice key = new COBOL_KeywordChoice("OOCTRL", "PREPROCESS", "REMOVE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
					public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Punctuation plus = new com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation('+');
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice code = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("CONTROL", "P", "WINDOW1");
					public COBOL_KeywordChoice code = new COBOL_KeywordChoice("CONTROL", "P", "WINDOW1");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
					public PunctuationRightParen rightParen;
				}

				public class COBOL_SetParensNumber : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice key = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("INTCODE");
					public COBOL_KeywordChoice key = new COBOL_KeywordChoice("INTCODE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
					public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Number number;
					public COBOL_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
					public PunctuationRightParen rightParen;
				}
			}
		}
	}

}
