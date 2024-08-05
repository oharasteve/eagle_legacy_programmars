// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.VB.Statements.VB_FunctionDeclaration;
import com.eagle.programmar.VB.Statements.VB_SubDeclaration;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;

public class VB_Program extends EagleLanguage implements EagleRunnable
{
	public static final String VB = "VB";

	public VB_Program()
	{
		super(VB, new VB_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://msdn.microsoft.com/en-us/library/";
	}

	public @S(10) @OPT TokenList<VB_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the method definitions
		for (VB_Statement stmt : statements._elements)
		{
			AbstractToken which = stmt.baseStatement.getWhich();
			if (which instanceof VB_FunctionDeclaration)
			{
				VB_FunctionDeclaration func = (VB_FunctionDeclaration) which;
				interpreter._functionList.put(func.name.getValue(), func);
				if (interpreter._TRACE)
				{
					System.err.println("*** Found VB function " + func.name.getValue());
				}
			}
			if (which instanceof VB_SubDeclaration)
			{
				VB_SubDeclaration sub = (VB_SubDeclaration) which;
				interpreter._functionList.put(sub.name.getValue(), sub);
				if (interpreter._TRACE)
				{
					System.err.println("*** Found VB sub " + sub.name.getValue());
				}
			}
		}

		// Second pass, run any stuff in the outermost 'object'
		for (VB_Statement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt.baseStatement);
		}
	}
}
