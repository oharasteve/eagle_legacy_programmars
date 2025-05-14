// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.IfCondMetrics;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Basic_StateMachine;
import com.eagle.programmar.Basic.Basic_Statement.Basic_BaseStatement;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
import com.eagle.programmar.Basic.Terminals.Basic_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Basic_IfStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) Basic_Keyword IF = new Basic_Keyword("IF");
	public @S(20) Basic_Expression condition;
	public @S(30) Basic_KeywordChoice THEN = new Basic_KeywordChoice("THEN", "THE");
	public @S(40) Basic_IfWhat ifWhat;

	private @SKIP ArrayList<IfCondMetrics> _metrics = null;

	public static class Basic_IfWhat extends TokenChooser
	{
		public @FIRST Basic_Number XXlabel;
		public @CHOICE Basic_BaseStatement XXstatement;
	}
	
	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		if (_metrics == null)
		{
			// Had to delay to make sure line number etc are all set
			_metrics = new ArrayList<IfCondMetrics>();
			_metrics.add(new IfCondMetrics(interpreter._metrics, this));
		}
	
		boolean cond1 = interpreter.getBoolValue(condition);
		_metrics.get(0).completedIf(cond1);

		if (cond1)
		{
			if (ifWhat.getWhich() instanceof Basic_BaseStatement)
			{
				Basic_BaseStatement stmt = (Basic_BaseStatement) ifWhat.getWhich();
				result = interpreter.tryToInterpret(stmt);
			}
			else // Must be a Basic_Number
			{
				Basic_Number label = (Basic_Number) ifWhat.getWhich();
				int lbl = Integer.parseInt(label.getValue());
				Basic_StateMachine state = (Basic_StateMachine) interpreter._state;
				state.gotoStatement(lbl); // Goto this label
			}
		}

		return result;
	}
}
