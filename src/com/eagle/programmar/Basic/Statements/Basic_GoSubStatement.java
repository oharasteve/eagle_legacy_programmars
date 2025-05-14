// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.programmar.Basic.Basic_StateMachine;
import com.eagle.programmar.Basic.Basic_Statement;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.programmar.Basic.Terminals.Basic_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Basic_GoSubStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable
{
	public @S(10) Basic_Keyword GOSUB = new Basic_Keyword("GOSUB");
	public @S(20) Basic_Number lbl;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Basic_StateMachine state = (Basic_StateMachine) interpreter._state;
		
		int label = Integer.parseInt(lbl.getValue());
		int save = state.getCurrentStatement();
		state.gotoStatement(label); // Gosub this label
		
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			Basic_Statement stmt = state.nextStatement();
			if (stmt == null)
			{
				break;
			}
			
			// Recursive ... goes away for a while
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}
		
		if (result == Eagle_Statement_Result.RETURN)
		{
			state.setCurrentStatement(save);
		}
	}
}
