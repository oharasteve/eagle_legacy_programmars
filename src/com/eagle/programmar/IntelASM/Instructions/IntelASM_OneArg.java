// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.IntelASM_Register;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class IntelASM_OneArg extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_KeywordChoice CMD = new IntelASM_KeywordChoice(
			"DEC", "DIV", "INC", "MUL", "NEG",
			"POP", "PUSH", "REP", "REPZ", "SETZ");
	public @S(20) IntelASM_Register reg;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

		switch (CMD.toString().toUpperCase())
		{
		case "DEC":
			int val1 = reg.getValue(state);
			reg.setValue(state, val1 - 1);
			break;
		case "DIV":
			// Divide RAX by reg, result in RAX, remainder in RDX
			int divisor = reg.getValue(state);
			int quotient = state._RAX / divisor;
			int remainder = state._RAX % divisor;
			// System.out.println("*** " + state._RAX + " / " + divisor + " = " + quotient + " rem " + remainder);
			state._RAX = quotient;
			state._RDX = remainder;
			break;
		case "INC":
			int val2 = reg.getValue(state);
			reg.setValue(state, val2 + 1);
			break;
		case "MUL":
			int mult = reg.getValue(state);
			state._RAX = state._RAX * mult;
			break;
		default:
			throw new RuntimeException("Unable to run command: " + CMD);
		}
	}
}
