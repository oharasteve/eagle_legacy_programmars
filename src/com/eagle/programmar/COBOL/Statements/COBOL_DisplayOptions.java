// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement.COBOL_DisplayColumn;
import com.eagle.programmar.COBOL.Statements.COBOL_DisplayStatement.COBOL_DisplayLine;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_DisplayOptions extends TokenChooser
{
	public @CHOICE COBOL_DisplayLine XXline;
	public @CHOICE COBOL_DisplayColumn XXcolumn;
	public @CHOICE PunctuationComma XXcomma;

	public @CHOICE static class COBOL_DisplayUpon extends TokenSequence
	{
		public @S(10) COBOL_Keyword UPON = new COBOL_Keyword("UPON");
		public @S(20) COBOL_Identifier_Reference upon;
	}

	public @FIRST static class COBOL_DisplayAt extends TokenSequence
	{
		public @S(10) COBOL_Keyword AT = new COBOL_Keyword("AT");
		public @S(20) @OPT COBOL_DisplayLine line;
		public @S(30) @OPT COBOL_DisplayColumn column;
	}

	public @CHOICE static class COBOL_DisplayWith extends TokenSequence
	{
		public @S(10) COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @S(20) TokenList<COBOL_DisplayColor> colors;

		public static class COBOL_DisplayColor extends TokenSequence
		{
			public @S(10) COBOL_KeywordChoice color = new COBOL_KeywordChoice("FOREGROUND-COLOR",
					"BACKGROUND-COLOR", "HBCKGROUND-COLOR", "HIGHLIGHT", "REVERSE-VIDEO");
			public @S(20) @OPT COBOL_Number fg;
		}
	}

	public @CHOICE static class COBOL_DisplayWithNoAdvancing extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @S(20) COBOL_Keyword NO = new COBOL_Keyword("NO");
		public @S(30) COBOL_Keyword ADVANCING = new COBOL_Keyword("ADVANCING");
	}

	public @CHOICE static class COBOL_DisplayWithControl extends TokenSequence
	{
		public @S(10) COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @S(20) COBOL_Keyword CONTROL = new COBOL_Keyword("CONTROL");
		public @S(30) COBOL_Identifier_Reference control;
	}

	public @CHOICE static class COBOL_DisplayLines extends TokenSequence
	{
		public @S(10) COBOL_Keyword LINES = new COBOL_Keyword("LINES");
		public @S(20) COBOL_Expression lines;
	}

	public @CHOICE static class COBOL_DisplaySize extends TokenSequence
	{
		public @S(10) COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
		public @S(20) COBOL_Expression size;
	}
}
