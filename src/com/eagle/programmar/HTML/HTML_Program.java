// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.HTML;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Django.Django_Control;
import com.eagle.programmar.Django.Django_Insert;
import com.eagle.programmar.Django.Django_Syntax;
import com.eagle.programmar.HTML.HTML_Tag.HTML_EndTag;
import com.eagle.programmar.HTML.Terminals.HTML_Code;
import com.eagle.programmar.HTML.Terminals.HTML_Comment;
import com.eagle.programmar.HTML.Terminals.HTML_ExtraEndAnchor;
import com.eagle.programmar.HTML.Terminals.HTML_Pre;
import com.eagle.programmar.HTML.Terminals.HTML_Text;
import com.eagle.programmar.PHP.PHP_Program.PHP_Section;
import com.eagle.programmar.PHP.PHP_Syntax;
import com.eagle.programmar.XML.XML_Header;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Program extends AbstractLanguage implements EagleRunnable
{
	public static final String HTML = "HTML";

	public HTML_Program()
	{
		super(HTML, new HTML_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/html/";
	}

	public @S(10) TokenList<HTML_Element> elements;

	public static class HTML_Element extends TokenChooser
	{
		public @CHOICE HTML_DocType XXdocType;

		public @LAST HTML_Tag XXtag;
		public @CHOICE HTML_EndTag XXendTag;
		public @CHOICE HTML_Comment XXcomment;

		// Statements, sort of
		public @CHOICE HTML_IfCondition XXifCondition;
		public @CHOICE HTML_EndIfCondition XXendIfCondition;

		// Custom tags that need to be processed separately
		public @CHOICE HTML_Pre XXpre;
		public @CHOICE HTML_Code XXcode;
		public @CHOICE HTML_Style XXstyle;
		public @CHOICE HTML_Script XXscript;
		public @CHOICE HTML_Anchor XXanchor;
		public @CHOICE HTML_Table XXtable;
		public @CHOICE HTML_Caption XXcaption;

		// @FIRST for speed. Some Chromium HTML files have thousands of
		// <span> tags all on one line
		public @FIRST HTML_Span XXspan;

		// PHP
		public @CHOICE @SYNTAX(PHP_Syntax.class) PHP_Section XXphp_block;

		// Django
		public @CHOICE @SYNTAX(Django_Syntax.class) Django_Control XXdj_control;
		public @CHOICE @SYNTAX(Django_Syntax.class) Django_Insert XXdj_insert;

		public @LAST XML_Header XXxmlHeader;

		public @LAST static class HTML_BogusEndAnchor extends TokenSequence
		{
			// In a separate class just to enable @CURIOUS
			public @S(10) @CURIOUS("Extra end anchor") HTML_ExtraEndAnchor endAnchor;
		}

		public @LAST static class HTML_JustText extends TokenSequence
		{
			// In a separate class just to enable @NEWLINE
			public @S(10) @NEWLINE HTML_Text text;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (HTML_Element element : elements._elements)
		{
			if (element.getWhich() instanceof PHP_Section)
			{
				PHP_Section section = (PHP_Section) element.getWhich();
				interpreter.tryToInterpret(section.body);
			}
		}
	}
}
