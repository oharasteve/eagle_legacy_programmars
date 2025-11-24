// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.Basic.Basic_StateMachine;
import com.eagle.programmar.Basic.Basic_Variable;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Basic_ReadStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_Keyword READ = new Basic_Keyword("READ");
	public @S(20) SeparatedList<Basic_Variable, PunctuationComma> variables;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Basic_StateMachine state = (Basic_StateMachine) interpreter._state;
		for (int i = 0; i < variables.getPrimaryCount(); i++)
		{
			Basic_Variable var = variables.getPrimaryElement(i);
			int data = state.getDataValue();
			var.assignValue(interpreter, new EagleInteger(data));
		}
	}
}
