// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 26, 2016

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Symbols.CSS_Class_Definition;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.programmar.CSS.Terminals.CSS_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class CSS_TagList extends TokenSequence
{
	public @S(10) @OPT CSS_Punctuation at = new CSS_Punctuation('@');
	public @S(20) @OPT PunctuationColon colon;
	public @S(30) @OPT CSS_Punctuation colonColon = new CSS_Punctuation("::");
	public @S(40) CSS_Tag tag;
	public @S(50) @OPT CSS_DotClass dotClass;
	public @S(60) @OPT CSS_Qualifier qualifier;
	public @S(70) @OPT TokenList<CSS_MoreQualifiers> moreQualifiers;
	public @S(80) @OPT TokenList<CSS_ColonOption> colonOption;
	public @S(90) @OPT CSS_PunctuationChoice separator =
			new CSS_PunctuationChoice(",", "+", "~", ">");

	public static class CSS_MoreQualifiers extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) CSS_Qualifier qualifier;
	}
	
	public static class CSS_Tag extends TokenChooser
	{
		public @CHOICE CSS_Identifier id;
		public @CHOICE CSS_DotClass dotClass;
		public @CHOICE PunctuationStar star;
		
		public @CHOICE static class CSS_Id_DotClass extends TokenSequence
		{
			public @S(10) CSS_Identifier id;
			public @S(20) CSS_DotClass dotClass;
		}
	}
	
	public static class CSS_DotClass extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) CSS_DotWhat what;
		
		public static class CSS_DotWhat extends TokenChooser
		{
			public @CHOICE CSS_Keyword MEDIA = new CSS_Keyword("media");
			public @CHOICE CSS_Class_Definition classDefinition;
		}
	}

	public static class CSS_ColonOption extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) CSS_ColonWhat what;
		public @S(30) @OPT PunctuationComma comma;
		
		public static class CSS_ColonWhat extends TokenChooser
		{
			public @CHOICE CSS_KeywordChoice option = new CSS_KeywordChoice(
					"active",
					"after",
					"before",
					"checked",
					"decrement",
					"default",
					"end",
					"first-child",
					"focus",
					"horizontal", 
					"hover",
					"increment",
					"last-child",
					"link",
					"-moz-any-link",
					"-o-prefocus",
					"start",
					"vertical",
					"visited");
			
			public @CHOICE static class CSS_Nth_Child extends TokenSequence
			{
				public @S(10) CSS_KeywordChoice NTH_CHILD = new CSS_KeywordChoice(
						"nth-child",
						"nth-last-child");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) CSS_Value value;
				public @S(40) PunctuationRightParen rightParen;
			}
			
			public @CHOICE static class CSS_ColonNot extends TokenSequence
			{
				public @S(10) CSS_Keyword NOT = new CSS_Keyword("not");
				public @S(20) PunctuationLeftParen leftParen;
				public @S(30) CSS_ColonNotWhat what;
				public @S(40) PunctuationRightParen rightParen;
				
				public static class CSS_ColonNotWhat extends TokenChooser
				{
					public @CHOICE CSS_Qualifier qualifier;
					public @CHOICE CSS_Keyword BODY = new CSS_Keyword("body");
					
					public @CHOICE static class CSS_ColonNotClass extends TokenSequence
					{
						public @S(10) CSS_PunctuationChoice separator = new CSS_PunctuationChoice(".", ":");
						public @S(20) CSS_Class_Definition classDefinition;
					}
				}
			}
		}
	}
}
