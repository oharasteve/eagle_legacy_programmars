// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Program_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_CommentToEndOfLine;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_IdentificationDivision extends TokenSequence
{
	public @S(10) COBOL_IdentificationHeader header;
	public @S(20) @OPT COBOL_IdentificationIsInitial isInitial;
	public @S(30) @OPT TokenList<COBOL_Comment> comments1;
	public @S(40) @OPT TokenList<COBOL_IdentificationEntry> entries;
	public @S(50) @OPT TokenList<COBOL_Comment> comments2;

	public static class COBOL_IdentificationIsInitial extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(20) @OPT COBOL_Keyword INITIAL = new COBOL_Keyword("INITIAL");
		public @S(30) @OPT COBOL_Keyword PROGRAM = new COBOL_Keyword("PROGRAM");
		public @S(40) @OPT PunctuationPeriod dot;
	}

	public static class COBOL_IdentificationHeader extends TokenChooser
	{
		public @CHOICE COBOL_ProgramId programId;

		public @CHOICE static class COBOL_IdentificationPresent extends TokenSequence
		{
			public @S(10) COBOL_KeywordChoice identification = new COBOL_KeywordChoice("IDENTIFICATION", "ID");
			public @S(20) COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
			public @S(30) PunctuationPeriod dot;
			public @S(40) @OPT TokenList<COBOL_Comment> comments;
			public @S(50) @OPT COBOL_ProgramId programId;
		}
	}
	
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
		
		public @CHOICE static class COBOL_IdentificationRepository extends TokenSequence
		{
			public @S(10) COBOL_Keyword REPOSITORY = new COBOL_Keyword("REPOSITORY");
			public @S(20) PunctuationPeriod dot;
			public @S(30) TokenList<COBOL_RepositoryEntry> entries;
			
			public static class COBOL_RepositoryEntry extends TokenSequence
			{
				public @S(10) COBOL_Keyword CLASS = new COBOL_Keyword("CLASS");
				public @S(20) COBOL_Keyword SYS_CONSOLE = new COBOL_Keyword("SYS-CONSOLE");
				public @S(30) COBOL_Keyword AS = new COBOL_Keyword("AS");
				public @S(40) COBOL_Literal literal;
				public @S(50) PunctuationPeriod dot;
			}
		}
	}
	
	public static class COBOL_Program_Subname extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) COBOL_Keyword CBL = new COBOL_Keyword("CBL");
	}
}
