// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.CSS
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using CSS_ColonOption = com.eagle.programmar.CSS.CSS_TagList.CSS_ColonOption;
	using CSS_If_Directive = com.eagle.programmar.CSS.Directives.CSS_If_Directive;
	using CSS_Comment = com.eagle.programmar.CSS.Terminals.CSS_Comment;
	using CSS_Identifier = com.eagle.programmar.CSS.Terminals.CSS_Identifier;
	using CSS_Keyword = com.eagle.programmar.CSS.Terminals.CSS_Keyword;
	using CSS_KeywordChoice = com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
	using CSS_Number = com.eagle.programmar.CSS.Terminals.CSS_Number;
	using CSS_Punctuation = com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
	using CSS_PunctuationChoice = com.eagle.programmar.CSS.Terminals.CSS_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;

	public class CSS_Program : AbstractLanguage
	{
		public const string CSS = "CSS";

		public CSS_Program() : base(CSS, new CSS_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://www.w3schools.com/css/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<CSS_Entry> entries;
		public  OPT;

		public class CSS_Entry : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Comment XXcomment;
			public CSS_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Body XXbody;
			public CSS_Body XXbody;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_If_Directive XXifDirective;
			public CSS_If_Directive XXifDirective;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_AtEntry XXatEntry;
			public CSS_AtEntry XXatEntry;
		}

		public class CSS_Body : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<CSS_TagList> tags;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationStar star;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
			public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<CSS_Item> items;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CSS_BackslashZero zero;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
			public PunctuationRightBrace rightBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<com.eagle.programmar.CSS.CSS_TagList.CSS_ColonOption> colonOption;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT CSS_StarPiece starPiece;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT CSS_Qualifier qualifier;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT PunctuationComma comma;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public  OPT;

			public class CSS_StarPiece : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationStar star;
				public PunctuationStar star;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationColon colon;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CSS_Keyword FIRSTCHILD = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("first-child");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CSS_Punctuation plus = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('+');
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CSS.Terminals.CSS_Keyword HTML = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("html");
				public CSS_Keyword HTML = new CSS_Keyword("html");
			}

			public class CSS_BackslashZero : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Punctuation backslash = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation("\\");
				public CSS_Punctuation backslash = new CSS_Punctuation("\\");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSS.Terminals.CSS_Number zero;
				public CSS_Number zero;
			}
		}

		public class CSS_Item : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Comment XXcomment;
			public CSS_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Line XXline;
			public CSS_Line XXline;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSS_PercentItem extends com.eagle.tokens.TokenSequence
			public class CSS_PercentItem : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Number pct;
				public CSS_Number pct;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSS.Terminals.CSS_Punctuation percent = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('%');
				public CSS_Punctuation percent = new CSS_Punctuation('%');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
				public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<CSS_Item> items;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
				public PunctuationRightBrace rightBrace;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSS_ToItem extends com.eagle.tokens.TokenSequence
			public class CSS_ToItem : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice TO = new com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice("from", "to");
				public CSS_KeywordChoice TO = new CSS_KeywordChoice("from", "to");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
				public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<CSS_Item> items;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
				public PunctuationRightBrace rightBrace;
			}
		}

		public class CSS_Line : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationStar star;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSS.Terminals.CSS_Identifier attribute;
			public CSS_Identifier attribute;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSS.Terminals.CSS_PunctuationChoice colonEquals = new com.eagle.programmar.CSS.Terminals.CSS_PunctuationChoice(":", "=");
			public CSS_PunctuationChoice colonEquals = new CSS_PunctuationChoice(":", "=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CSS_Value_List values;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CSS_Tab tab;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PunctuationSemicolon semicolon1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon2;
			public  OPT;

			public class CSS_Tab : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Punctuation tab = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation("\\9");
				public CSS_Punctuation tab = new CSS_Punctuation("\\9");
			}

			public class CSS_Value_List : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) CSS_Value val;
				public CSS_Value val;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CSS_MoreValues> more;
				public  OPT;

				public class CSS_MoreValues : TokenChooser
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Value XXval;
					public CSS_Value XXval;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSS_MoreValuesComma extends com.eagle.tokens.TokenSequence
					public class CSS_MoreValuesComma : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
						public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CSS_Value val;
						public CSS_Value val;
					}
				}
			}
		}
	}

}
