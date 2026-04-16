// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.SQL.Statements.SQL_CreateFunctionStatement;
import com.eagle.programmar.SQL.Statements.SQL_CreateProcedureStatement;
import com.eagle.programmar.SQL.Terminals.SQL_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class SQL_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String SQL = "SQL";

	public SQL_Program()
	{
		super(SQL, new SQL_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/sql/";
	}

	public @S(10) TokenList<SQL_StatementOrComment> statements;

	public static class SQL_StatementOrComment extends TokenChooser
	{
		public @CHOICE SQL_Statement XXstatement;
		public @CHOICE SQL_Comment XXcomment;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the CREATE PROCEDURE calls
		for (SQL_StatementOrComment elt : statements._elements)
		{
			if (elt.getWhich() instanceof SQL_Statement)
			{
				SQL_Statement stmt = (SQL_Statement) elt.getWhich();
				if (stmt.getWhich() instanceof SQL_CreateProcedureStatement)
				{
					SQL_CreateProcedureStatement proc = (SQL_CreateProcedureStatement) stmt.getWhich();
					interpreter.addFunction(proc.procName.getValue(), proc);
				}
				else if (stmt.getWhich() instanceof SQL_CreateFunctionStatement)
				{
					SQL_CreateFunctionStatement func = (SQL_CreateFunctionStatement) stmt.getWhich();
					interpreter.addFunction(func.funcName.getValue(), func);
				}
			}
		}

		// Second pass, run any stuff in the top-level program
		for (SQL_StatementOrComment elt : statements._elements)
		{
			if (elt.getWhich() instanceof SQL_Statement)
			{
				SQL_Statement stmt = (SQL_Statement) elt.getWhich();
				interpreter.tryToInterpret(stmt);
			}
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// First pass, transform all the procedure definitions
		for (SQL_StatementOrComment elt : statements._elements)
		{
			if (elt.getWhich() instanceof SQL_Statement)
			{
				SQL_Statement stmt = (SQL_Statement) elt.getWhich();
				if (stmt.getWhich() instanceof EagleTransformableFunction)
				{
					EagleTransformableFunction transformable = (EagleTransformableFunction) stmt.getWhich();
					transformable.transformFunction(transformer, generator);
				}
				else if (elt.getWhich() instanceof SQL_Comment)
				{
					SQL_Comment comm = (SQL_Comment) elt.getWhich();
					generator.addComment(comm.getValue(), comm);
				}
			}
		}

		// Are there any global variables we need to declare?
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			// System.err.println("****** Found var " + met._symbolName);
			SQL_Type type = SQL_Type.newPrimitiveType("VARCHAR");
			AbstractType abstrType = SQL_Type.findAbstractType(generator, type);
			AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName,
					null, abstrType, null, this);
			generator.addStatement(dataStmt, this);
		}

		// Second pass, transform all the data and logic
		for (SQL_StatementOrComment elt : statements._elements)
		{
			if (elt.getWhich() instanceof SQL_Statement)
			{
				SQL_Statement stmt = (SQL_Statement) elt.getWhich();
				Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, stmt);
					}
				}
			}
		}

//		// Not needed for C# or Java, but Python needs this
//		generator.addCallToMain();

		return generator.getTransformedProgram();
	}
}
