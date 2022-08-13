// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_ReportDescriptionEntry extends TokenChooser
{
	public @CHOICE static class COBOL_ReportDescriptionControls extends TokenSequence
	{
		public @S(10) COBOL_Keyword CONTROLS = new COBOL_Keyword("CONTROLS");
		public @S(20) COBOL_Keyword ARE = new COBOL_Keyword("ARE");
		public @S(30) @OPT COBOL_Keyword FINAL = new COBOL_Keyword("FINAL");
		public @S(40) TokenList<COBOL_Identifier_Reference> ids;
	}

	public @CHOICE static class COBOL_ReportDescriptionPageLimit extends TokenSequence
	{
		public @S(10) COBOL_Keyword PAGE = new COBOL_Keyword("PAGE");
		public @S(20) COBOL_Keyword LIMIT = new COBOL_Keyword("LIMIT");
		public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(40) COBOL_Number count;
	}
	
	public @CHOICE static class COBOL_ReportDescriptionHeading extends TokenSequence
	{
		public @S(10) COBOL_Keyword HEADING = new COBOL_Keyword("HEADING");
		public @S(20) COBOL_Number count;
	}
	
	public @CHOICE static class COBOL_ReportDescriptionFirstDetail extends TokenSequence
	{
		public @S(10) COBOL_Keyword FIRST = new COBOL_Keyword("FIRST");
		public @S(20) COBOL_Keyword DETAIL = new COBOL_Keyword("DETAIL");
		public @S(30) COBOL_Number count;
	}
	
	public @CHOICE static class COBOL_ReportDescriptionLastDetail extends TokenSequence
	{
		public @S(10) COBOL_Keyword LAST = new COBOL_Keyword("LAST");
		public @S(20) COBOL_Keyword DETAIL = new COBOL_Keyword("DETAIL");
		public @S(30) COBOL_Number count;
	}
	
	public @CHOICE static class COBOL_ReportDescriptionFooting extends TokenSequence
	{
		public @S(10) COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
		public @S(20) COBOL_Number count;
	}
}