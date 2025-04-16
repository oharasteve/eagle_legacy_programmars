// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 9, 2025

package com.eagle.programmar.IntelASM;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.programmar.IntelASM.Symbols.IntelASM_Identifier_Reference;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class IntelASM_Variable extends TokenChooser
{
	public @CHOICE IntelASM_Register XXreg;
	
	public @CHOICE static class IntelASM_Brackets_Register extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @NOSPACE IntelASM_Register register;
		public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
	}
	
	public @CHOICE static class IntelASM_Brackets_Address extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @NOSPACE IntelASM_Identifier_Reference id;
		public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
	}
	
	public int getValue(EagleInterpreter interpreter)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
		
		AbstractToken which = this.getWhich();
		if (which instanceof IntelASM_Register)
		{
			IntelASM_Register reg = (IntelASM_Register) which;
			return reg.getValue(state);
		}
		else
		{
			throw new RuntimeException("Unexpected variable: " + which.getClass().getCanonicalName());
		}
	}
	
	public void setValue(EagleInterpreter interpreter, int value)
	{
		IntelASM_StateMachine state = (IntelASM_StateMachine) interpreter._state;
		
		AbstractToken which = this.getWhich();
		if (which instanceof IntelASM_Register)
		{
			IntelASM_Register reg = (IntelASM_Register) which;
			reg.setValue(state, value);
		}
		else if (which instanceof IntelASM_Brackets_Register)
		{
			IntelASM_Brackets_Register brack = (IntelASM_Brackets_Register) which;
			IntelASM_Register reg = brack.register;
			int index = reg.getValue(state);
			state.setMemory1(index, value);
		}
		else if (which instanceof IntelASM_Brackets_Address)
		{
			IntelASM_Brackets_Address addr = (IntelASM_Brackets_Address) which;
			int index = interpreter.getIntValue(addr.id);
			state.setMemory4(index, value);
		}
		else
		{
			throw new RuntimeException("Unexpected variable: " + which.getClass().getCanonicalName());
		}
	}
}
