// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

namespace com.eagle.programmar.XML
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using Django_Control = com.eagle.programmar.Django.Django_Control;
	using Django_Insert = com.eagle.programmar.Django.Django_Insert;
	using Django_Syntax = com.eagle.programmar.Django.Django_Syntax;
	using Django_AutoEscapeControl = com.eagle.programmar.Django.Controls.Django_AutoEscapeControl;
	using HTML_DocType = com.eagle.programmar.HTML.HTML_DocType;
	using HTML_TagElement = com.eagle.programmar.HTML.HTML_Tag.HTML_TagElement;
	using HTML_Tag_Namespace = com.eagle.programmar.HTML.HTML_Tag.HTML_Tag_Namespace;
	using HTML_CData = com.eagle.programmar.HTML.Terminals.HTML_CData;
	using HTML_Comment = com.eagle.programmar.HTML.Terminals.HTML_Comment;
	using HTML_Identifier = com.eagle.programmar.HTML.Terminals.HTML_Identifier;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using HTML_Text = com.eagle.programmar.HTML.Terminals.HTML_Text;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class XML_Program : AbstractLanguage
	{
		public const string XML = "XML";

		public XML_Program() : base(XML, new XML_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://www.w3schools.com/xml/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<XML_Element> elements;
		public TokenList<XML_Element> elements;

		public class XML_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE XML_Header XXheader;
			public XML_Header XXheader;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Comment XXcomment;
			public HTML_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Text XXtext;
			public HTML_Text XXtext;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_DocType XXdocType;
			public HTML_DocType XXdocType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_CData XXcdata;
			public HTML_CData XXcdata;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST XML_CombinedTag XXtag;
			public XML_CombinedTag XXtag;

			// Django
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST @SYNTAX(com.eagle.programmar.Django.Django_Syntax.class) com.eagle.programmar.Django.Controls.Django_AutoEscapeControl XXautoEscape;
			public @SYNTAX(typeof(Django_Syntax)) Django_AutoEscapeControl XXautoEscape;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST @SYNTAX(com.eagle.programmar.Django.Django_Syntax.class) com.eagle.programmar.Django.Django_Control XXdj_control;
			public @SYNTAX(typeof(Django_Syntax)) Django_Control XXdj_control;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST @SYNTAX(com.eagle.programmar.Django.Django_Syntax.class) com.eagle.programmar.Django.Django_Insert XXdj_insert;
			public @SYNTAX(typeof(Django_Syntax)) Django_Insert XXdj_insert;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class XML_TagElement extends com.eagle.tokens.TokenSequence
			public static class XML_TagElement extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) XML_StartTag startTag;
				public XML_StartTag startTag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<XML_Element> elements;
				public @OPT TokenList<XML_Element> elements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) XML_EndTag endTag;
				public XML_EndTag endTag;
			}
		}

		public static class XML_CombinedTag extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('<');
			public @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
			public @OPT HTML_Tag_Namespace tagNamespace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE XML_Identifier tag;
			public @NOSPACE XML_Identifier tag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.HTML.HTML_Tag.HTML_TagElement> attributes;
			public @OPT TokenList<HTML_TagElement> attributes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("/>");
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation("/>");
		}

		public static class XML_StartTag extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('<');
			public @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
			public @OPT HTML_Tag_Namespace tagNamespace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE XML_Identifier tag;
			public @NOSPACE XML_Identifier tag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.HTML.HTML_Tag.HTML_TagElement> attributes;
			public @OPT TokenList<HTML_TagElement> attributes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}

		public static class XML_EndTag extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("</");
			public @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation("</");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
			public @OPT HTML_Tag_Namespace tagNamespace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE XML_Identifier tag;
			public @NOSPACE XML_Identifier tag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}

		public static class XML_Identifier extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.HTML.Terminals.HTML_Identifier, com.eagle.tokens.punctuation.PunctuationPeriod> tag;
			public SeparatedList<HTML_Identifier, PunctuationPeriod> tag;
		}
	}

}
