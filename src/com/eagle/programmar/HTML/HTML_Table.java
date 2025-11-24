// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2013

package com.eagle.programmar.HTML;

import com.eagle.programmar.Django.Django_Control;
import com.eagle.programmar.HTML.Terminals.HTML_Comment;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
import com.eagle.programmar.PHP.PHP_Program.PHP_Section;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Table extends TokenSequence
{
	public @S(10) @INDENT HTML_StartTable startTable;
	public @S(20) @OPT TokenList<HTML_TableBodyElement> elements;
	public @S(30) @OPT @OUTDENT HTML_EndTable endTable; // Optional in case there is never a closing </table>

	public static class HTML_TableBodyElement extends TokenChooser
	{
		public @CHOICE PHP_Section XXphp;
		public @CHOICE Django_Control XXcontrol;
		public @CHOICE HTML_TablePiece XXpiece;
	}

	public static class HTML_TableCol extends TokenSequence
	{
		public @S(10) @INDENT HTML_Punctuation startTagSection = new HTML_Punctuation('<');
		public @S(20) @NOSPACE HTML_Keyword COL = new HTML_Keyword("col");
		public @S(30) @OPT TokenList<HTML_Attribute> attributes;
		public @S(40) @NOSPACE HTML_PunctuationChoice endTagCol = new HTML_PunctuationChoice(">", "/>");
	}

	public static class HTML_TablePiece extends TokenChooser
	{
		public @CHOICE HTML_Comment XXcomment;
		public @CHOICE HTML_TableRow XXrow;
		public @CHOICE HTML_Caption XXcaption;
		public @CHOICE HTML_TableCol XXcolumn;
		public @CHOICE @CURIOUS("Missing table row") HTML_TableData XXdata;

		public @CHOICE static class HTML_TableColGroup extends TokenSequence
		{
			public @S(10) @INDENT HTML_Punctuation startTagColGroup = new HTML_Punctuation('<');
			public @S(20) @NOSPACE HTML_Keyword COLGROUP1 = new HTML_Keyword("colgroup");
			public @S(30) @OPT TokenList<HTML_Attribute> attributes;
			public @S(40) @NOSPACE HTML_Punctuation endTagColGroup = new HTML_Punctuation('>');

			public @S(50) @OPT TokenList<HTML_TableCol> columns;

			public @S(60) HTML_Punctuation startTag = new HTML_Punctuation("</");
			public @S(70) @NOSPACE HTML_Keyword COLGROUP2 = new HTML_Keyword("colgroup");
			public @S(80) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}

		public @CHOICE static class HTML_TableSection extends TokenSequence
		{
			public @S(10) @INDENT HTML_Punctuation startTagSection = new HTML_Punctuation('<');
			public @S(20) @NOSPACE HTML_KeywordChoice tableType = new HTML_KeywordChoice("thead", "tbody", "tfoot");
			public @S(30) @OPT TokenList<HTML_Attribute> attributes;
			public @S(40) @NOSPACE HTML_Punctuation endTagRow = new HTML_Punctuation('>');
			public @S(50) @OPT HTML_TableSectionBody body;
			public @S(60) @OPT @OUTDENT HTML_EndTableSection endSection;

			public static class HTML_TableSectionBody extends TokenChooser
			{
				public @CHOICE Django_Control XXcontrol;

				public @CHOICE static class HTML_Table_NormalBody extends TokenSequence
				{
					public @S(10) TokenList<HTML_TableRow> rows;
				}
			}

			public static class HTML_EndTableSection extends TokenSequence
			{
				public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("</");
				public @S(20) @NOSPACE HTML_KeywordChoice tableType = new HTML_KeywordChoice("thead", "tbody", "tfoot");
				public @S(30) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
			}
		}
	}

	public static class HTML_StartTable extends TokenSequence
	{
		public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("<");
		public @S(20) @NOSPACE HTML_Keyword TABLE = new HTML_Keyword("table");
		public @S(30) @OPT TokenList<HTML_Attribute> attributes;
		public @S(40) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}

	public static class HTML_EndTable extends TokenSequence
	{
		public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @S(20) @NOSPACE HTML_Keyword TABLE = new HTML_Keyword("table");
		public @S(30) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
}
