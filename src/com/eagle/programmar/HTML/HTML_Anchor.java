// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.HTML_Attribute.HTML_Value;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
import com.eagle.programmar.PHP.PHP_Program.PHP_Entry;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class HTML_Anchor extends TokenChooser
{
	public @CHOICE static class HTML_AnchorHref extends TokenSequence
	{
		public @S(10) @INDENT HTML_StartAnchor startTagA;
		public @S(20) @OPT TokenList<PHP_Entry> contents;
		public @S(30) @OPT @OUTDENT HTML_EndAnchor endAnchor;	// Optional in case there is never a closing </a>

		public static class HTML_StartAnchor extends TokenSequence
		{
			public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("<");
			public @S(20) @NOSPACE HTML_Keyword A = new HTML_Keyword("a");
			public @S(30) @OPT TokenList<HTML_Attribute> attributes;
			public @S(40) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}

		public static class HTML_EndAnchor extends TokenSequence
		{
			public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("</");
			public @S(20) @NOSPACE HTML_Keyword A = new HTML_Keyword("a");
			public @S(30) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}
	}

	// Tried all sorts of things to get this to work. No dice.
	// Test case 1: /www/rrcc\lh_tr_2006\7B.htm (times out if above catches the
	// name. No bogus entries)
	// Test case 2:
	// /wwwa/Dox/Supplier_Portal/SupplierSearch/SupplierSearch/help.html (has bogus
	// entries)
	// public @LAST @CURIOUS("Extra end anchor name") HTML_EndAnchor
	// bogusAnchorNameEnd;

	// This is an oddball case. An anchor with no closing </a> (in theory).
	public @FIRST static class HTML_AnchorName extends TokenSequence
	{
		public @S(10) @INDENT HTML_Punctuation startTagA = new HTML_Punctuation('<');
		public @S(20) @NOSPACE HTML_Keyword A = new HTML_Keyword("a");
		public @S(30) HTML_Keyword NAME = new HTML_Keyword("name");
		public @S(40) @NOSPACE PunctuationEquals equals;
		public @S(50) @NOSPACE HTML_Value value;
		public @S(60) @NOSPACE HTML_PunctuationChoice endTag = new HTML_PunctuationChoice("/>", ">");
		public @S(70) @OPT @CURIOUS("Extra end anchor name") HTML_EndAnchorName bogusAnchorNameEnd;

		public static class HTML_EndAnchorName extends TokenSequence
		{
			public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("</");
			public @S(20) @NOSPACE HTML_Keyword A = new HTML_Keyword("a");
			public @S(30) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}
	}
}
