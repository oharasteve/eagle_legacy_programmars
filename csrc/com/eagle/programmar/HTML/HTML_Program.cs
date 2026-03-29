// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.HTML
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Django_Control = com.eagle.programmar.Django.Django_Control;
	using Django_Insert = com.eagle.programmar.Django.Django_Insert;
	using Django_Syntax = com.eagle.programmar.Django.Django_Syntax;
	using HTML_EndTag = com.eagle.programmar.HTML.HTML_Tag.HTML_EndTag;
	using HTML_Code = com.eagle.programmar.HTML.Terminals.HTML_Code;
	using HTML_Comment = com.eagle.programmar.HTML.Terminals.HTML_Comment;
	using HTML_ExtraEndAnchor = com.eagle.programmar.HTML.Terminals.HTML_ExtraEndAnchor;
	using HTML_Pre = com.eagle.programmar.HTML.Terminals.HTML_Pre;
	using HTML_Text = com.eagle.programmar.HTML.Terminals.HTML_Text;
	using PHP_Section = com.eagle.programmar.PHP.PHP_Program.PHP_Section;
	using PHP_Syntax = com.eagle.programmar.PHP.PHP_Syntax;
	using XML_Header = com.eagle.programmar.XML.XML_Header;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class HTML_Program : AbstractLanguage, EagleRunnable
	{
		public const string HTML = "HTML";

		public HTML_Program() : base(HTML, new HTML_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://www.w3schools.com/html/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<HTML_Element> elements;
		public TokenList<HTML_Element> elements;

		public class HTML_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_DocType XXdocType;
			public HTML_DocType XXdocType;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST HTML_Tag XXtag;
			public HTML_Tag XXtag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_EndTag XXendTag;
			public HTML_EndTag XXendTag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Comment XXcomment;
			public HTML_Comment XXcomment;

			// Statements, sort of
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_IfCondition XXifCondition;
			public HTML_IfCondition XXifCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_EndIfCondition XXendIfCondition;
			public HTML_EndIfCondition XXendIfCondition;

			// Custom tags that need to be processed separately
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Pre XXpre;
			public HTML_Pre XXpre;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Code XXcode;
			public HTML_Code XXcode;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Style XXstyle;
			public HTML_Style XXstyle;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Script XXscript;
			public HTML_Script XXscript;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Anchor XXanchor;
			public HTML_Anchor XXanchor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Table XXtable;
			public HTML_Table XXtable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Caption XXcaption;
			public HTML_Caption XXcaption;

			// @FIRST for speed. Some Chromium HTML files have thousands of
			// <span> tags all on one line
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST HTML_Span XXspan;
			public HTML_Span XXspan;

			// PHP
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.PHP.PHP_Syntax.class) com.eagle.programmar.PHP.PHP_Program.PHP_Section XXphp_block;
			public @SYNTAX(typeof(PHP_Syntax)) PHP_Section XXphp_block;

			// Django
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.Django.Django_Syntax.class) com.eagle.programmar.Django.Django_Control XXdj_control;
			public @SYNTAX(typeof(Django_Syntax)) Django_Control XXdj_control;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.Django.Django_Syntax.class) com.eagle.programmar.Django.Django_Insert XXdj_insert;
			public @SYNTAX(typeof(Django_Syntax)) Django_Insert XXdj_insert;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST XML_Header XXxmlHeader;
			public XML_Header XXxmlHeader;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class HTML_BogusEndAnchor extends com.eagle.tokens.TokenSequence
			public static class HTML_BogusEndAnchor extends TokenSequence
			{
				// In a separate class just to enable @CURIOUS
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @CURIOUS("Extra end anchor") com.eagle.programmar.HTML.Terminals.HTML_ExtraEndAnchor endAnchor;
				public @CURIOUS("Extra end anchor") HTML_ExtraEndAnchor endAnchor;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class HTML_JustText extends com.eagle.tokens.TokenSequence
			public static class HTML_JustText extends TokenSequence
			{
				// In a separate class just to enable @NEWLINE
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE HTML_Text text;
				public @NEWLINE HTML_Text text;
			}
		}

		public void interpret(EagleInterpreter interpreter)
		{
			foreach (HTML_Element element in elements._elements)
			{
				if (element.getWhich() is PHP_Section)
				{
					PHP_Section section = (PHP_Section) element.getWhich();
					interpreter.tryToInterpret(section.body);
				}
			}
		}
	}

}
