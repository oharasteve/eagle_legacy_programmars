// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IntelASM_DQ extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_Keyword DQ = new IntelASM_Keyword("DQ");
	public @S(20) SeparatedList<IntelASM_Expression,PunctuationComma> args;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
		
		int numArgs = args.getPrimaryCount();
		for (int j = 0; j < numArgs; j++)
		{
			IntelASM_Expression expr = args.getPrimaryElement(j);
			int val = interpreter.getIntValue(expr);
			switch (state._section)
			{
			case RODATA:
			case DATA:
				state.setMemory4(state._memoryUsed, val);
				state._memoryUsed += 8; // Yeah, yeah, I know. Should be 4 for DW
				break;
			default:
				throw new RuntimeException("DQ must be in .rodata or .data");
			}
		}
	}
}
