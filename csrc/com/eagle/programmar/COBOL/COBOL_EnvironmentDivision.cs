// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

namespace com.eagle.programmar.COBOL
{
	using COBOL_Class_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_Class_Definition;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class COBOL_EnvironmentDivision : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ENVIRONMENT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ENVIRONMENT");
		public COBOL_Keyword ENVIRONMENT = new COBOL_Keyword("ENVIRONMENT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DIVISION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DIVISION");
		public COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<COBOL_EnvironmentEntry> entries;
		public  OPT;

		public class COBOL_EnvironmentEntry : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ClassControl XXclassControl;
			public COBOL_ClassControl XXclassControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ConfigurationSection XXconfiguration;
			public COBOL_ConfigurationSection XXconfiguration;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_InputOutputSection XXinputOutput;
			public COBOL_InputOutputSection XXinputOutput;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_SpecialNames XXspecialNames;
			public COBOL_SpecialNames XXspecialNames;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_FileControl XXfileControl;
			public COBOL_FileControl XXfileControl;
		}

		public class COBOL_ClassControl : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CLASSCONTROL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CLASS-CONTROL");
			public COBOL_Keyword CLASSCONTROL = new COBOL_Keyword("CLASS-CONTROL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot1;
			public PunctuationPeriod dot1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<COBOL_ClassControlIs> controlIsList;
			public TokenList<COBOL_ClassControlIs> controlIsList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationPeriod dot2;
			public PunctuationPeriod dot2;

			public class COBOL_ClassControlIs : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Symbols.COBOL_Class_Definition classDef;
				public COBOL_Class_Definition classDef;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CLASS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CLASS");
				public COBOL_Keyword CLASS = new COBOL_Keyword("CLASS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Literal name;
				public COBOL_Literal name;
			}
		}

		public class COBOL_ConfigurationSection : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CONFIGURATION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CONFIGURATION");
			public COBOL_Keyword CONFIGURATION = new COBOL_Keyword("CONFIGURATION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SECTION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SECTION");
			public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<COBOL_Computer> computers;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_SpecialNames specialNames;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments2;
			public  OPT;

			public class COBOL_Computer : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice source = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("SOURCE-COMPUTER", "OBJECT-COMPUTER");
				public COBOL_KeywordChoice source = new COBOL_KeywordChoice("SOURCE-COMPUTER", "OBJECT-COMPUTER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot1;
				public PunctuationPeriod dot1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
				public COBOL_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationPeriod dot2;
				public PunctuationPeriod dot2;
			}
		}

		public class COBOL_InputOutputSection : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword INPUTOUTPUT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("INPUT-OUTPUT");
			public COBOL_Keyword INPUTOUTPUT = new COBOL_Keyword("INPUT-OUTPUT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SECTION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SECTION");
			public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.COBOL.Terminals.COBOL_Comment> comments1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<COBOL_IOSectionEntry> entries;
			public  OPT;
		}

		public class COBOL_IOSectionEntry : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_FileControl XXfileControl;
			public COBOL_FileControl XXfileControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_IOControl XXioControl;
			public COBOL_IOControl XXioControl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_IOControlSelect XXselectControl;
			public COBOL_IOControlSelect XXselectControl;
		}

		public class COBOL_IOControl : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IOCONTROL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("I-O-CONTROL");
			public COBOL_Keyword IOCONTROL = new COBOL_Keyword("I-O-CONTROL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) COBOL_IOControlSame controlSame;
			public COBOL_IOControlSame controlSame;

			public class COBOL_IOControlSame : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SAME = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SAME");
				public COBOL_Keyword SAME = new COBOL_Keyword("SAME");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RECORD = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RECORD");
				public COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword AREA = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("AREA");
				public COBOL_Keyword AREA = new COBOL_Keyword("AREA");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FOR = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FOR");
				public COBOL_Keyword FOR = new COBOL_Keyword("FOR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword NETWORK = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("NETWORK");
				public COBOL_Keyword NETWORK = new COBOL_Keyword("NETWORK");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SHARED = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SHARED");
				public COBOL_Keyword SHARED = new COBOL_Keyword("SHARED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
			}
		}
	}

}
