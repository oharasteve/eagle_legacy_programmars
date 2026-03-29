// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

namespace com.eagle.programmar.HTML
{
	using HTML_Value = com.eagle.programmar.HTML.HTML_Attribute.HTML_Value;
	using HTML_Keyword = com.eagle.programmar.HTML.Terminals.HTML_Keyword;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using HTML_PunctuationChoice = com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
	using PHP_Entry = com.eagle.programmar.PHP.PHP_Program.PHP_Entry;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class HTML_Anchor : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_AnchorHref extends com.eagle.tokens.TokenSequence
		public class HTML_AnchorHref : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT HTML_StartAnchor startTagA;
			public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.PHP.PHP_Program.PHP_Entry> contents;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @OUTDENT HTML_EndAnchor endAnchor;
			public  OPT; // Optional in case there is never a closing </a>

			public class HTML_StartAnchor : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("<");
				public HTML_Punctuation startTag = new HTML_Punctuation("<");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword A = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("a");
				public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<HTML_Attribute> attributes;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
				public  NOSPACE;
			}

			public class HTML_EndAnchor : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("</");
				public HTML_Punctuation startTag = new HTML_Punctuation("</");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword A = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("a");
				public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
				public  NOSPACE;
			}
		}

		// Tried all sorts of things to get this to work. No dice.
		// Test case 1: /www/rrcc/lh_tr_2006\7B.htm (times out if above catches the
		// name. No bogus entries)
		// Test case 2:
		// /wwwa/Dox/Supplier_Portal/SupplierSearch/SupplierSearch/help.html (has bogus
		// entries)
		// public @LAST @CURIOUS("Extra end anchor name") HTML_EndAnchor
		// bogusAnchorNameEnd;

		// This is an oddball case. An anchor with no closing </a> (in theory).
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class HTML_AnchorName extends com.eagle.tokens.TokenSequence
		public class HTML_AnchorName : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @INDENT HTML_Punctuation startTagA = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('<');
			public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword A = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("a");
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.HTML.Terminals.HTML_Keyword NAME = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("name");
			public HTML_Keyword NAME = new HTML_Keyword("name");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationEquals equals;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE HTML_Value value;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE HTML_PunctuationChoice endTag = new com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice("/>", ">");
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT @CURIOUS("Extra end anchor name") HTML_EndAnchorName bogusAnchorNameEnd;
			public  OPT;

			public class HTML_EndAnchorName : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("</");
				public HTML_Punctuation startTag = new HTML_Punctuation("</");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Keyword A = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("a");
				public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
				public  NOSPACE;
			}
		}
	}

}
