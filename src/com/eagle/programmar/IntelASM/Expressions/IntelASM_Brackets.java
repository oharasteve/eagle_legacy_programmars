// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 9, 2025

package com.eagle.programmar.IntelASM.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class IntelASM_Brackets extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @NOSPACE IntelASM_Expression expr;
	public @S(30) @NOSPACE PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;

		int index = interpreter.getIntValue(expr);
		int value = state.getMemory4(index); 
		// System.out.println("******* brackets index = " + index + " value = " + value);
		interpreter.pushInt(value);
	}
}
