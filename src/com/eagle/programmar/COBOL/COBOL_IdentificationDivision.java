// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Program_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_CommentToEndOfLine;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_IdentificationDivision extends TokenSequence
{
	public @S(10) COBOL_KeywordChoice identification = new COBOL_KeywordChoice("IDENTIFICATION", "ID");
	public @S(20) COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
	public @S(30) PunctuationPeriod dot1;
	public @S(40) @OPT TokenList<COBOL_Comment> comments1;
	public @S(50) @OPT COBOL_ProgramId programId;
	
	public @S(60) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
	public @S(70) @OPT COBOL_Keyword INITIAL = new COBOL_Keyword("INITIAL");
	public @S(80) @OPT COBOL_Keyword PROGRAM = new COBOL_Keyword("PROGRAM");
	public @S(90) @OPT PunctuationPeriod dot2;
	
	public @S(100) @OPT TokenList<COBOL_Comment> comments2;

	public @S(110) @OPT TokenList<COBOL_IdentificationEntry> entries;
	
	public @S(120) @OPT TokenList<COBOL_Comment> comments3;
	
	public static class COBOL_ProgramId extends TokenSequence
	{
		public @S(10) COBOL_Keyword PROGRAMID = new COBOL_Keyword("PROGRAM-ID");
		public @S(20) @OPT PunctuationPeriod dot;
		public @S(30) COBOL_Program_Definition programDef;
		public @S(40) @OPT COBOL_Program_Subname subId;
	}
	
	public static class COBOL_IdentificationEntry extends TokenChooser
	{
		public @CHOICE COBOL_SpecialNames specialNames;

		public @CHOICE static class COBOL_IdentificationSimple extends TokenSequence
		{
			public @S(10) COBOL_KeywordChoice entryWord = new COBOL_KeywordChoice(
					"AUTHOR", "INSTALLATION", "DATE-WRITTEN", "DATE-COMPILED", "SECURITY");
			public @S(20) @OPT PunctuationPeriod dot;
			public @S(30) TokenList<COBOL_CommentToEndOfLine> comments;
		}
	}
	
	public static class COBOL_Program_Subname extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) COBOL_Keyword CBL = new COBOL_Keyword("CBL");
	}
}
