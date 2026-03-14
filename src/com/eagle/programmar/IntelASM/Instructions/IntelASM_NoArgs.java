// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class IntelASM_NoArgs extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_KeywordChoice CMD = new IntelASM_KeywordChoice(
			"CLD", "LODSB", "MOVSB", "NOP", "RET", "STD", "STOSB");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

		switch (CMD.toString().toUpperCase())
		{
		case "RET":
			state._nextInstruction = state._calls.pop().intValue();
			break;
		default:
			throw new RuntimeException("Unable to run command: " + CMD);
		}
	}
}
