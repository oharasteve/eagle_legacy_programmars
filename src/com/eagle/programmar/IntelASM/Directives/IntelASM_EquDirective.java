// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Directives;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.IntelASM_StateMachine;
import com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Definition;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class IntelASM_EquDirective extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_Label_Definition label;
	public @S(20) @OPT @CURIOUS("Extra colon") PunctuationColon colon;
	public @S(30) IntelASM_Keyword EQU = new IntelASM_Keyword("EQU");
	public @S(40) IntelASM_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
		String lbl = label.getValue().toUpperCase();
		if (state._labels.containsKey(lbl))
		{
			throw new RuntimeException("Duplicate label: " + lbl);
		}

		int k = interpreter.getIntValue(expr);
		EagleInteger val = new EagleInteger(k);
		interpreter.setSymbol(label, lbl, val);
	}
}
