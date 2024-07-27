// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_DisplayStatement extends COBOL_AbstractStatement implements EagleRunnable
{
	public @S(10) @DOC("rlpsdisp.htm") COBOL_Keyword DISPLAY = new COBOL_Keyword("DISPLAY");
	public @S(20) @OPT COBOL_DisplayPosition position;
	public @S(30) TokenList<COBOL_DisplayClause> clauses;

	public static class COBOL_DisplayPosition extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT COBOL_Expression x;
		public @S(30) PunctuationComma comma;
		public @S(40) COBOL_Expression y;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static class COBOL_DisplayClause extends TokenSequence
	{
		public @S(10) COBOL_DisplayWhat what;
		public @S(20) @OPT TokenList<COBOL_DisplayOptions> options;

		public static class COBOL_DisplayLine extends TokenSequence
		{
			public @S(10) COBOL_Keyword LINE = new COBOL_Keyword("LINE");
			public @S(20) COBOL_Expression line;
		}

		public static class COBOL_DisplayColumn extends TokenSequence
		{
			public @S(10) COBOL_Keyword COLUMN = new COBOL_Keyword("COLUMN");
			public @S(20) COBOL_Expression column;
		}

		public static class COBOL_DisplayOptions extends TokenChooser
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
	}

	public static class COBOL_DisplayWhat extends TokenSequence implements EagleRunnable
	{
		public @S(10) SeparatedList<COBOL_Expression, PunctuationComma> exprs;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			for (AbstractToken token : exprs._elements)
			{
				if (token instanceof COBOL_Expression)
				{
					COBOL_Expression expr = (COBOL_Expression) token;
					EagleValue result = interpreter.getEagleValue(expr);
					System.out.print(result.toString());
				}
			}
			System.out.println();
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_DisplayClause clause : clauses._elements)
		{
			interpreter.tryToInterpret(clause.what);
		}
	}
}
