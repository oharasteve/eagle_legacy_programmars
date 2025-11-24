// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 11, 2025

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.IntelASM_Register;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IntelASM_CMP extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_Keyword CMP = new IntelASM_Keyword("CMP");
	public @S(20) IntelASM_Register reg;
	public @S(30) @NOSPACE PunctuationComma comma;
	public @S(40) IntelASM_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

		int val = interpreter.getIntValue(expr);
		state._flag = reg.compValue(state, val);
	}
}