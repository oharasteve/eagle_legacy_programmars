// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.XML;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.Django.Django_Control;
import com.eagle.programmar.Django.Django_Insert;
import com.eagle.programmar.Django.Django_Syntax;
import com.eagle.programmar.Django.Controls.Django_AutoEscapeControl;
import com.eagle.programmar.HTML.HTML_DocType;
import com.eagle.programmar.HTML.HTML_Tag.HTML_TagElement;
import com.eagle.programmar.HTML.HTML_Tag.HTML_Tag_Namespace;
import com.eagle.programmar.HTML.Terminals.HTML_CData;
import com.eagle.programmar.HTML.Terminals.HTML_Comment;
import com.eagle.programmar.HTML.Terminals.HTML_Identifier;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_Text;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class XML_Program extends EagleLanguage
{
	public static final String XML = "XML";

	public XML_Program()
	{
		super(XML, new XML_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/xml/";
	}

	public @S(10) TokenList<XML_Element> elements;

	public static class XML_Element extends TokenChooser
	{
		public @CHOICE XML_Header XXheader;

		public @CHOICE HTML_Comment XXcomment;
		public @CHOICE HTML_Text XXtext;
		public @CHOICE HTML_DocType XXdocType;
		public @CHOICE HTML_CData XXcdata;

		public @FIRST XML_CombinedTag XXtag;

		// Django
		public @LAST @SYNTAX(Django_Syntax.class) Django_AutoEscapeControl XXautoEscape;
		public @LAST @SYNTAX(Django_Syntax.class) Django_Control XXdj_control;
		public @LAST @SYNTAX(Django_Syntax.class) Django_Insert XXdj_insert;

		public @CHOICE static class XML_TagElement extends TokenSequence
		{
			public @S(10) XML_StartTag startTag;
			public @S(20) @OPT TokenList<XML_Element> elements;
			public @S(30) XML_EndTag endTag;
		}
	}

	public static class XML_CombinedTag extends TokenSequence
	{
		public @S(10) @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation('<');
		public @S(20) @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
		public @S(30) @NOSPACE XML_Identifier tag;
		public @S(40) @OPT TokenList<HTML_TagElement> attributes;
		public @S(50) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation("/>");
	}

	public static class XML_StartTag extends TokenSequence
	{
		public @S(10) @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation('<');
		public @S(20) @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
		public @S(30) @NOSPACE XML_Identifier tag;
		public @S(40) @OPT TokenList<HTML_TagElement> attributes;
		public @S(50) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}

	public static class XML_EndTag extends TokenSequence
	{
		public @S(10) @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @S(20) @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
		public @S(30) @NOSPACE XML_Identifier tag;
		public @S(40) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}

	public static class XML_Identifier extends TokenSequence
	{
		public @S(10) SeparatedList<HTML_Identifier, PunctuationPeriod> tag;
	}
}
