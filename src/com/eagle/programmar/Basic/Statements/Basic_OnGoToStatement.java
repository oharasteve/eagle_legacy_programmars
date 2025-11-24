// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 20, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Basic_StateMachine;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.programmar.Basic.Terminals.Basic_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Basic_OnGoToStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable
{
	public @S(10) Basic_Keyword ON = new Basic_Keyword("ON");
	public @S(20) Basic_Expression expr;
	public @S(30) Basic_Keyword GOTO = new Basic_Keyword("GOTO");
	public @S(40) SeparatedList<Basic_Number, PunctuationComma> labels;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Basic_StateMachine state = (Basic_StateMachine) interpreter._state;
		int index = interpreter.getIntValue(expr);
		if (index < 1 || index > labels.getPrimaryCount())
		{
			throw new RuntimeException("ON / GOTO invalid index: " + index);
		}
		Basic_Number lbl = labels.getPrimaryElement(index - 1);
		int label = Integer.parseInt(lbl.getValue());
		state.gotoStatement(label); // Goto this label
	}
}
