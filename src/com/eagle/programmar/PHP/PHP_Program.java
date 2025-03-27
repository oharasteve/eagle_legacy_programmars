// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.PHP;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.HTML.HTML_DocType;
import com.eagle.programmar.HTML.HTML_Program;
import com.eagle.programmar.HTML.HTML_Program.HTML_Element;
import com.eagle.programmar.HTML.HTML_Syntax;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.PHP.PHP_Body.PHP_NormalBlock;
import com.eagle.programmar.Perl.Perl_FunctionDefinition;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Perl_StatementOrComment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PHP_Program extends AbstractLanguage implements EagleRunnable
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
		public @CHOICE @SYNTAX(HTML_Syntax.class) HTML_Program XXhtml;
		public @CHOICE PHP_Section XXphp;
		public @LAST PHP_IfBlock XXifBlock;
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
		// First pass, just collect all the function definitions, buried deep inside the PHP
		for (PHP_Entry entry : entries._elements)
		{
			if (entry.getWhich() instanceof HTML_Program)
			{
				HTML_Program prog = (HTML_Program) entry.getWhich();
				for (HTML_Element html : prog.elements._elements)
				{
					if (html.getWhich() instanceof PHP_Section)
					{
						PHP_Section section = (PHP_Section) html.getWhich();
						if (section.body.getWhich() instanceof PHP_NormalBlock)
						{
							PHP_NormalBlock block = (PHP_NormalBlock) section.body.getWhich();
							for (PHP_Element element : block.elements._elements)
							{
								if (element.getWhich() instanceof Perl_StatementOrComment)
								{
									Perl_StatementOrComment stmtComm = (Perl_StatementOrComment) element.getWhich();
									if (stmtComm.getWhich() instanceof Perl_Statement)
									{
										Perl_Statement stmt = (Perl_Statement) stmtComm.getWhich();
										if (stmt.getWhich() instanceof Perl_FunctionDefinition)
										{
											Perl_FunctionDefinition func = (Perl_FunctionDefinition) stmt.getWhich();
											interpreter.addFunction(func.fnName.getValue(), func);
										}
									}
								}
							}
						}
					}
				}
			}
		}

		// Second pass, run any stuff in the outermost PHP
		for (PHP_Entry entry : entries._elements)
		{
			if (entry.getWhich() instanceof HTML_Program)
			{
				HTML_Program prog = (HTML_Program) entry.getWhich();
				for (HTML_Element html : prog.elements._elements)
				{
					if (html.getWhich() instanceof PHP_Section)
					{
						{
							PHP_Section section = (PHP_Section) html.getWhich();
							interpreter.tryToInterpret(section.body);
						}
					}
				}
			}
		}
	}
}
