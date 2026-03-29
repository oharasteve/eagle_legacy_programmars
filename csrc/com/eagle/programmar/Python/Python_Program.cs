// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2013

namespace com.eagle.programmar.Python
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using Python_Statement = com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
	using Python_Function = com.eagle.programmar.Python.Statements.Python_Function;
	using Python_SameLineStatement = com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_SameLineStatement;
	using Python_Function_Definition = com.eagle.programmar.Python.Symbols.Python_Function_Definition;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public abstract class Python_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public Python_Program(string name, EagleSyntax syntax) : base(name, syntax)
		{
		}

		public override string booleanName(bool flag)
		{
			if (flag)
			{
				return "True";
			}
			return "False";
		}

		public override string DocRoot
		{
			get
			{
				return "https://docs.python.org/3.10/reference/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<Python_CommentEoln> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Python.Terminals.Python_EndOfLine> blankLines;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Python_ComplexStatement> entries;
		public  OPT;

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Python_ComplexStatement stmt in entries._elements)
			{
				AbstractToken which = stmt.statementOrComment.getWhich();
				if (which is Python_SameLineStatement)
				{
					Python_SameLineStatement stmts = (Python_SameLineStatement) which;
					for (int i = 0; i < stmts.statements.getPrimaryCount(); i++)
					{
						Python_Statement simple = stmts.statements.getPrimaryElement(i);
						if (simple.getWhich() is Python_Function)
						{
							Python_Function fn = (Python_Function) simple.getWhich();
							if (fn.fnName.getWhich() is Python_Function_Definition)
							{
								Python_Function_Definition name = (Python_Function_Definition) fn.fnName.getWhich();
								interpreter.addFunction(name.getValue(), fn);
							}
						}
					}
				}
			}

			// Second pass, execute the program
			foreach (Python_ComplexStatement stmt in entries._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}

		public virtual void addComment(Python_Comment comment)
		{
			if (this.comments == null)
			{
				this.comments = new TokenList<Python_CommentEoln>();
			}
			Python_CommentEoln eol = new Python_CommentEoln();
			eol.comment = comment;
			this.comments.addToken(eol);
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Transform all the Function definitions first
			foreach (Python_ComplexStatement stmt1 in entries._elements)
			{
				AbstractToken which1 = stmt1.statementOrComment.getWhich();
				if (which1 is Python_SameLineStatement)
				{
					Python_SameLineStatement stmt2 = (Python_SameLineStatement) which1;
					int numStmts2 = stmt2.statements.getPrimaryCount();
					for (int i = 0; i < numStmts2; i++)
					{
						Python_Statement stmt3 = stmt2.statements.getPrimaryElement(i);
						if (stmt3.getWhich() is Python_Function)
						{
							Python_Function func = (Python_Function) stmt3.getWhich();
							func.transformFunction(transformer, generator);
						}
					}
				}
			}

			// Are there any global variables we need to declare?
			string scopeStr = this._currentLine + "-" + this._endLine;
			List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
			foreach (AssignMetrics met in asgMetrics)
			{
				EagleGenerator.TypeEnum typE = met.uniqueType();
				if (typE != EagleGenerator.TypeEnum.VOID)
				{
					AbstractType abstrType = generator.transformType(typE, null, this);

					// System.err.println("****** Found var " + met._symbolName);
					AbstractExpression initExpr = null;
					AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, abstrType, initExpr, this);
					generator.addStatement(dataStmt, this);
				}
			}

			// Transform all the logic, etc.
			foreach (Python_ComplexStatement stmt4 in entries._elements)
			{
				AbstractToken which4 = stmt4.statementOrComment.getWhich();
				if (which4 is Python_SameLineStatement)
				{
					Python_SameLineStatement stmt5 = (Python_SameLineStatement) which4;
					int numStmts5 = stmt5.statements.getPrimaryCount();
					for (int i = 0; i < numStmts5; i++)
					{
						Python_Statement stmt6 = stmt5.statements.getPrimaryElement(i);
						ICollection<AbstractStatement> newStmts6 = transformer.transformStatement(generator, stmt6);
						if (newStmts6 != null)
						{
							foreach (AbstractStatement newStmt6 in newStmts6)
							{
								generator.addStatement(newStmt6, stmt6);
							}
						}
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}

}
