// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.CSS;

import com.eagle.core.AbstractLanguage;
import com.eagle.programmar.CSS.CSS_TagList.CSS_ColonOption;
import com.eagle.programmar.CSS.Directives.CSS_If_Directive;
import com.eagle.programmar.CSS.Terminals.CSS_Comment;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.programmar.CSS.Terminals.CSS_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class CSS_Program extends AbstractLanguage
{
	public static final String CSS = "CSS";

	public CSS_Program()
	{
		super(CSS, new CSS_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/css/";
	}

	public @S(10) @OPT TokenList<CSS_Entry> entries;

	public static class CSS_Entry extends TokenChooser
	{
		public @CHOICE CSS_Comment XXcomment;
		public @CHOICE CSS_Body XXbody;
		public @CHOICE CSS_If_Directive XXifDirective;
		public @CHOICE CSS_AtEntry XXatEntry;
	}

	public static class CSS_Body extends TokenSequence
	{
		public @S(10) @OPT CSS_Selectors_Group selectors;
//		public @S(10) @OPT TokenList<CSS_TagList> tags;
//		public @S(20) @OPT PunctuationStar star;
		public @S(30) PunctuationLeftBrace leftBrace;
		public @S(40) @OPT TokenList<CSS_Item> items;
		public @S(50) @OPT CSS_BackslashZero zero;
		public @S(60) PunctuationRightBrace rightBrace;
		public @S(70) @OPT TokenList<CSS_ColonOption> colonOption;
		public @S(80) @OPT CSS_StarPiece starPiece;
		public @S(90) @OPT CSS_Qualifier qualifier;
		public @S(100) @OPT PunctuationComma comma;
		public @S(110) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;

		public static class CSS_StarPiece extends TokenSequence
		{
			public @S(10) PunctuationStar star;
			public @S(20) @OPT PunctuationColon colon;
			public @S(30) @OPT CSS_Keyword FIRSTCHILD = new CSS_Keyword("first-child");
			public @S(40) @OPT CSS_Punctuation plus = new CSS_Punctuation('+');
			public @S(50) CSS_Keyword HTML = new CSS_Keyword("html");
		}

		public static class CSS_BackslashZero extends TokenSequence
		{
			public @S(10) CSS_Punctuation backslash = new CSS_Punctuation("\\");
			public @S(20) CSS_Number zero;
		}
	}

	public static class CSS_Item extends TokenChooser
	{
		public @CHOICE CSS_Comment XXcomment;
		public @CHOICE CSS_Line XXline;

		public @CHOICE static class CSS_PercentItem extends TokenSequence
		{
			public @S(10) CSS_Number pct;
			public @S(20) CSS_Punctuation percent = new CSS_Punctuation('%');
			public @S(30) PunctuationLeftBrace leftBrace;
			public @S(40) @OPT TokenList<CSS_Item> items;
			public @S(50) PunctuationRightBrace rightBrace;
		}

		public @CHOICE static class CSS_ToItem extends TokenSequence
		{
			public @S(10) CSS_KeywordChoice TO = new CSS_KeywordChoice("from", "to");
			public @S(20) PunctuationLeftBrace leftBrace;
			public @S(30) @OPT TokenList<CSS_Item> items;
			public @S(40) PunctuationRightBrace rightBrace;
		}
	}

	public static class CSS_Line extends TokenSequence
	{
		public @S(10) @OPT PunctuationStar star;
		public @S(20) CSS_Identifier attribute;
		public @S(30) CSS_PunctuationChoice colonEquals = new CSS_PunctuationChoice(":", "=");
		public @S(40) @OPT CSS_Value_List values;
		public @S(50) @OPT CSS_Tab tab;
		public @S(60) @OPT PunctuationSemicolon semicolon1;
		public @S(70) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon2;

		public static class CSS_Tab extends TokenSequence
		{
			public @S(10) CSS_Punctuation tab = new CSS_Punctuation("\\9");
		}

		public static class CSS_Value_List extends TokenSequence
		{
			public @S(10) CSS_Value val;
			public @S(20) @OPT TokenList<CSS_MoreValues> more;

			public static class CSS_MoreValues extends TokenChooser
			{
				public @CHOICE CSS_Value XXval;

				public @CHOICE static class CSS_MoreValuesComma extends TokenSequence
				{
					public @S(10) PunctuationComma comma;
					public @S(20) CSS_Value val;
				}
			}
		}
	}
}
