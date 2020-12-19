// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_File_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_DataDivision extends TokenSequence
{
	public @S(10) @OPT COBOL_DataDivisionHeader header;
	public @S(20) TokenList<COBOL_DataSection> sections;
	
	public static class COBOL_DataDivisionHeader extends TokenSequence
	{
		public @S(10) COBOL_Keyword DATA = new COBOL_Keyword("DATA");
		public @S(20) COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
		public @S(30) PunctuationPeriod dot;
	}

	public static class COBOL_DataSection extends TokenChooser
	{
		public @CHOICE COBOL_Comment comment;
		public @CHOICE COBOL_FileSection fileSection;
		public @CHOICE COBOL_WorkingStorageSection workingStorageSection;
		public @CHOICE COBOL_LocalStorageSection localStorageSection;
		public @CHOICE COBOL_ScreenSection screenSection;
		public @CHOICE COBOL_LinkageSection linkageSection;
		public @CHOICE COBOL_ReportSection reportSection;
	}
	
	public static class COBOL_FileSection extends TokenSequence
	{
		public @S(10) COBOL_Keyword FILE = new COBOL_Keyword("FILE");
		public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public @S(30) PunctuationPeriod dot;
		public @S(40) TokenList<COBOL_Copy_or_FileDescriptor> fileDescriptors;
	}
	
	public static class COBOL_Copy_or_FileDescriptor extends TokenChooser
	{
		public @CHOICE COBOL_Copy_Directive copyDirective;
		public @CHOICE COBOL_Comment comment;

		public @CHOICE static class COBOL_FileDescriptor extends TokenSequence
		{
			public @S(10) COBOL_KeywordChoice fd_sd = new COBOL_KeywordChoice("FD", "SD");
			public @S(20) COBOL_File_Definition id;
			public @S(30) @OPT COBOL_FD_ReportExternal reportExternal;
			public @S(40) @OPT COBOL_FD_RecordContains recordContains;
			public @S(50) @OPT COBOL_FD_LabelRecordStandard labelRecordStandard;
			public @S(60) @OPT COBOL_FD_LabelRecordOmitted labelRecordOmitted;
			public @S(70) @OPT COBOL_FD_RecordingMode recordingMode;
			public @S(80) PunctuationPeriod dot;
			public @S(90) @OPT TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
			
			public static class COBOL_FD_ReportExternal extends TokenChooser
			{
				public static @CHOICE class COBOL_FD_ReportIs extends TokenSequence
				{
					public @S(10) COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
					public @S(20) COBOL_Keyword IS = new COBOL_Keyword("IS");
					public @S(30) @OPT COBOL_Keyword EXTERNAL = new COBOL_Keyword("EXTERNAL");
					public @S(40) @OPT COBOL_Identifier_Reference reportId;
				}
				public static @CHOICE class COBOL_FD_IsExternal extends TokenSequence
				{
					public @S(10) COBOL_Keyword IS = new COBOL_Keyword("IS");
					public @S(20) COBOL_Keyword EXTERNAL = new COBOL_Keyword("EXTERNAL");
					public @S(30) @OPT COBOL_Identifier_Reference reportId;
				}
			}
			
			public static class COBOL_FD_RecordContains extends TokenSequence
			{
				public @S(10) COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
				public @S(20) COBOL_Keyword CONTAINS = new COBOL_Keyword("CONTAINS");
				public @S(30) COBOL_Number count;
				public @S(40) COBOL_Keyword CHARACTERS = new COBOL_Keyword("CHARACTERS");
			}
			
			public static class COBOL_FD_LabelRecordStandard extends TokenSequence
			{
				// LABEL RECORD STANDARD VALUE OF FILE-ID "HLP\APACHELP.APC"
				public @S(10) COBOL_Keyword LABEL = new COBOL_Keyword("LABEL");
				public @S(20) COBOL_KeywordChoice RECORD = new COBOL_KeywordChoice("RECORD", "RECORDS");
				public @S(30) @OPT COBOL_KeywordChoice IS1 = new COBOL_KeywordChoice("IS", "ARE");
				public @S(40) COBOL_Keyword STANDARD = new COBOL_Keyword("STANDARD");
				public @S(50) @OPT COBOL_FD_LabelRecordValue labelValue;
				
				public static class COBOL_FD_LabelRecordValue extends TokenSequence
				{
					public @S(10) COBOL_Keyword VALUE = new COBOL_Keyword("VALUE");
					public @S(20) COBOL_Keyword OF = new COBOL_Keyword("OF");
					public @S(30) COBOL_Keyword FILEID = new COBOL_Keyword("FILE-ID");
					public @S(40) @OPT COBOL_Keyword IS2 = new COBOL_Keyword("IS");
					public @S(50) COBOL_FileId fileId;
					
					public static class COBOL_FileId extends TokenChooser
					{
						public @CHOICE COBOL_Identifier_Reference fileRef;
						public @CHOICE COBOL_Literal fileName;
					}
				}
			}
			
			public static class COBOL_FD_RecordingMode extends TokenSequence
			{
				public @S(10) COBOL_Keyword RECORDING = new COBOL_Keyword("RECORDING");
				public @S(20) COBOL_Keyword MODE = new COBOL_Keyword("MODE");
				public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
				public @S(40) COBOL_Keyword VARIABLE = new COBOL_Keyword("VARIABLE");
			}

			public static class COBOL_FD_LabelRecordOmitted extends TokenSequence
			{
				// LABEL RECORD STANDARD VALUE OF FILE-ID "HLP\APACHELP.APC"
				public @S(10) COBOL_Keyword LABEL = new COBOL_Keyword("LABEL");
				public @S(20) COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
				public @S(30) COBOL_Keyword OMITTED = new COBOL_Keyword("OMITTED");
				public @S(40) @OPT COBOL_FD_Linage linage;
				
				public static class COBOL_FD_Linage extends TokenSequence
				{
					public @S(10) COBOL_Keyword LINAGE = new COBOL_Keyword("LINAGE");
					public @S(20) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
					public @S(30) COBOL_Expression linage;
				}
			}
		}
	}
	
	public static class COBOL_WorkingStorageSection extends TokenSequence
	{
		public @S(10) COBOL_Keyword WORKINGSTORAGE = new COBOL_Keyword("WORKING-STORAGE");
		public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public @S(30) PunctuationPeriod dot;
		public @S(40) TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
	}
		
	public static class COBOL_LocalStorageSection extends TokenSequence
	{
		public @S(10) COBOL_Keyword LOCALSTORAGE = new COBOL_Keyword("LOCAL-STORAGE");
		public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public @S(30) PunctuationPeriod dot;
		public @S(40) @OPT TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
	}
		
	public static class COBOL_LinkageSection extends TokenSequence
	{
		public @S(10) COBOL_Keyword LINKAGE = new COBOL_Keyword("LINKAGE");
		public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public @S(30) PunctuationPeriod dot;
		public @S(40) TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
	}
	
	public static class COBOL_ReportSection extends TokenSequence
	{
		public @S(10) COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
		public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public @S(30) PunctuationPeriod dot;
		public @S(40) TokenList<COBOL_ReportEntry> reportEntries;
	}
	
	public static class COBOL_CopyOrDataDeclaration extends TokenChooser
	{
		public @CHOICE COBOL_Copy_Directive copyBook;
		public @CHOICE COBOL_DataDeclaration declaration;
	}
}
