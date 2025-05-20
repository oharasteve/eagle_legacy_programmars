// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 20, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_Expression;
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
	public @S(40) SeparatedList<Basic_Number,PunctuationComma> lbls;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		throw new RuntimeException("Need to implement");
//		
//		Basic_StateMachine state = (Basic_StateMachine) interpreter._state;
//		
//		int label = Integer.parseInt(lbl.getValue());
//		int save = state.getCurrentStatement();
//		state.gotoStatement(label); // Gosub this label
//		
//		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
//		while (true)
//		{
//			Basic_Statement stmt = state.nextStatement();
//			if (stmt == null)
//			{
//				break;
//			}
//			
//			// Recursive ... goes away for a while
//			result = interpreter.tryToInterpret(stmt);
//			if (result != Eagle_Statement_Result.NORMAL)
//			{
//				break;
//			}
//		}
//		
//		if (result == Eagle_Statement_Result.RETURN)
//		{
//			state.setCurrentStatement(save);
//		}
	}
}
