// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2013

package com.eagle.programmar.Python;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleSyntax;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_Statement_List;
import com.eagle.programmar.Python.Statements.Python_FunctionDefinition;
import com.eagle.programmar.Python.Symbols.Python_Function_Definition;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;

public abstract class Python_Program extends EagleLanguage implements EagleRunnable
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

	public @S(10) @OPT TokenList<Python_Statement> entries;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		interpreter._functionList = new ArrayList<AbstractFunction>();
		for (Python_Statement stmt : entries._elements)
		{
			AbstractToken which = stmt.statementOrComment.getWhich();
			if (which instanceof Python_Statement_List)
			{
				Python_Statement_List stmts = (Python_Statement_List) which;
				for (int i = 0; i < stmts.statements.getPrimaryCount(); i++)
				{
					Python_Simple_Statement simple = stmts.statements.getPrimaryElement(i);
					if (simple.getWhich() instanceof Python_FunctionDefinition)
					{
						Python_FunctionDefinition fn = (Python_FunctionDefinition) simple.getWhich();
						if (fn.fnName.getWhich() instanceof Python_Function_Definition)
						{
							Python_Function_Definition name = (Python_Function_Definition) fn.fnName.getWhich();
							interpreter._functionList.add(fn);
							if (interpreter._TRACE) System.err.println("*** Found function " + name.getValue());
						}
					}
				}
			}
		}

		// Second pass, execute the program
		for (Python_Statement stmt : entries._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}
