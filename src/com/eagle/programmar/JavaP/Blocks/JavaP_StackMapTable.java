// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

package com.eagle.programmar.JavaP.Blocks;

import com.eagle.programmar.JavaP.JavaP_Value;
import com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class JavaP_StackMapTable extends TokenSequence
{
	public @S(10) JavaP_Keyword STACKMAP = new JavaP_Keyword("StackMapTable");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_Keyword NUMBER_OF_ENTRIES = new JavaP_Keyword("number_of_entries");
	public @S(40) PunctuationEquals equals;
	public @S(50) JavaP_Number entries;
	public @S(60) JavaP_EndOfLine eoln;

	public @S(70) @OPT TokenList<JavaP_StackMapFrame> frames;

	public static class JavaP_StackMapFrame extends TokenSequence
	{
		public @S(10) JavaP_Keyword FRAMETYPE = new JavaP_Keyword("frame_type");
		public @S(20) PunctuationEquals equals;
		public @S(30) JavaP_Number frameType;
		public @S(40) @OPT JavaP_Comment comment;
		public @S(50) JavaP_EndOfLine eoln;

		public @S(60) @OPT JavaP_StackMapOffset offsetDelta;
		public @S(70) @OPT JavaP_StackMapLocals locals;
		public @S(80) @OPT JavaP_StackMapStack stack;

		public static class JavaP_StackMapOffset extends TokenSequence
		{
			public @S(10) JavaP_Keyword OFFSETDELTA = new JavaP_Keyword("offset_delta");
			public @S(20) PunctuationEquals equals;
			public @S(30) JavaP_Number delta;
			public @S(40) JavaP_EndOfLine eoln;
		}

		public static class JavaP_StackMapLocals extends TokenSequence
		{
			public @S(10) JavaP_Keyword LOCALS = new JavaP_Keyword("locals");
			public @S(20) PunctuationEquals equals;
			public @S(30) PunctuationLeftBracket leftBracket;
			public @S(40) @OPT SeparatedList<JavaP_StackMapLocal, PunctuationComma> locals;
			public @S(50) PunctuationRightBracket rightBracket;
			public @S(60) JavaP_EndOfLine eoln;

			public static class JavaP_StackMapLocal extends TokenChooser
			{
				public @CHOICE JavaP_KeywordChoice XXtype = new JavaP_KeywordChoice(
						"bogus", "double", "int", "long", "top");

				public @CHOICE static class JavaP_StackMapLocalClass extends TokenSequence
				{
					public @S(10) JavaP_Keyword CLASS = new JavaP_Keyword("class");
					public @S(20) JavaP_Value value;
				}
			}
		}

		public static class JavaP_StackMapStack extends TokenSequence
		{
			public @S(10) JavaP_Keyword STACK = new JavaP_Keyword("stack");
			public @S(20) PunctuationEquals equals;
			public @S(30) PunctuationLeftBracket leftBracket;
			public @S(40) SeparatedList<JavaP_StackMapStackClass, PunctuationComma> stackClasses;
			public @S(50) PunctuationRightBracket rightBracket;
			public @S(60) JavaP_EndOfLine eoln;

			public static class JavaP_StackMapStackClass extends TokenSequence
			{
				public @S(10) @OPT JavaP_KeywordChoice CLASS = new JavaP_KeywordChoice("class", "uninitialized");
				public @S(20) @OPT JavaP_Value value;
			}
		}
	}
}
