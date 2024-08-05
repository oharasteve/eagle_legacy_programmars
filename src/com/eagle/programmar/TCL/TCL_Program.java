// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Statement.TCL_BaseStatement;
import com.eagle.tokens.TokenList;

public class TCL_Program extends EagleLanguage implements EagleRunnable
{
	public static final String TCL = "TCL";

	public TCL_Program()
	{
		super(TCL, new TCL_Syntax());
	}

	@Override
	public String booleanName(boolean flag)
	{
		if (flag) return "$true";
		return "$false";
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.tcl.tk/man/tcl8.7/";
	}

	public @S(10) TokenList<TCL_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the method definitions
		for (TCL_Statement stmt : statements._elements)
		{
			for (int i = 0; i < stmt.compoundStatement.statements.getPrimaryCount(); i++)
			{
				TCL_BaseStatement base = stmt.compoundStatement.statements.getPrimaryElement(i);
				if (base.getWhich() instanceof TCL_Procedure)
				{
					TCL_Procedure proc = (TCL_Procedure) base.getWhich();
					interpreter._functionList.put(proc.name.getValue(), proc);
					if (interpreter._TRACE)
					{
						System.err.println("*** Found TCL procedure " + proc.name.getValue());
					}
				}
			}
		}

		// Second pass, run any stuff in the outermost 'object'
		for (TCL_Statement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}
