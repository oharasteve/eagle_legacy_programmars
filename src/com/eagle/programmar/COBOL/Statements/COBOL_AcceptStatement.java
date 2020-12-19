// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_AcceptStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsacce.htm") COBOL_Keyword ACCEPT = new COBOL_Keyword("ACCEPT");
	public @S(20) @OPT COBOL_AcceptPosition position;
	public @S(30) COBOL_Identifier_Reference var;
	public @S(40) @OPT COBOL_Subscript subscript;
	public @S(50) @OPT TokenList<COBOL_AcceptOption> options;
	
	public static class COBOL_AcceptPosition extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) COBOL_Expression line;
		public @S(30) PunctuationComma comma;
		public @S(40) COBOL_Expression column;
		public @S(50) PunctuationRightParen rightParen;
	}
	
	public static class COBOL_AcceptOption extends TokenChooser
	{
		public @CHOICE COBOL_KeywordChoice option = new COBOL_KeywordChoice(
				"AUTO",
				"AUTO-SKIP",
				"FULL",
				"NO-ECHO",
				"PROMPT",
				"SECURE",
				"UPDATE"
				);

		public @CHOICE static class COBOL_AcceptFrom extends TokenSequence
		{
			public @S(10) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
			public @S(20) COBOL_KeywordChoice time = new COBOL_KeywordChoice("DATE", "DAY", "TIME");
			public @S(30) @OPT COBOL_KeywordChoice format = new COBOL_KeywordChoice("YYYYMMDD", "YYYYDDD");
		}

		public @CHOICE static class COBOL_AcceptCommand extends TokenSequence
		{
			public @S(10) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
			public @S(20) COBOL_Keyword COMMANDLINE = new COBOL_Keyword("COMMAND-LINE");
		}

		public @CHOICE static class COBOL_AcceptAt extends TokenSequence
		{
			public @S(10) COBOL_Keyword AT = new COBOL_Keyword("AT");
			public @S(20) @OPT COBOL_Keyword LINE = new COBOL_Keyword("LINE");
			public @S(30) COBOL_Expression location;
		}
			
		public @CHOICE static class COBOL_AcceptColumn extends TokenSequence
		{
			public @S(10) COBOL_Keyword COLUMN = new COBOL_Keyword("COLUMN");
			public @S(20) COBOL_Expression column;
		}
		
		public @CHOICE static class COBOL_AcceptWithColors extends TokenSequence
		{
			public @S(10) COBOL_Keyword WITH = new COBOL_Keyword("WITH");
			public @S(20) @OPT COBOL_Keyword UPDATE = new COBOL_Keyword("UPDATE");
			public @S(30) TokenList<COBOL_AcceptColor> colors;
			
			public static class COBOL_AcceptColor extends TokenSequence
			{
				public @S(10) COBOL_KeywordChoice color = new COBOL_KeywordChoice(
						"AUTO", "AUTO-SKIP", "FOREGROUND-COLOR", "BACKGROUND-COLOR", "HIGHLIGHT");
				public @S(20) @OPT COBOL_Number fg;
			}
		}
	}
}
