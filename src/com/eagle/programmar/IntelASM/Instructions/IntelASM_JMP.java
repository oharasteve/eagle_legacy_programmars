// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.JumpMetrics;
import com.eagle.programmar.IntelASM.IntelASM_Label;
import com.eagle.programmar.IntelASM.IntelASM_Program;
import com.eagle.programmar.IntelASM.IntelASM_Program.IntelASM_Line;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class IntelASM_JMP extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_KeywordChoice JMP = new IntelASM_KeywordChoice(
			"JC", "JE", "JG", "JGE", "JL", "JLE",
			"JMP", "JNE", "JNZ", "JZ");
	public @S(20) IntelASM_Label_Reference label;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

		boolean jump;
		switch (JMP.getValue().toUpperCase())
		{
		case "JMP":
			jump = true;
			break;
		case "JL":
			jump = state._flag < 0;
			break;
		case "JLE":
			jump = state._flag <= 0;
			break;
		case "JNE":
			jump = state._flag != 0;
			break;
		case "JE":
			jump = state._flag == 0;
			break;
		case "JGE":
			jump = state._flag >= 0;
			break;
		case "JG":
			jump = state._flag > 0;
			break;
		default:
			throw new RuntimeException("Unable to handle " + JMP.getValue() + " yet.");
		}

		if (jump)
		{
			// Look up the label
			String name = label.getValue().toUpperCase();

			// And transfer control to the label
			state._nextInstruction = state._labels.get(name);

			// Update metrics
			IntelASM_Program lang = (IntelASM_Program) interpreter._lang;
			IntelASM_Line line = lang.lines._elements.get(state._nextInstruction);
			IntelASM_Label fn = (IntelASM_Label) line.getWhich();

			if (fn._jumpMetrics == null)
			{
				fn._jumpMetrics = new JumpMetrics(interpreter._metrics, name, fn);
			}
			fn._jumpMetrics.addJumpFrom(this);
		}
	}
}
