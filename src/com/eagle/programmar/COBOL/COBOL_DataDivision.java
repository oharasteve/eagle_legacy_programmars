// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_WorkingStorage.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformer;

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
		public @CHOICE COBOL_Comment XXcomment;
		public @CHOICE COBOL_FileSection XXfileSection;
		public @CHOICE COBOL_WorkingStorage XXworkingStorageSection;
		public @CHOICE COBOL_LocalStorageSection XXlocalStorageSection;
		public @CHOICE COBOL_ScreenSection XXscreenSection;
		public @CHOICE COBOL_LinkageSection XXlinkageSection;
		public @CHOICE COBOL_ReportSection XXreportSection;
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
		public @CHOICE COBOL_Copy_Directive XXcopyDirective;
		public @CHOICE COBOL_Comment XXcomment;
		public @CHOICE COBOL_FileDescriptor XXfileDescriptor;
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

	public void transform(EagleTransformer transformer, EagleGenerator generator)
	{
		for (COBOL_DataSection section : sections._elements)
		{
			AbstractToken which = section.getWhich();
			if (which instanceof COBOL_WorkingStorage)
			{
				COBOL_WorkingStorage work = (COBOL_WorkingStorage) which;
				work.transform(transformer, generator);
			}
		}
	}
}
