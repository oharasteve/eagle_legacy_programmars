// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_ReportLine_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Report_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Level;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Picture;
import com.eagle.tokens.TokenChooser;
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
		
		public static class COBOL_ReportDescriptionEntry extends TokenChooser
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
	}
	
	public static class COBOL_ReportDataLine extends TokenSequence
	{
		public @S(10) COBOL_Level O1;
		public @S(20) @OPT COBOL_ReportLine_Definition id;
		public @S(30) COBOL_Keyword TYPE = new COBOL_Keyword("TYPE");
		public @S(40) COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(50) COBOL_ReportDataLineType lineType;
		public @S(60) PunctuationPeriod dot;
		
		public @S(70) TokenList<COBOL_ReportLine> reportLines;
		
 		public static class COBOL_ReportDataLineType extends TokenChooser
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
				public @S(50) @OPT COBOL_Keyword NEXT = new COBOL_Keyword("NEXT");
				public @S(60) @OPT COBOL_Keyword GROUP = new COBOL_Keyword("GROUP");
				public @S(70) @OPT COBOL_Keyword PLUS = new COBOL_Keyword("PLUS");
				public @S(80) @OPT COBOL_Number count;
			}
		}
 		
 		public static class COBOL_ReportLine extends TokenSequence
 		{
 			public @S(10) COBOL_Level O2;
 			public @S(20) COBOL_Keyword LINE = new COBOL_Keyword("LINE");
 			public @S(30) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
 			public @S(40) @OPT COBOL_Keyword PLUS = new COBOL_Keyword("PLUS");
			public @S(50) @OPT COBOL_Number line;
 			public @S(60) PunctuationPeriod dot;
 			
 			public @S(70) TokenList<COBOL_ColumnLine> columnLines;
 			
 	 		public static class COBOL_ColumnLine extends TokenSequence
 	 		{
 	 			public @S(10) COBOL_Level O3;
				public @S(20) @OPT COBOL_ReportLine_Definition id;
 	 			public @S(30) COBOL_Keyword COLUMN = new COBOL_Keyword("COLUMN");
 				public @S(40) @OPT COBOL_Number column;

 				public @S(50) COBOL_KeywordChoice pic = new COBOL_KeywordChoice("PIC", "PICTURE");
 				public @S(60) COBOL_Picture picture;
 				public @S(70) TokenList<COBOL_ColumnLineClause> columnClauses;
 				public @S(80) PunctuationPeriod dot;
 	 			
 	 			public static class COBOL_ColumnLineClause extends TokenChooser
 	 			{
 	 				public @CHOICE static class COBOL_ColumnLineSource extends TokenSequence
 	 				{
	 	 				public @S(10) COBOL_Keyword SOURCE = new COBOL_Keyword("SOURCE");
	 					public @S(20) COBOL_Identifier_Reference id;
	 					public @S(30) @OPT COBOL_Subscript subscript;
 	 				}

 	 				public @CHOICE static class COBOL_SourceClause extends TokenSequence
 	 				{
 	 					public @S(10) COBOL_Keyword SOURCE = new COBOL_Keyword("SOURCE");
 	 					public @S(20) COBOL_Keyword PAGECOUNTER = new COBOL_Keyword("PAGE-COUNTER");
 	 				}

 	 				public @CHOICE static class COBOL_ColumnLineGroup extends TokenSequence
 	 				{
	 	 				public @S(10) COBOL_Keyword GROUP = new COBOL_Keyword("GROUP");
	 	 				public @S(20) COBOL_Keyword INDICATE = new COBOL_Keyword("INDICATE");
 	 				}
 	 				
 	 				public @CHOICE static class COBOL_ColumnLineValue extends TokenSequence
 	 				{
	 	 				public @S(10) COBOL_Keyword VALUE = new COBOL_Keyword("VALUE");
	 	 				public @S(20) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
	 	 				public @S(30) COBOL_Literal val;
 	 				}
 	 				
 	 				public @CHOICE static class COBOL_ColumnLineSum extends TokenSequence
 	 				{
	 	 				public @S(10) COBOL_Keyword SUM = new COBOL_Keyword("SUM");
	 					public @S(20) COBOL_Identifier_Reference id;
 	 				}
 	 			}
 	 		}
 		}
	}
}
