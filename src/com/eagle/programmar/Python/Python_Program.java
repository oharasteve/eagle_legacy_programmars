// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2013

package com.eagle.programmar.Python;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.core.EagleSyntax;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
import com.eagle.programmar.Python.Statements.Python_Function;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_SameLineStatement;
import com.eagle.programmar.Python.Symbols.Python_Function_Definition;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public abstract class Python_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public Python_Program(String name, EagleSyntax syntax)
	{
		super(name, syntax);
	}

	@Override
	public String booleanName(boolean flag)
	{
		if (flag) return "True";
		return "False";
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.python.org/3.10/reference/";
	}

	public @S(10) @OPT TokenList<Python_CommentEoln> comments;
	public @S(20) @OPT TokenList<Python_EndOfLine> blankLines;
	public @S(30) @OPT TokenList<Python_ComplexStatement> entries;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Python_ComplexStatement stmt : entries._elements)
		{
			AbstractToken which = stmt.statementOrComment.getWhich();
			if (which instanceof Python_SameLineStatement)
			{
				Python_SameLineStatement stmts = (Python_SameLineStatement) which;
				for (int i = 0; i < stmts.statements.getPrimaryCount(); i++)
				{
					Python_Statement simple = stmts.statements.getPrimaryElement(i);
					if (simple.getWhich() instanceof Python_Function)
					{
						Python_Function fn = (Python_Function) simple.getWhich();
						if (fn.fnName.getWhich() instanceof Python_Function_Definition)
						{
							Python_Function_Definition name = (Python_Function_Definition) fn.fnName.getWhich();
							interpreter.addFunction(name.getValue(), fn);
						}
					}
				}
			}
		}

		// Second pass, execute the program
		for (Python_ComplexStatement stmt : entries._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
	
	public void addComment(Python_Comment comment)
	{
		if (this.comments == null)
		{
			this.comments = new TokenList<Python_CommentEoln>();
		}
		Python_CommentEoln eol = new Python_CommentEoln();
		eol.comment = comment;
		this.comments.addToken(eol);
	}
	
	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator generator)
	{
		// Transform all the Function definitions first
		for (Python_ComplexStatement stmt1 : entries._elements)
		{
			AbstractToken which1 = stmt1.statementOrComment.getWhich();
			if (which1 instanceof Python_SameLineStatement)
			{
				Python_SameLineStatement stmt2 = (Python_SameLineStatement) which1;
				int numStmts2 = stmt2.statements.getPrimaryCount();
				for (int i = 0; i < numStmts2; i++)
				{
					Python_Statement stmt3 = stmt2.statements.getPrimaryElement(i);
					if (stmt3.getWhich() instanceof Python_Function)
					{
						Python_Function func = (Python_Function) stmt3.getWhich();
						func.transformFunction(transformer, generator);
					}
				}
			}
		}

		// Are there any global variables we need to declare?
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			TypeEnum typE = met.uniqueType();
			if (typE != TypeEnum.VOID)
			{
				AbstractType abstrType = generator.transformType(typE, null, this);

				// System.err.println("****** Found var " + met._symbolName);
				AbstractExpression initExpr = null;
				AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName,
						null, abstrType, initExpr, this);
				generator.addStatement(dataStmt, this);
			}
		}

		// Transform all the global data and logic, etc.
		// Transform all the Function definitions first
		for (Python_ComplexStatement stmt4 : entries._elements)
		{
			AbstractToken which4 = stmt4.statementOrComment.getWhich();
			if (which4 instanceof Python_SameLineStatement)
			{
				Python_SameLineStatement stmt5 = (Python_SameLineStatement) which4;
				int numStmts5 = stmt5.statements.getPrimaryCount();
				for (int i = 0; i < numStmts5; i++)
				{
					Python_Statement stmt6 = stmt5.statements.getPrimaryElement(i);
					Collection<AbstractStatement> newStmts6 = transformer.transformStatement(generator, stmt6);
					if (newStmts6 != null)
					{
						for (AbstractStatement newStmt6 : newStmts6)
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
