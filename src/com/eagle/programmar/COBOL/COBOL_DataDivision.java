// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
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
		public @CHOICE COBOL_FileDescriptor fileDescriptor;
	}
	
	public static class COBOL_WorkingStorageSection extends TokenSequence implements EagleRunnable
	{
		public @S(10) COBOL_Keyword WORKINGSTORAGE = new COBOL_Keyword("WORKING-STORAGE");
		public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public @S(30) PunctuationPeriod dot;
		public @S(40) TokenList<COBOL_CopyOrDataDeclaration> dataDeclarations;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			for (COBOL_CopyOrDataDeclaration decl : dataDeclarations._elements)
			{
				interpreter.tryToInterpret(decl.getWhich());
			}
		}
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
