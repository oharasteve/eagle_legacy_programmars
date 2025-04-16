// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 10, 2025

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.TokenSequence;

public class IntelASM_SysCall extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) IntelASM_Keyword CMD = new IntelASM_Keyword("SYSCALL");

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
		
		int rax = state._RAX;
		switch (rax)
		{
		case 1: // Print
			int stream = state._RDI;
			if (stream != 1)
			{
				throw new RuntimeException("Can only print to stdout");
			}
			int pos = state._RSI;
			int nc = state._RDX;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < nc; i++)
			{
				sb.append((char) state.getMemory1(pos + i));
			}
			System.out.print(sb.toString()); // Should have its own line-feed
			break;
		case 60: // Exit
			interpreter._exitCode = state._RDI;
			return Eagle_Statement_Result.RETURN;
		default:
			throw new RuntimeException("Unable to handle SYSCALL " + rax);
		}
		return Eagle_Statement_Result.NORMAL;
	}
}
