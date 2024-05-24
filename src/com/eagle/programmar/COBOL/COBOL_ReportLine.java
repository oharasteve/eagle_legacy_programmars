// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_ReportLine_Definition;
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

public class COBOL_ReportLine extends TokenSequence
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