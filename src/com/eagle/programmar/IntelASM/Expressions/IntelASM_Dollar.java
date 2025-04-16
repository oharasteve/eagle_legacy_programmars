// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 9, 2025

package com.eagle.programmar.IntelASM.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class IntelASM_Dollar extends PrimaryOperator implements EagleRunnable
{
	public @S(10) IntelASM_Punctuation dollar = new IntelASM_Punctuation('$');

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
		interpreter.pushInt(state._dollarLine);
	}
}
