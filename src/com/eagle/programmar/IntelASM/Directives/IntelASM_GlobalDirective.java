// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.TokenSequence;

public class IntelASM_GlobalDirective extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_Keyword GLOBAL = new IntelASM_Keyword("GLOBAL");
	public @S(20) IntelASM_Identifier_Reference id;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
		state._startLabel = id.getValue().toUpperCase();
	}
}
