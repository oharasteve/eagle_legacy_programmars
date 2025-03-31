// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.SQL.Statements.SQL_CreateProcedureStatement;
import com.eagle.programmar.SQL.Terminals.SQL_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class SQL_Program extends AbstractLanguage implements EagleRunnable
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
					interpreter.addFunction(proc.proc.getValue(), proc);
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
}
