// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.PHP;

import com.eagle.core.EagleLanguage;
import com.eagle.programmar.HTML.HTML_DocType;
import com.eagle.programmar.HTML.HTML_Program;
import com.eagle.programmar.HTML.HTML_Syntax;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.Perl.Perl_Statement.Perl_SimpleStatement.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Perl_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PHP_Program extends EagleLanguage
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
	public @S(20) TokenList<PHP_Entry> entry;
	
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
		
		public static class PHP_Body extends TokenChooser
		{
			// Really wasteful ... frequently parses twice
			public @CHOICE static class PHP_NoEnd extends TokenSequence
			{
				public @S(10) TokenList<PHP_Element> elements;
				public @S(20) PHP_EndOfFile eof;	// Can't be inside another class ...
			}
			
			public @CHOICE static class PHP_NormalEnd extends TokenSequence
			{
				public @S(10) TokenList<PHP_Element> elements;
				public @S(20) PHP_EndTag endTag;
			}
		}

		public static class PHP_Element extends TokenChooser
		{
			public @CHOICE @SYNTAX(Perl_Syntax.class) Perl_StatementOrComment statement;
		}
	}
}
