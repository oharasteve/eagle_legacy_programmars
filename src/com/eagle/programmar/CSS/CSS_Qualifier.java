// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationStar;

public class CSS_Qualifier extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) CSS_QualifierChoice qual;
	public @S(30) PunctuationRightBracket rightBracket;
	public @S(40) @OPT CSS_Punctuation greaterThan = new CSS_Punctuation('>');

	public static class CSS_QualifierChoice extends TokenChooser
	{
		public @CHOICE CSS_KeywordChoice choice = new CSS_KeywordChoice(
				"has-element-focus",
				"multiple",
				"readonly",
				"selected",
				"size",
				"subframe");
		
		public @CHOICE static class CSS_QualifierClass extends TokenSequence
		{
			public @S(10) CSS_Keyword CLASS = new CSS_Keyword("class");
			public @S(20) PunctuationStar star;
			public @S(30) PunctuationEquals equals;
			public @S(40) CSS_Value value;
		}
		
		public @CHOICE static class CSS_QualiferCode extends TokenSequence
		{
			public @S(10) CSS_KeywordChoice CODE = new CSS_KeywordChoice(
					"controls",
					"data-original-title",
					"disabled",
					"hidden",
					"href",
					"title");
			public @S(20) @OPT CSS_BracketsHatEquals hatEquals;
			
			public static class CSS_BracketsHatEquals extends TokenSequence
			{
				public @S(10) CSS_Punctuation hat = new CSS_Punctuation('^');
				public @S(20) PunctuationEquals equals;
				public @S(30) CSS_Literal literal;
			}
		}
		
		public @CHOICE static class CSS_QualifierDataToggle extends TokenSequence
		{
			public @S(10) CSS_Keyword DATA_TOGGLE = new CSS_Keyword("data-toggle");
			public @S(20) PunctuationEquals equals;
			public @S(30) CSS_Value value;
		}
		
		public @CHOICE static class CSS_QualifierDir extends TokenSequence
		{
			public @S(10) CSS_Keyword DIR = new CSS_Keyword("dir");
			public @S(20) PunctuationEquals equals;
			public @S(30) CSS_QualifierWhichDir which;
			
			public static class CSS_QualifierWhichDir extends TokenChooser
			{
				public @CHOICE CSS_KeywordChoice RTL = new CSS_KeywordChoice(
						"rtl");
				public @CHOICE CSS_Literal literal;
			}
		}

		public @CHOICE static class CSS_QualifierFrame extends TokenSequence
		{
			public @S(10) CSS_KeywordChoice FRAME = new CSS_KeywordChoice(
					"aria-valuenow",
					"frame",
					"page");
			public @S(20) PunctuationEquals equals;
			public @S(30) CSS_Literal literal;
		}

		public @CHOICE static class CSS_QualifierHighlight extends TokenSequence
		{
			public @S(10) CSS_Keyword HIGHLIGHT = new CSS_Keyword("highlight");
			public @S(20) PunctuationEquals equals;
			public @S(30) CSS_QualifierWhichHighlight which;
			
			public static class CSS_QualifierWhichHighlight extends TokenChooser
			{
				public @CHOICE CSS_KeywordChoice STRONG = new CSS_KeywordChoice(
						"strong");
				public @CHOICE CSS_Literal literal;
			}
		}

		public @CHOICE static class CSS_QualifierRole extends TokenSequence
		{
			public @S(10) CSS_Keyword ROLE = new CSS_Keyword("role");
			public @S(20) PunctuationEquals equals;
			public @S(30) CSS_QualifierWhichRole which;
			
			public static class CSS_QualifierWhichRole extends TokenChooser
			{
				public @CHOICE CSS_KeywordChoice value = new CSS_KeywordChoice(
						"button",
						"number",
						"text");
				public @CHOICE CSS_Literal literal;
			}
		}
		
		public @CHOICE static class CSS_QualifierRow extends TokenSequence
		{
			public @S(10) CSS_Keyword ROW = new CSS_Keyword("row$");
			public @S(20) PunctuationEquals equals;
			public @S(30) CSS_Literal literal;
		}

		public @CHOICE static class CSS_QualifierType extends TokenSequence
		{
			public @S(10) CSS_Keyword TYPE = new CSS_Keyword("type");
			public @S(20) PunctuationEquals equals;
			public @S(30) CSS_QualifierWhichType which;
			
			public static class CSS_QualifierWhichType extends TokenChooser
			{
				public @CHOICE CSS_KeywordChoice value = new CSS_KeywordChoice(
						"button",
						"checkbox",
						"date",
						"datetime-local",
						"email",
						"file",
						"month",
						"number",
						"password",
						"radio",
						"range",
						"reset",
						"search",
						"submit",
						"time",
						"text");
				public @CHOICE CSS_Literal literal;
			}
		}
	}
}
