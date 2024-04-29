// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.PHP;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.HTML.HTML_DocType;
import com.eagle.programmar.HTML.HTML_Program;
import com.eagle.programmar.HTML.HTML_Syntax;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PHP_Program extends EagleLanguage implements EagleRunnable
{
	public static final String PHP = "PHP";
	
	public PHP_Program()
	{
		super(PHP, new PHP_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.php.net/manual/en/";
	}
	
	public @S(10) @OPT @SYNTAX(HTML_Syntax.class) HTML_DocType docType;
	public @S(20) TokenList<PHP_Entry> entries;
	
	public static class PHP_Entry extends TokenChooser
	{
		public @CHOICE @SYNTAX(HTML_Syntax.class) HTML_Program html;
		public @CHOICE PHP_Section php;
		public @LAST PHP_IfBlock ifBlock;
	}
	
	public static class PHP_StartTag extends TokenSequence
	{
		public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("<?");
		public @S(20) @OPT HTML_Keyword php = new HTML_Keyword("php");
	}
	
	public static class PHP_EndTag extends TokenSequence
	{
		public @S(10) HTML_Punctuation endTag = new HTML_Punctuation("?>");
	}
	
	public static class PHP_Section extends TokenSequence
	{
		public @S(10) PHP_StartTag startTag;
		public @S(20) PHP_Body body;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (PHP_Entry entry : entries._elements)
		{
			interpreter.tryToInterpret(entry.getWhich());
		}
	}
}
