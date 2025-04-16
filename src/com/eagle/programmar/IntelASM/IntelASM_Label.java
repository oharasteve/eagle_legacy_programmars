// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

package com.eagle.programmar.IntelASM;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.JumpMetrics;
import com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Definition;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class IntelASM_Label extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_Label_Definition label;
	public @S(20) @NOSPACE PunctuationColon colon;
	
	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP JumpMetrics _jumpMetrics = null;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
		String lbl = label.getValue().toUpperCase();
		if (state._labels.containsKey(lbl))
		{
			throw new RuntimeException("Duplicate label: " + lbl);
		}
		
		switch (state._section)
		{
		case RODATA:
		case DATA:
			EagleInteger val = new EagleInteger(state._memoryUsed);
			interpreter.setSymbol(label, lbl, val);
			break;
		default:
			// System.out.println("******** Setting label " + lbl + " to " + state._currentLine);
			state._labels.put(lbl, state._currentLine);
			break;
		}
	}
}
