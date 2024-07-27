// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural;

import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Natural_Subscript extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Natural_Subscript_Contents contents;
	public @S(30) PunctuationRightParen rightParen;

	public static class Natural_Subscript_Contents extends TokenChooser
	{
		public @CHOICE PunctuationStar XXstar;

		public @CHOICE static class Natural_Subscript_Contents_Label extends TokenSequence
		{
			public @S(10) Natural_Label label;
		}

		public @CHOICE static class Natural_Subscript_Contents_Normal extends TokenSequence
		{
			public @S(10) Natural_Expression subscript;
			public @S(20) @OPT Natural_Subscript_Range subscriptRange;
			public @S(30) @OPT Natural_Second_Subscript secondSubscript;

			public static class Natural_Subscript_Range extends TokenSequence
			{
				public @S(10) PunctuationColon colon;
				public @S(20) Natural_Expression subscript;
			}

			public static class Natural_Second_Subscript extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) Natural_Expression subscript;
				public @S(30) @OPT Natural_Subscript_Range subscriptRange;
			}
		}
	}
}
