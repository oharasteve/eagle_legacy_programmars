// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2013

namespace com.eagle.programmar.HTML
{
	using Django_Control = com.eagle.programmar.Django.Django_Control;
	using HTML_Comment = com.eagle.programmar.HTML.Terminals.HTML_Comment;
	using HTML_Keyword = com.eagle.programmar.HTML.Terminals.HTML_Keyword;
	using HTML_KeywordChoice = com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using HTML_PunctuationChoice = com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
	using PHP_Section = com.eagle.programmar.PHP.PHP_Program.PHP_Section;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class HTML_Table : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT HTML_StartTable startTable;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<HTML_TableBodyElement> elements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @OUTDENT HTML_EndTable endTable;
		public  OPT; // Optional in case there is never a closing </table>

		public class HTML_TableBodyElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PHP_Section XXphp;
			public PHP_Section XXphp;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_Control XXcontrol;
			public Django_Control XXcontrol;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_TablePiece XXpiece;
			public HTML_TablePiece XXpiece;
		}

		public class HTML_TableCol : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT HTML_Punctuation startTagSection = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('<');
			public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword COL = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("col");
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<HTML_Attribute> attributes;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_PunctuationChoice endTagCol = new com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice(">", "/>");
			public  NOSPACE;
		}

		public class HTML_TablePiece : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Comment XXcomment;
			public HTML_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_TableRow XXrow;
			public HTML_TableRow XXrow;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Caption XXcaption;
			public HTML_Caption XXcaption;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_TableCol XXcolumn;
			public HTML_TableCol XXcolumn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @CURIOUS("Missing table row") HTML_TableData XXdata;
			public @CURIOUS("Missing table row") HTML_TableData XXdata;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_TableColGroup extends com.eagle.tokens.TokenSequence
			public static class HTML_TableColGroup extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT HTML_Punctuation startTagColGroup = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('<');
				public @INDENT HTML_Punctuation startTagColGroup = new HTML_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword COLGROUP1 = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("colgroup");
				public @NOSPACE HTML_Keyword COLGROUP1 = new HTML_Keyword("colgroup");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<HTML_Attribute> attributes;
				public @OPT TokenList<HTML_Attribute> attributes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_Punctuation endTagColGroup = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
				public @NOSPACE HTML_Punctuation endTagColGroup = new HTML_Punctuation('>');

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<HTML_TableCol> columns;
				public @OPT TokenList<HTML_TableCol> columns;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("</");
				public HTML_Punctuation startTag = new HTML_Punctuation("</");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE HTML_Keyword COLGROUP2 = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("colgroup");
				public @NOSPACE HTML_Keyword COLGROUP2 = new HTML_Keyword("colgroup");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
				public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_TableSection extends com.eagle.tokens.TokenSequence
			public static class HTML_TableSection extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT HTML_Punctuation startTagSection = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('<');
				public @INDENT HTML_Punctuation startTagSection = new HTML_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_KeywordChoice tableType = new com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice("thead", "tbody", "tfoot");
				public @NOSPACE HTML_KeywordChoice tableType = new HTML_KeywordChoice("thead", "tbody", "tfoot");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<HTML_Attribute> attributes;
				public @OPT TokenList<HTML_Attribute> attributes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_Punctuation endTagRow = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
				public @NOSPACE HTML_Punctuation endTagRow = new HTML_Punctuation('>');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT HTML_TableSectionBody body;
				public @OPT HTML_TableSectionBody body;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT @OUTDENT HTML_EndTableSection endSection;
				public @OPT HTML_EndTableSection endSection;

				public static class HTML_TableSectionBody extends TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_Control XXcontrol;
					public Django_Control XXcontrol;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_Table_NormalBody extends com.eagle.tokens.TokenSequence
					public static class HTML_Table_NormalBody extends TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<HTML_TableRow> rows;
						public TokenList<HTML_TableRow> rows;
					}
				}

				public static class HTML_EndTableSection extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("</");
					public HTML_Punctuation startTag = new HTML_Punctuation("</");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_KeywordChoice tableType = new com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice("thead", "tbody", "tfoot");
					public @NOSPACE HTML_KeywordChoice tableType = new HTML_KeywordChoice("thead", "tbody", "tfoot");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
					public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
				}
			}
		}

		public static class HTML_StartTable extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("<");
			public HTML_Punctuation startTag = new HTML_Punctuation("<");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword TABLE = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("table");
			public @NOSPACE HTML_Keyword TABLE = new HTML_Keyword("table");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<HTML_Attribute> attributes;
			public @OPT TokenList<HTML_Attribute> attributes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}

		public static class HTML_EndTable extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("</");
			public HTML_Punctuation startTag = new HTML_Punctuation("</");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword TABLE = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("table");
			public @NOSPACE HTML_Keyword TABLE = new HTML_Keyword("table");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}
	}

}
