// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_PutStatement extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @OPT PLI_Label label;
	public @S(20) @DOC("7.45") PLI_Keyword PUT = new PLI_Keyword("PUT");
	public @S(30) @OPT PLI_PutFile file;
	public @S(40) @OPT PLI_Keyword SKIP = new PLI_Keyword("SKIP");
	public @S(50) @OPT PLI_PutFormat_Count count;
	public @S(60) @OPT PLI_PutString string;
	public @S(70) @OPT PLI_KeywordChoice dataOrEditOrList = new PLI_KeywordChoice("DATA", "EDIT", "LIST");

	public @S(80) @OPT PLI_PutValues values;

	public @S(90) @OPT PLI_PutFormat putFormat;

	public @S(100) PunctuationSemicolon semicolon;

	public static class PLI_PutFile extends TokenSequence
	{
		public @S(10) PLI_Keyword FILE = new PLI_Keyword("FILE");
		public @S(20) PunctuationLeftParen leftParen1;
		public @S(30) PLI_Identifier_Reference file;
		public @S(40) PunctuationRightParen rightParen1;
	}

	public static class PLI_PutString extends TokenSequence
	{
		public @S(10) PLI_Keyword STRING = new PLI_Keyword("STRING");
		public @S(20) PunctuationLeftParen leftParen1;
		public @S(30) PLI_Identifier_Reference var;
		public @S(40) PunctuationRightParen rightParen1;
	}

	public static class PLI_PutValues extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen1;
		public @S(20) SeparatedList<PLI_Expression, PunctuationComma> exprs;
		public @S(30) PunctuationRightParen rightParen1;
	}

	public static class PLI_PutFormat extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen2;
		public @S(20) PLI_PutEditFormat editFormat;
		public @S(30) @OPT TokenList<PLI_PutMoreFormats> moreFmts;
		public @S(40) PunctuationRightParen rightParen2;

		public static class PLI_PutEditFormat extends TokenChooser
		{
			public @CHOICE PLI_Keyword XXSKIP = new PLI_Keyword("SKIP");
			public @CHOICE PLI_Literal XXliteral;

			public @CHOICE static class PLI_PutMultipleFormats extends TokenSequence
			{
				public @S(10) PLI_Number number;
				public @S(20) PLI_PutFormat format;
			}

			public @CHOICE static class PLI_PutFormat_A extends TokenSequence
			{
				public @S(10) @OPT PLI_Number number;
				public @S(20) PLI_Keyword A = new PLI_Keyword("A");
				public @S(30) @OPT PLI_PutFormat_Count formatCount;
			}

			public @CHOICE static class PLI_PutFormat_E extends TokenSequence
			{
				public @S(10) @OPT PLI_Number number;
				public @S(20) PLI_Keyword E = new PLI_Keyword("E");
				public @S(30) PLI_PutFormat_Count formatCount;
			}

			public @CHOICE static class PLI_PutFormat_F extends TokenSequence
			{
				public @S(10) @OPT PLI_Number number;
				public @S(20) PLI_Keyword F = new PLI_Keyword("F");
				public @S(30) PLI_PutFormat_Count formatCount;
			}

			public @CHOICE static class PLI_PutFormat_R extends TokenSequence
			{
				public @S(10) PLI_Keyword R = new PLI_Keyword("R");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) PLI_Identifier_Reference label;
				public @S(40) PunctuationRightParen rightParen;
			}

			public @CHOICE static class PLI_PutFormat_X extends TokenSequence
			{
				public @S(10) @OPT PLI_Number number;
				public @S(20) PLI_Keyword X = new PLI_Keyword("X");
				public @S(30) PLI_PutFormat_Count formatCount;
			}
		}

		public static class PLI_PutMoreFormats extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) PLI_PutEditFormat editFormat;
		}
	}

	public static class PLI_PutFormat_Count extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) PLI_Expression expr;
		public @S(30) @OPT PLI_PutFormat_SecondCount secondCount;
		public @S(40) PunctuationRightParen rightParen;

		public static class PLI_PutFormat_SecondCount extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) PLI_Expression expr;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (values.isPresent())
		{
			for (int i = 0; i < values.exprs.getPrimaryCount(); i++)
			{
				PLI_Expression expr = values.exprs.getPrimaryElement(i);
				String val = interpreter.getStrValue(expr);
				System.out.println(val);
			}
		}
	}
}
