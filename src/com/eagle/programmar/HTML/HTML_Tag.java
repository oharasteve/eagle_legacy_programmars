// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.Django.Django_Control;
import com.eagle.programmar.Django.Django_Insert;
import com.eagle.programmar.Django.Terminals.Django_Comment;
import com.eagle.programmar.HTML.Terminals.HTML_Identifier;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class HTML_Tag extends TokenSequence
{
	public @S(10) @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation('<');
	public @S(20) @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
	public @S(30) @NOSPACE HTML_Identifier tag;
	public @S(40) @OPT TokenList<HTML_TagElement> attributes;
	public @S(50) @NOSPACE HTML_PunctuationChoice closer = new HTML_PunctuationChoice(">", "/>");

	public static class HTML_TagElement extends TokenChooser
	{
		public @CHOICE HTML_Attribute XXattribute;
		public @CHOICE Django_Control XXcontrol;
		public @CHOICE Django_Insert XXinsert;
		public @CHOICE Django_Comment XXcomment;
	}

	public static class HTML_Tag_Namespace extends TokenSequence
	{
		public @S(10) HTML_Identifier ns;
		public @S(20) PunctuationColon colon;
	}

	public static class HTML_EndTag extends TokenSequence
	{
		public @S(10) @NEWLINE HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @S(20) @OPT @NOSPACE HTML_Tag_Namespace tagNamespace;
		public @S(30) @NOSPACE HTML_Identifier tag;
		public @S(40) @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
}
