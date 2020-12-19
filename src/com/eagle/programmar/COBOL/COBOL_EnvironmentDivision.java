// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Class_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_EnvironmentDivision extends TokenSequence
{
	public @S(10) COBOL_Keyword ENVIRONMENT = new COBOL_Keyword("ENVIRONMENT");
	public @S(20) COBOL_Keyword DIVISION = new COBOL_Keyword("DIVISION");
	public @S(30) PunctuationPeriod dot;
	public @S(40) @OPT TokenList<COBOL_Comment> comments1;
	public @S(50) @OPT COBOL_ClassControl classControl;
	public @S(60) @OPT COBOL_ConfigurationSection configuration;
	public @S(70) @OPT COBOL_InputOutputSection inputOutput;
	public @S(80) @OPT COBOL_SpecialNames specialNames;
	
	public static class COBOL_ClassControl extends TokenSequence
	{
		public @S(10) COBOL_Keyword CLASSCONTROL = new COBOL_Keyword("CLASS-CONTROL");
		public @S(20) PunctuationPeriod dot1;
		public @S(30) TokenList<COBOL_ClassControlIs> controlIsList;
		public @S(40) PunctuationPeriod dot2;
		
		public static class COBOL_ClassControlIs extends TokenSequence
		{
			public @S(10) COBOL_Class_Definition classDef;
			public @S(20) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(30) COBOL_Keyword CLASS = new COBOL_Keyword("CLASS");
			public @S(40) COBOL_Literal name;
		}
	}
	
	public static class COBOL_ConfigurationSection extends TokenSequence
	{
		public @S(10) COBOL_Keyword CONFIGURATION = new COBOL_Keyword("CONFIGURATION");
		public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public @S(30) PunctuationPeriod dot;
		public @S(40) @OPT TokenList<COBOL_Comment> comments1;
		public @S(50) @OPT TokenList<COBOL_Computer> computers;
		public @S(60) @OPT COBOL_SpecialNames specialNames;
		public @S(70) @OPT TokenList<COBOL_Comment> comments2;
		
		public static class COBOL_Computer extends TokenSequence
		{
			public @S(10) COBOL_KeywordChoice source = new COBOL_KeywordChoice("SOURCE-COMPUTER", "OBJECT-COMPUTER");
			public @S(20) PunctuationPeriod dot1;
			public @S(30) COBOL_Identifier_Reference id;
			public @S(40) PunctuationPeriod dot2;
		}
	}
	
	public static class COBOL_InputOutputSection extends TokenSequence
	{
		public @S(10) COBOL_Keyword INPUTOUTPUT = new COBOL_Keyword("INPUT-OUTPUT");
		public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
		public @S(30) PunctuationPeriod dot;
		public @S(40) @OPT TokenList<COBOL_Comment> comments1;
		public @S(50) @OPT COBOL_FileControl fileControl;
		public @S(60) @OPT COBOL_IOControl ioControl;
	}
	
	public static class COBOL_IOControl extends TokenSequence
	{
		public @S(10) COBOL_Keyword IOCONTROL = new COBOL_Keyword("I-O-CONTROL");
		public @S(20) PunctuationPeriod dot;
		public @S(30) COBOL_IOControlSame controlSame;

		public static class COBOL_IOControlSame extends TokenSequence
		{
			public @S(10) COBOL_Keyword SAME = new COBOL_Keyword("SAME");
			public @S(20) COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
			public @S(30) COBOL_Keyword AREA = new COBOL_Keyword("AREA");
			public @S(40) COBOL_Keyword FOR = new COBOL_Keyword("FOR");
			public @S(50) COBOL_Keyword NETWORK = new COBOL_Keyword("NETWORK");
			public @S(60) COBOL_Keyword SHARED = new COBOL_Keyword("SHARED");
			public @S(70) PunctuationPeriod dot;
		}
	}
}
