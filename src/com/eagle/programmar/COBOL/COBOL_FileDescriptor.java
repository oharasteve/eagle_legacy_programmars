// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Symbols.COBOL_File_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_FileDescriptor extends TokenSequence
{
	public @S(10) COBOL_KeywordChoice fd_sd = new COBOL_KeywordChoice("FD", "SD");
	public @S(20) COBOL_File_Definition id;
	public @S(30) @OPT COBOL_FD_ReportExternal reportExternal;
	public @S(40) @OPT COBOL_FD_RecordContains recordContains;
	public @S(50) @OPT COBOL_FD_RecordIsVarying recordIsVarying;
	public @S(60) @OPT COBOL_FD_LabelRecordStandard labelRecordStandard;
	public @S(70) @OPT COBOL_FD_LabelRecordOmitted labelRecordOmitted;
	public @S(80) @OPT COBOL_FD_RecordingMode recordingMode;
	public @S(90) PunctuationPeriod dot;
	public @S(100) @OPT TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;

	public static class COBOL_FD_ReportExternal extends TokenChooser
	{
		public @CHOICE static class COBOL_FD_ReportIs extends TokenSequence
		{
			public @S(10) COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
			public @S(20) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(30) @OPT COBOL_Keyword EXTERNAL = new COBOL_Keyword("EXTERNAL");
			public @S(40) @OPT COBOL_Identifier_Reference reportId;
		}

		public @CHOICE static class COBOL_FD_IsExternal extends TokenSequence
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

	public static class COBOL_FD_RecordIsVarying extends TokenSequence
	{
		public @S(10) COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
		public @S(20) COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(30) COBOL_Keyword VARYING = new COBOL_Keyword("VARYING");
		public @S(40) COBOL_Keyword IN = new COBOL_Keyword("IN");
		public @S(50) COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
		public @S(60) COBOL_Keyword DEPENDING = new COBOL_Keyword("DEPENDING");
		public @S(70) COBOL_Keyword ON = new COBOL_Keyword("ON");
		public @S(80) COBOL_Identifier_Reference what;
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
				public @CHOICE COBOL_Identifier_Reference XXfileRef;
				public @CHOICE COBOL_Literal XXfileName;
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