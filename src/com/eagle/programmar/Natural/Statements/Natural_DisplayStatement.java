// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_FunctionCall;
import com.eagle.programmar.Natural.Natural_Option;
import com.eagle.programmar.Natural.Natural_SystemVariable;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_Comment;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.programmar.Natural.Terminals.Natural_Tab;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSlash;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Natural_DisplayStatement extends TokenSequence
{
	public @S(10) @DOC("sm/display.htm") Natural_Keyword DISPLAY = new Natural_Keyword("DISPLAY");
	public @S(20) @OPT Natural_Keyword NOTITLE = new Natural_Keyword("NOTITLE");
	public @S(30) @OPT Natural_DisplayFunctions displayFns;
	public @S(40) TokenList<Natural_DisplayElement> displayElement;

	public static class Natural_DisplayFunctions extends TokenSequence
	{
		public @S(10) @OPT Natural_Keyword AND = new Natural_Keyword("AND");
		public @S(20) @OPT Natural_Keyword GIVE = new Natural_Keyword("GIVE");
		public @S(30) @OPT Natural_Keyword SYSTEM = new Natural_Keyword("SYSTEM");
		public @S(40) Natural_Keyword FUNCTIONS = new Natural_Keyword("FUNCTIONS");
	}

	public static class Natural_DisplayElement extends TokenChooser
	{
		public @CHOICE Natural_Option XXdisplayOption;
		public @CHOICE PunctuationSlash XXslash;
		public @CHOICE Natural_Tab XXtab;
		public @CHOICE Natural_Variable XXvar;
		public @CHOICE Natural_Comment XXcomment;
		public @CHOICE Natural_SystemVariable XXsysVar;
		public @CHOICE Natural_KeywordChoice XXoption = new Natural_KeywordChoice("HORIZ", "UNDERLINED", "TRAILER");
		public @CHOICE Natural_FunctionCall XXfnCall;
		public @CHOICE Natural_DisplayParameter XXparameter;

		public @CHOICE static class Natural_Justified extends TokenSequence
		{
			public @S(10) Natural_Keyword LEFT = new Natural_Keyword("LEFT");
			public @S(20) @OPT Natural_Keyword JUSTIFIED = new Natural_Keyword("JUSTIFIED");
		}

		public @CHOICE static class Natural_FieldPositioning extends TokenSequence
		{
			public @S(10) Natural_Keyword T = new Natural_Keyword("T");
			public @S(20) PunctuationStar star;
			public @S(30) Natural_Variable var;
		}

		public @CHOICE static class Natural_FieldAndLinePositioning extends TokenSequence
		{
			public @S(10) Natural_Keyword P = new Natural_Keyword("P");
			public @S(20) PunctuationStar star;
			public @S(30) Natural_Variable var;
		}

		public @CHOICE static class Natural_Vertical extends TokenSequence
		{
			public @S(10) Natural_Keyword VERT = new Natural_Keyword("VERT");
			public @S(20) @OPT Natural_Keyword AS = new Natural_Keyword("AS");
			public @S(30) Natural_Literal literal;
		}

		public @CHOICE static class Natural_VerticalCaptioned extends TokenSequence
		{
			public @S(10) Natural_Keyword VERT = new Natural_Keyword("VERT");
			public @S(20) @OPT Natural_Keyword AS = new Natural_Keyword("AS");
			public @S(30) Natural_Keyword CAPTIONED = new Natural_Keyword("CAPTIONED");
		}

		public @CHOICE static class Natural_Display_Literal extends TokenSequence
		{
			public @S(10) Natural_Literal literal;
			public @S(20) @OPT Natural_LiteralCount count;

			public static class Natural_LiteralCount extends TokenSequence
			{
				public @S(10) PunctuationLeftParen leftParen;
				public @S(20) Natural_Number count;
				public @S(30) PunctuationRightParen rightParen;
			}
		}

		public @CHOICE static class Natural_Relative_Positioning extends TokenSequence
		{
			public @S(10) Natural_Number lines;
			public @S(20) PunctuationSlash slash;
			public @S(30) Natural_Number column;
		}
	}
}
