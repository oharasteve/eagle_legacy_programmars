// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 19, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_IOControlSelect extends TokenSequence
{
	public @S(10) COBOL_Keyword SELECT = new COBOL_Keyword("SELECT");
	public @S(20) COBOL_Identifier_Reference id;
	public @S(30) COBOL_Keyword ASSIGN = new COBOL_Keyword("ASSIGN");
	public @S(40) COBOL_Keyword TO = new COBOL_Keyword("TO");
	public @S(50) COBOL_Literal filename;
	public @S(60) TokenList<COBOL_IOSelectClause> clauses;
	public @S(70) PunctuationPeriod dot;

	public static class COBOL_IOSelectClause extends TokenChooser
	{
		public @CHOICE static class COBOL_IOSelectOrganization extends TokenSequence
		{
			public @S(10) COBOL_Keyword ORGANIZATION = new COBOL_Keyword("ORGANIZATION");
			public @S(20) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(30) @OPT COBOL_Keyword INDEXED = new COBOL_Keyword("INDEXED");
			public @S(40) @OPT COBOL_Keyword LINE = new COBOL_Keyword("LINE");
			public @S(50) @OPT COBOL_Keyword SEQUENTIAL = new COBOL_Keyword("SEQUENTIAL");
		}

		public @CHOICE static class COBOL_IOSelectAccess extends TokenSequence
		{
			public @S(10) COBOL_Keyword ACCESS = new COBOL_Keyword("ACCESS");
			public @S(20) COBOL_Keyword MODE = new COBOL_Keyword("MODE");
			public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_Keyword DYNAMIC = new COBOL_Keyword("DYNAMIC");
		}

		public @CHOICE static class COBOL_IOSelectRecord extends TokenSequence
		{
			public @S(10) @OPT COBOL_Keyword ALTERNATE = new COBOL_Keyword("ALTERNATE");
			public @S(20) COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
			public @S(30) COBOL_Keyword KEY = new COBOL_Keyword("KEY");
			public @S(40) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(50) COBOL_Identifier_Reference id;
			public @S(60) @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
			public @S(70) @OPT COBOL_Keyword DUPLICATES = new COBOL_Keyword("DUPLICATES");
		}

		public @CHOICE static class COBOL_IOSelectFile extends TokenSequence
		{
			public @S(10) COBOL_Keyword FILE = new COBOL_Keyword("FILE");
			public @S(20) COBOL_Keyword STATUS = new COBOL_Keyword("STATUS");
			public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(40) COBOL_Identifier_Reference id;
		}
	}
}
