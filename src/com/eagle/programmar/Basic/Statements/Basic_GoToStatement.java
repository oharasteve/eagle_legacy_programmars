// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 20, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_StateMachine;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.programmar.Basic.Terminals.Basic_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Basic_GoToStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable
{
	public @S(10) Basic_Keyword GOTO = new Basic_Keyword("GOTO");
	public @S(20) Basic_Number lbl;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Basic_StateMachine state = (Basic_StateMachine) interpreter._state;
		int label = Integer.parseInt(lbl.getValue());
		state.gotoStatement(label); // Goto this label. No Deposit. No Return.
	}
}
