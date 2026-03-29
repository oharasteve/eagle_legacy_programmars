// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.COBOL
{
	using COBOL_CopyOrDataDeclaration = com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
	using COBOL_File_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_File_Definition;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class COBOL_FileDescriptor : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice fd_sd = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("FD", "SD");
		public COBOL_KeywordChoice fd_sd = new COBOL_KeywordChoice("FD", "SD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_File_Definition id;
		public COBOL_File_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_FD_ReportExternal reportExternal;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_FD_RecordContains recordContains;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_FD_RecordIsVarying recordIsVarying;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_FD_LabelRecordStandard labelRecordStandard;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT COBOL_FD_LabelRecordOmitted labelRecordOmitted;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT COBOL_FD_RecordingMode recordingMode;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT TokenList<com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration> dataDeclarations;
		public  OPT;

		public class COBOL_FD_ReportExternal : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_FD_ReportIs extends com.eagle.tokens.TokenSequence
			public class COBOL_FD_ReportIs : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword REPORT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("REPORT");
				public COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword EXTERNAL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("EXTERNAL");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Identifier_Reference reportId;
				public  OPT;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_FD_IsExternal extends com.eagle.tokens.TokenSequence
			public class COBOL_FD_IsExternal : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword EXTERNAL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("EXTERNAL");
				public COBOL_Keyword EXTERNAL = new COBOL_Keyword("EXTERNAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Identifier_Reference reportId;
				public  OPT;
			}
		}

		public class COBOL_FD_RecordContains : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RECORD = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RECORD");
			public COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CONTAINS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CONTAINS");
			public COBOL_Keyword CONTAINS = new COBOL_Keyword("CONTAINS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Number count;
			public COBOL_Number count;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CHARACTERS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CHARACTERS");
			public COBOL_Keyword CHARACTERS = new COBOL_Keyword("CHARACTERS");
		}

		public class COBOL_FD_RecordIsVarying : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RECORD = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RECORD");
			public COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword VARYING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("VARYING");
			public COBOL_Keyword VARYING = new COBOL_Keyword("VARYING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IN");
			public COBOL_Keyword IN = new COBOL_Keyword("IN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SIZE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SIZE");
			public COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DEPENDING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DEPENDING");
			public COBOL_Keyword DEPENDING = new COBOL_Keyword("DEPENDING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ON = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ON");
			public COBOL_Keyword ON = new COBOL_Keyword("ON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference what;
			public COBOL_Identifier_Reference what;
		}

		public class COBOL_FD_LabelRecordStandard : TokenSequence
		{
			// LABEL RECORD STANDARD VALUE OF FILE-ID "HLP\APACHELP.APC"
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LABEL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LABEL");
			public COBOL_Keyword LABEL = new COBOL_Keyword("LABEL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice RECORD = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("RECORD", "RECORDS");
			public COBOL_KeywordChoice RECORD = new COBOL_KeywordChoice("RECORD", "RECORDS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_KeywordChoice IS1 = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("IS", "ARE");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword STANDARD = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("STANDARD");
			public COBOL_Keyword STANDARD = new COBOL_Keyword("STANDARD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_FD_LabelRecordValue labelValue;
			public  OPT;

			public class COBOL_FD_LabelRecordValue : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword VALUE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("VALUE");
				public COBOL_Keyword VALUE = new COBOL_Keyword("VALUE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword OF = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OF");
				public COBOL_Keyword OF = new COBOL_Keyword("OF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FILEID = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FILE-ID");
				public COBOL_Keyword FILEID = new COBOL_Keyword("FILE-ID");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Keyword IS2 = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) COBOL_FileId fileId;
				public COBOL_FileId fileId;

				public class COBOL_FileId : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Identifier_Reference XXfileRef;
					public COBOL_Identifier_Reference XXfileRef;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Literal XXfileName;
					public COBOL_Literal XXfileName;
				}
			}
		}

		public class COBOL_FD_RecordingMode : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RECORDING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RECORDING");
			public COBOL_Keyword RECORDING = new COBOL_Keyword("RECORDING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword MODE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("MODE");
			public COBOL_Keyword MODE = new COBOL_Keyword("MODE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword VARIABLE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("VARIABLE");
			public COBOL_Keyword VARIABLE = new COBOL_Keyword("VARIABLE");
		}

		public class COBOL_FD_LabelRecordOmitted : TokenSequence
		{
			// LABEL RECORD STANDARD VALUE OF FILE-ID "HLP\APACHELP.APC"
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LABEL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LABEL");
			public COBOL_Keyword LABEL = new COBOL_Keyword("LABEL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RECORD = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RECORD");
			public COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword OMITTED = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("OMITTED");
			public COBOL_Keyword OMITTED = new COBOL_Keyword("OMITTED");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_FD_Linage linage;
			public  OPT;

			public class COBOL_FD_Linage : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LINAGE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LINAGE");
				public COBOL_Keyword LINAGE = new COBOL_Keyword("LINAGE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) COBOL_Expression linage;
				public COBOL_Expression linage;
			}
		}
	}
}
