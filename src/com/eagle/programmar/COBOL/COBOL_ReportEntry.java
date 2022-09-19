// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_ReportLine_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Report_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Level;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_ReportEntry extends TokenSequence
{
	public @S(10) COBOL_ReportDescription rd;
	public @S(20) TokenList<COBOL_ReportDataLine> dataLines;
	
	public static class COBOL_ReportDescription extends TokenSequence
	{
		public @S(10) COBOL_Keyword RD = new COBOL_Keyword("RD");
		public @S(20) COBOL_Report_Definition reportName;
		public @S(30) TokenList<COBOL_ReportDescriptionEntry> descriptionEntries;
		public @S(40) PunctuationPeriod dot;
	}
	
	public static class COBOL_ReportDataLine extends TokenSequence
	{
		public @S(10) COBOL_Level O1;
		public @S(20) @OPT COBOL_ReportLine_Definition id;
		public @S(30) COBOL_Keyword TYPE = new COBOL_Keyword("TYPE");
		public @S(40) COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(50) COBOL_ReportDataLineType lineType;
		public @S(60) @OPT COBOL_ReportNextGroup next;
		public @S(70) PunctuationPeriod dot;
		public @S(80) TokenList<COBOL_ReportLine> reportLines;
	}
	
	public static class COBOL_ReportNextGroup extends TokenSequence
	{
		public @S(10) COBOL_Keyword NEXT = new COBOL_Keyword("NEXT");
		public @S(20) COBOL_Keyword GROUP = new COBOL_Keyword("GROUP");
		public @S(30) COBOL_Keyword PLUS = new COBOL_Keyword("PLUS");
		public @S(40) COBOL_Number count;
	}
}
