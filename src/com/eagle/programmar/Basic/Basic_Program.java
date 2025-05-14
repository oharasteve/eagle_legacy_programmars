// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_Statement.Basic_BaseStatement;
import com.eagle.programmar.Basic.Statements.Basic_DataStatement;
import com.eagle.programmar.Basic.Terminals.Basic_Number;
import com.eagle.tokens.TokenList;

public class Basic_Program extends AbstractLanguage implements EagleRunnable
{
	public static final String BASIC = "Basic";

	public Basic_Program()
	{
		super(BASIC, new Basic_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return null;
	}

	public @S(10) @OPT TokenList<Basic_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Basic_StateMachine state = new Basic_StateMachine();
		interpreter._state = state;
		
		// Pull out all the DATA lines and put them in the State Machine
		for (Basic_Statement statement : statements._elements)
		{
			int numStmt = statement.statements.getPrimaryCount();
			for (int i = 0; i < numStmt; i++)
			{
				Basic_BaseStatement stmt = statement.statements.getPrimaryElement(i);
				if (stmt.getWhich() instanceof Basic_DataStatement)
				{
					Basic_DataStatement data = (Basic_DataStatement) stmt.getWhich();
					int numData = data.values.getPrimaryCount();
					for (int j = 0; j < numData; j++)
					{
						Basic_Number num = data.values.getPrimaryElement(j);
						int val = Integer.parseInt(num.getValue());
						state.addDataValue(val);
					}
				}
			}
		}

		for (Basic_Statement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}
