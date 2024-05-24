// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django;

import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
import com.eagle.programmar.Django.Terminals.Django_Literal;
import com.eagle.programmar.Django.Terminals.Django_Number;
import com.eagle.programmar.Django.Terminals.Django_Punctuation;
import com.eagle.programmar.Django.Terminals.Django_PunctuationChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Django_Insert extends TokenSequence
{
	public @S(10) HTML_Punctuation startBraceBrace = new HTML_Punctuation("{{");
	public @S(20) @OPT Django_PunctuationChoice plus = new Django_PunctuationChoice("+", "-", "/");
	public @S(30) Django_InsertWhat what;
	public @S(40) HTML_Punctuation endBraceBrace = new HTML_Punctuation("}}");

	public static class Django_InsertWhat extends TokenChooser
	{
		public @CHOICE static class Django_InsertSuper extends TokenSequence
		{
			public @S(10) Django_Keyword SUPER = new Django_Keyword("super");
			public @S(20) @OPT Django_InsertSuperArgs args;

			public static class Django_InsertSuperArgs extends TokenSequence
			{
				public @S(10) PunctuationLeftParen leftParen;
				public @S(20) PunctuationRightParen rightParen;
			}
		}

		public @CHOICE static class Django_InsertExpression extends TokenSequence
		{
			public @S(10) Django_Expression expr;
			public @S(20) @OPT Django_InsertDot insertDot;
			public @S(30) @OPT Django_OrWhat what;
		}
	}

	public static class Django_InsertDot extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) Django_InsertDotWhat what;

		public static class Django_InsertDotWhat extends TokenChooser
		{
			public @CHOICE Django_Number number;
			public @CHOICE Django_Variable variable;
		}
	}

	public static class Django_OrWhat extends TokenSequence
	{
		public @S(10) Django_Punctuation verticalBar = new Django_Punctuation('|');
		public @S(20) Django_KeywordChoice DATE = new Django_KeywordChoice("capfirst", "date", "escape", "safe");
		public @S(30) @OPT PunctuationColon colon;
		public @S(40) @OPT Django_Literal literal;
	}
}
