// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

namespace com.eagle.programmar.COBOL
{
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class COBOL_FileSelect : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SELECT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SELECT");
		public COBOL_Keyword SELECT = new COBOL_Keyword("SELECT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword NOT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("NOT");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword OPTIONAL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OPTIONAL");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference selectWhat;
		public COBOL_Identifier_Reference selectWhat;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ASSIGN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ASSIGN");
		public COBOL_Keyword ASSIGN = new COBOL_Keyword("ASSIGN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_Keyword TO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TO");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) COBOL_AssignTo assignTo;
		public COBOL_AssignTo assignTo;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<COBOL_SelectClause> selectClauses;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;

		public class COBOL_AssignTo : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Literal XXassignLiteral;
			public COBOL_Literal XXassignLiteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Keyword XXDISK = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DISK");
			public COBOL_Keyword XXDISK = new COBOL_Keyword("DISK");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Identifier_Reference XXdataRef;
			public COBOL_Identifier_Reference XXdataRef;
		}

		public class COBOL_SelectClause : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Comment XXcomment;
			public COBOL_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_SelectOrganization extends com.eagle.tokens.TokenSequence
			public class COBOL_SelectOrganization : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ORGANIZATION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ORGANIZATION");
				public COBOL_Keyword ORGANIZATION = new COBOL_Keyword("ORGANIZATION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword LINE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LINE");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice organization = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("INDEXED", "SEQUENTIAL", "RELATIVE");
				public COBOL_KeywordChoice organization = new COBOL_KeywordChoice("INDEXED", "SEQUENTIAL", "RELATIVE");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_SelectAccess extends com.eagle.tokens.TokenSequence
			public class COBOL_SelectAccess : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ACCESS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ACCESS");
				public COBOL_Keyword ACCESS = new COBOL_Keyword("ACCESS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword MODE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("MODE");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice access = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("SEQUENTIAL", "DYNAMIC", "RANDOM");
				public COBOL_KeywordChoice access = new COBOL_KeywordChoice("SEQUENTIAL", "DYNAMIC", "RANDOM");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_SelectRecord extends com.eagle.tokens.TokenSequence
			public class COBOL_SelectRecord : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RECORD = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RECORD");
				public COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword KEY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("KEY");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
				public COBOL_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_SelectRecordEquals selectEquals;
				public  OPT;

				public class COBOL_SelectRecordEquals : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
					public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference> ids;
					public TokenList<COBOL_Identifier_Reference> ids;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_SelectAlternate extends com.eagle.tokens.TokenSequence
			public class COBOL_SelectAlternate : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ALTERNATE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ALTERNATE");
				public COBOL_Keyword ALTERNATE = new COBOL_Keyword("ALTERNATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword RECORD = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RECORD");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword KEY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("KEY");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
				public COBOL_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_SelectAlternates selectAlternates;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT COBOL_Keyword WITH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WITH");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT COBOL_Keyword DUPLICATES = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DUPLICATES");
				public  OPT;

				public class COBOL_SelectAlternates : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
					public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference> variables;
					public TokenList<COBOL_Identifier_Reference> variables;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_SelectFile extends com.eagle.tokens.TokenSequence
			public class COBOL_SelectFile : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT COBOL_Keyword FILE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FILE");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword STATUS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("STATUS");
				public COBOL_Keyword STATUS = new COBOL_Keyword("STATUS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
				public COBOL_Identifier_Reference id;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_SelectRelative extends com.eagle.tokens.TokenSequence
			public class COBOL_SelectRelative : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RELATIVE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RELATIVE");
				public COBOL_Keyword RELATIVE = new COBOL_Keyword("RELATIVE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword KEY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("KEY");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference id;
				public COBOL_Identifier_Reference id;
			}

			// LOCK MANUAL WITH LOCK ON MULTIPLE RECORDS
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_SelectLock extends com.eagle.tokens.TokenSequence
			public class COBOL_SelectLock : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice LOCK = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("LOCK", "LOCKING");
				public COBOL_KeywordChoice LOCK = new COBOL_KeywordChoice("LOCK", "LOCKING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice EXCLUSIVE = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("EXCLUSIVE", "AUTOMATIC", "MANUAL");
				public COBOL_KeywordChoice EXCLUSIVE = new COBOL_KeywordChoice("EXCLUSIVE", "AUTOMATIC", "MANUAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_SelectLockWith lockWith;
				public  OPT;

				public class COBOL_SelectLockWith : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword WITH = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("WITH");
					public COBOL_Keyword WITH = new COBOL_Keyword("WITH");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LOCK = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LOCK");
					public COBOL_Keyword LOCK = new COBOL_Keyword("LOCK");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ON = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ON");
					public COBOL_Keyword ON = new COBOL_Keyword("ON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword MULTIPLE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("MULTIPLE");
					public COBOL_Keyword MULTIPLE = new COBOL_Keyword("MULTIPLE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RECORDS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RECORDS");
					public COBOL_Keyword RECORDS = new COBOL_Keyword("RECORDS");
				}
			}
		}
	}
}
