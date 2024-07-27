// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 23, 2014

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.HTML_Attribute.HTML_Value;
import com.eagle.programmar.HTML.Terminals.HTML_Comment;
import com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class HTML_DocType extends TokenSequence
{
	static String[] SUFFIXES = new String[] {
			"+", "?", "*"
	};

	public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("<!");
	public @S(20) @NOSPACE HTML_KeywordChoice DOCTYPE = new HTML_KeywordChoice("ATTLIST", "DOCTYPE", "ELEMENT",
			"ENTITY", "NOTATION");
	public @S(30) TokenList<HTML_DocValue> values;
	public @S(40) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');

	public static class HTML_DocValue extends TokenChooser
	{
		public @CHOICE HTML_Value XXvalue;
		public @CHOICE HTML_DocType XXdocType;
		public @CHOICE HTML_Comment XXcomment;

		public @CHOICE static class HTML_DocBrackets extends TokenSequence
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) TokenList<HTML_DocValue> values;
			public @S(30) PunctuationRightBracket rightBracket;
		}

		public @CHOICE static class HTML_DocParens extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) HTML_DocValue value;
			public @S(30) @OPT HTML_PunctuationChoice suffix = new HTML_PunctuationChoice(SUFFIXES);
			public @S(40) @OPT TokenList<HTML_DocMoreValues> more;
			public @S(50) PunctuationRightParen rightParen;
			public @S(60) @OPT HTML_Punctuation plus = new HTML_Punctuation('+');

			public static class HTML_DocMoreValues extends TokenSequence
			{
				public @S(10) HTML_PunctuationChoice commaOrBar = new HTML_PunctuationChoice(",", "|");
				public @S(20) HTML_DocValue value;
				public @S(30) @OPT HTML_PunctuationChoice suffix = new HTML_PunctuationChoice(SUFFIXES);
			}
		}
	}
}
