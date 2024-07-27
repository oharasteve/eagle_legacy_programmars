// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_FileSelect extends TokenSequence
{
	public @S(10) COBOL_Keyword SELECT = new COBOL_Keyword("SELECT");
	public @S(20) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
	public @S(30) @OPT COBOL_Keyword OPTIONAL = new COBOL_Keyword("OPTIONAL");
	public @S(40) COBOL_Identifier_Reference selectWhat;
	public @S(50) COBOL_Keyword ASSIGN = new COBOL_Keyword("ASSIGN");
	public @S(60) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
	public @S(70) COBOL_AssignTo assignTo;
	public @S(80) @OPT TokenList<COBOL_SelectClause> selectClauses;
	public @S(90) PunctuationPeriod dot;

	public static class COBOL_AssignTo extends TokenChooser
	{
		public @CHOICE COBOL_Literal XXassignLiteral;
		public @CHOICE COBOL_Keyword XXDISK = new COBOL_Keyword("DISK");
		public @CHOICE COBOL_Identifier_Reference XXdataRef;
	}

	public static class COBOL_SelectClause extends TokenChooser
	{
		public @CHOICE COBOL_Comment XXcomment;

		public @CHOICE static class COBOL_SelectOrganization extends TokenSequence
		{
			public @S(10) COBOL_Keyword ORGANIZATION = new COBOL_Keyword("ORGANIZATION");
			public @S(20) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(30) @OPT COBOL_Keyword LINE = new COBOL_Keyword("LINE");
			public @S(40) COBOL_KeywordChoice organization = new COBOL_KeywordChoice("INDEXED", "SEQUENTIAL",
					"RELATIVE");
		}

		public @CHOICE static class COBOL_SelectAccess extends TokenSequence
		{
			public @S(10) COBOL_Keyword ACCESS = new COBOL_Keyword("ACCESS");
			public @S(20) @OPT COBOL_Keyword MODE = new COBOL_Keyword("MODE");
			public @S(30) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_KeywordChoice access = new COBOL_KeywordChoice("SEQUENTIAL", "DYNAMIC", "RANDOM");
		}

		public @CHOICE static class COBOL_SelectRecord extends TokenSequence
		{
			public @S(10) COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
			public @S(20) @OPT COBOL_Keyword KEY = new COBOL_Keyword("KEY");
			public @S(30) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_Identifier_Reference id;
			public @S(50) @OPT COBOL_SelectRecordEquals selectEquals;

			public static class COBOL_SelectRecordEquals extends TokenSequence
			{
				public @S(10) PunctuationEquals equals;
				public @S(20) TokenList<COBOL_Identifier_Reference> ids;
			}
		}

		public @CHOICE static class COBOL_SelectAlternate extends TokenSequence
		{
			public @S(10) COBOL_Keyword ALTERNATE = new COBOL_Keyword("ALTERNATE");
			public @S(20) @OPT COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
			public @S(30) @OPT COBOL_Keyword KEY = new COBOL_Keyword("KEY");
			public @S(40) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(50) COBOL_Identifier_Reference id;
			public @S(60) @OPT COBOL_SelectAlternates selectAlternates;
			public @S(70) @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
			public @S(80) @OPT COBOL_Keyword DUPLICATES = new COBOL_Keyword("DUPLICATES");

			public static class COBOL_SelectAlternates extends TokenSequence
			{
				public @S(10) PunctuationEquals equals;
				public @S(20) TokenList<COBOL_Identifier_Reference> variables;
			}
		}

		public @CHOICE static class COBOL_SelectFile extends TokenSequence
		{
			public @S(10) @OPT COBOL_Keyword FILE = new COBOL_Keyword("FILE");
			public @S(20) COBOL_Keyword STATUS = new COBOL_Keyword("STATUS");
			public @S(30) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_Identifier_Reference id;
		}

		public @CHOICE static class COBOL_SelectRelative extends TokenSequence
		{
			public @S(10) COBOL_Keyword RELATIVE = new COBOL_Keyword("RELATIVE");
			public @S(20) @OPT COBOL_Keyword KEY = new COBOL_Keyword("KEY");
			public @S(30) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_Identifier_Reference id;
		}

		// LOCK MANUAL WITH LOCK ON MULTIPLE RECORDS
		public @CHOICE static class COBOL_SelectLock extends TokenSequence
		{
			public @S(10) COBOL_KeywordChoice LOCK = new COBOL_KeywordChoice("LOCK", "LOCKING");
			public @S(20) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(30) COBOL_KeywordChoice EXCLUSIVE = new COBOL_KeywordChoice("EXCLUSIVE", "AUTOMATIC", "MANUAL");
			public @S(40) @OPT COBOL_SelectLockWith lockWith;

			public static class COBOL_SelectLockWith extends TokenSequence
			{
				public @S(10) COBOL_Keyword WITH = new COBOL_Keyword("WITH");
				public @S(20) COBOL_Keyword LOCK = new COBOL_Keyword("LOCK");
				public @S(30) COBOL_Keyword ON = new COBOL_Keyword("ON");
				public @S(40) COBOL_Keyword MULTIPLE = new COBOL_Keyword("MULTIPLE");
				public @S(50) COBOL_Keyword RECORDS = new COBOL_Keyword("RECORDS");
			}
		}
	}
}