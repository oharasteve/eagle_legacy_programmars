// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class COBOL_ReportDataLineType extends TokenChooser
{
	public @CHOICE static class COBOL_ReportDataLineTypeReportHeading extends TokenSequence
	{
		public @S(10) COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
		public @S(20) COBOL_Keyword HEADING = new COBOL_Keyword("HEADING");
	}
	
	public @CHOICE static class COBOL_ReportDataLineTypeReportFooting extends TokenSequence
	{
		public @S(10) COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
		public @S(20) COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
	}

	public @CHOICE static class COBOL_ReportDataLineTypePageHeading extends TokenSequence
	{
		public @S(10) COBOL_Keyword PAGE = new COBOL_Keyword("PAGE");
		public @S(20) COBOL_Keyword HEADING = new COBOL_Keyword("HEADING");
	}
	
	public @CHOICE static class COBOL_ReportDataLineTypeDetail extends TokenSequence
	{
		public @S(10) COBOL_Keyword DETAIL = new COBOL_Keyword("DETAIL");
	}

	public @CHOICE static class COBOL_ReportDataLineTypePageFooting extends TokenSequence
	{
		public @S(10) COBOL_Keyword PAGE = new COBOL_Keyword("PAGE");
		public @S(20) COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
	}

	public @CHOICE static class COBOL_ReportDataLineTypeControlFooting extends TokenSequence
	{
		public @S(10) COBOL_Keyword CONTROL = new COBOL_Keyword("CONTROL");
		public @S(20) COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
		public @S(30) @OPT COBOL_Keyword FINAL = new COBOL_Keyword("FINAL");
		public @S(40) @OPT COBOL_Identifier_Reference id;
	}
}