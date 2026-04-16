// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.PHP;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.HTML.HTML_DocType;
import com.eagle.programmar.HTML.HTML_Program;
import com.eagle.programmar.HTML.HTML_Program.HTML_Element;
import com.eagle.programmar.HTML.HTML_Syntax;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.PHP.PHP_Body.PHP_NormalBlock;
import com.eagle.programmar.Perl.Perl_Function;
import com.eagle.programmar.Perl.Perl_Statement;
import com.eagle.programmar.Perl.Perl_StatementOrComment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class PHP_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
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
		// First pass, just collect all the function definitions, buried deep inside the
		// PHP
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
										if (stmt.getWhich() instanceof Perl_Function)
										{
											Perl_Function func = (Perl_Function) stmt.getWhich();
											interpreter.addFunction(func.id.getValue(), func);
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

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Are there any global variables we need to declare?
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			TypeEnum typE = met.uniqueType();
			if (typE != TypeEnum.VOID)
			{
				AbstractType abstrType = generator.transformType(typE, null, this);
				AbstractExpression initExpr = null;
				// System.err.println("****** Found var " + met._symbolName);
				AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName,
						null, abstrType, initExpr, this);
				generator.addStatement(dataStmt, this);
			}
		}

		// First pass, just collect all the function definitions, buried deep inside the
		// PHP
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
										if (stmt.getWhich() instanceof Perl_Function)
										{
											Perl_Function func = (Perl_Function) stmt.getWhich();
											func.transformFunction(transformer, generator);
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
						PHP_Section section = (PHP_Section) html.getWhich();
						Collection<AbstractStatement> newStmts = transformer.transformStatement(
								generator, section.body.getWhich());
						if (newStmts != null)
						{
							for (AbstractStatement newStmt : newStmts)
							{
								generator.addStatement(newStmt, entry);
							}
						}
					}
				}
			}
		}

		return generator.getTransformedProgram();
	}
}
