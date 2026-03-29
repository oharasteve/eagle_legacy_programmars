// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.PHP
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using HTML_DocType = com.eagle.programmar.HTML.HTML_DocType;
	using HTML_Program = com.eagle.programmar.HTML.HTML_Program;
	using HTML_Element = com.eagle.programmar.HTML.HTML_Program.HTML_Element;
	using HTML_Syntax = com.eagle.programmar.HTML.HTML_Syntax;
	using HTML_Keyword = com.eagle.programmar.HTML.Terminals.HTML_Keyword;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using PHP_NormalBlock = com.eagle.programmar.PHP.PHP_Body.PHP_NormalBlock;
	using Perl_Function = com.eagle.programmar.Perl.Perl_Function;
	using Perl_Statement = com.eagle.programmar.Perl.Perl_Statement;
	using Perl_StatementOrComment = com.eagle.programmar.Perl.Perl_StatementOrComment;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class PHP_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string PHP = "PHP";

		public PHP_Program() : base(PHP, new PHP_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://www.php.net/manual/en/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @SYNTAX(com.eagle.programmar.HTML.HTML_Syntax.class) com.eagle.programmar.HTML.HTML_DocType docType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<PHP_Entry> entries;
		public TokenList<PHP_Entry> entries;

		public class PHP_Entry : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @SYNTAX(com.eagle.programmar.HTML.HTML_Syntax.class) com.eagle.programmar.HTML.HTML_Program XXhtml;
			public @SYNTAX(typeof(HTML_Syntax)) HTML_Program XXhtml;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PHP_Section XXphp;
			public PHP_Section XXphp;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST PHP_IfBlock XXifBlock;
			public PHP_IfBlock XXifBlock;
		}

		public static class PHP_StartTag extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("<?");
			public HTML_Punctuation startTag = new HTML_Punctuation("<?");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT HTML_Keyword php = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("php");
			public @OPT HTML_Keyword php = new HTML_Keyword("php");
		}

		public static class PHP_EndTag extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("?>");
			public HTML_Punctuation endTag = new HTML_Punctuation("?>");
		}

		public static class PHP_Section extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) PHP_StartTag startTag;
			public PHP_StartTag startTag;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) PHP_Body body;
			public PHP_Body body;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the function definitions, buried deep inside the
			// PHP
			foreach (PHP_Entry entry in entries._elements)
			{
				if (entry.getWhich() is HTML_Program)
				{
					HTML_Program prog = (HTML_Program) entry.getWhich();
					foreach (HTML_Program.HTML_Element html in prog.elements._elements)
					{
						if (html.getWhich() is PHP_Section)
						{
							PHP_Section section = (PHP_Section) html.getWhich();
							if (section.body.getWhich() is PHP_NormalBlock)
							{
								PHP_NormalBlock block = (PHP_NormalBlock) section.body.getWhich();
								foreach (PHP_Element element in block.elements._elements)
								{
									if (element.getWhich() is Perl_StatementOrComment)
									{
										Perl_StatementOrComment stmtComm = (Perl_StatementOrComment) element.getWhich();
										if (stmtComm.getWhich() is Perl_Statement)
										{
											Perl_Statement stmt = (Perl_Statement) stmtComm.getWhich();
											if (stmt.getWhich() is Perl_Function)
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
			foreach (PHP_Entry entry in entries._elements)
			{
				if (entry.getWhich() is HTML_Program)
				{
					HTML_Program prog = (HTML_Program) entry.getWhich();
					foreach (HTML_Program.HTML_Element html in prog.elements._elements)
					{
						if (html.getWhich() is PHP_Section)
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

		public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Are there any global variables we need to declare?
			string scopeStr = this._currentLine + "-" + this._endLine;
			List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
			foreach (AssignMetrics met in asgMetrics)
			{
				EagleGenerator.TypeEnum typE = met.uniqueType();
				if (typE != EagleGenerator.TypeEnum.VOID)
				{
					AbstractType abstrType = generator.transformType(typE, null, this);
					AbstractExpression initExpr = null;
					// System.err.println("****** Found var " + met._symbolName);
					AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, abstrType, initExpr, this);
					generator.addStatement(dataStmt, this);
				}
			}

			// First pass, just collect all the function definitions, buried deep inside the
			// PHP
			foreach (PHP_Entry entry in entries._elements)
			{
				if (entry.getWhich() is HTML_Program)
				{
					HTML_Program prog = (HTML_Program) entry.getWhich();
					foreach (HTML_Program.HTML_Element html in prog.elements._elements)
					{
						if (html.getWhich() is PHP_Section)
						{
							PHP_Section section = (PHP_Section) html.getWhich();
							if (section.body.getWhich() is PHP_NormalBlock)
							{
								PHP_NormalBlock block = (PHP_NormalBlock) section.body.getWhich();
								foreach (PHP_Element element in block.elements._elements)
								{
									if (element.getWhich() is Perl_StatementOrComment)
									{
										Perl_StatementOrComment stmtComm = (Perl_StatementOrComment) element.getWhich();
										if (stmtComm.getWhich() is Perl_Statement)
										{
											Perl_Statement stmt = (Perl_Statement) stmtComm.getWhich();
											if (stmt.getWhich() is Perl_Function)
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
			foreach (PHP_Entry entry in entries._elements)
			{
				if (entry.getWhich() is HTML_Program)
				{
					HTML_Program prog = (HTML_Program) entry.getWhich();
					foreach (HTML_Program.HTML_Element html in prog.elements._elements)
					{
						if (html.getWhich() is PHP_Section)
						{
							PHP_Section section = (PHP_Section) html.getWhich();
							ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, section.body.getWhich());
							if (newStmts != null)
							{
								foreach (AbstractStatement newStmt in newStmts)
								{
									generator.addStatement(newStmt, entry);
								}
							}
						}
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}

}
