// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.IntelASM.IntelASM_Label;
import com.eagle.programmar.IntelASM.IntelASM_Program;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Reference;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.TokenSequence;

public class IntelASM_CALL extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_Keyword CALL = new IntelASM_Keyword("CALL");
	public @S(20) IntelASM_Label_Reference label;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

		// Look up the label
		String name = label.getValue().toUpperCase();
		
		interpreter.callingFunction(name, null);

		// Prepare to evaluate the method
		long startTime = System.nanoTime();
		state._calls.push(state._nextInstruction);

		// And transfer control to the label
		state._nextInstruction = state._labels.get(name);
		IntelASM_Program lang = (IntelASM_Program) interpreter._lang;
		IntelASM_Label fn = (IntelASM_Label) lang.lines._elements.get(state._nextInstruction).getWhich();
		
		// There is no direct result from a CALL in assembler.
		// Usually stored in some register or another.
		long elapsedTime = System.nanoTime() - startTime;
		
		if (fn._callMetrics == null)
		{
			fn._callMetrics = new CallMetrics(interpreter._metrics, name, this);
		}
		fn._callMetrics.addCallFrom(this, elapsedTime);

		// Now remove all those parameters
		interpreter.completedFunction(name, null);
	}
}