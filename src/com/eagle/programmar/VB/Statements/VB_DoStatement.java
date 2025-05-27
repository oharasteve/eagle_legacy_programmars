// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2024

package com.eagle.programmar.VB.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Element;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class VB_DoStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("statements/do-loop-statement") VB_Keyword DO = new VB_Keyword("Do");
	public @S(20) @OPT VB_DoCondition cond1;
	public @S(30) VB_EndOfLine eoln;
	public @S(40) TokenList<VB_Element> actions;
	public @S(50) VB_Keyword LOOP = new VB_Keyword("Loop");
	public @S(60) @OPT VB_DoCondition cond2;
	
	public static class VB_DoCondition extends TokenSequence
	{
		public @S(10) VB_KeywordChoice WHILE = new VB_KeywordChoice("While", "Until");
		public @S(20) VB_Expression condition;
	}
	
	private @SKIP ForLoopMetrics _metrics = null;
	
	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, DO);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			if (cond1 != null && cond1.isPresent())
			{
				boolean cond = interpreter.getBoolValue(cond1.condition);
				if (cond1.WHILE.toString().toLowerCase().equals("while"))
				{
					cond = !cond;
				}
				if (cond) break;
			}

			metric.iterate();
			
			for (VB_Element stmt : actions._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}

			if (result == Eagle_Statement_Result.BREAK)
			{
				metric.broke();
				result = Eagle_Statement_Result.NORMAL;
				break;
			}
			else if (result == Eagle_Statement_Result.CONTINUE)
			{
				metric.continued();
				result = Eagle_Statement_Result.NORMAL;
			}
			else if (result == Eagle_Statement_Result.RETURN)
			{
				break;
			}
			
			if (cond2 != null && cond2.isPresent())
			{
				boolean cond = interpreter.getBoolValue(cond2.condition);
				if (cond2.WHILE.toString().toLowerCase().equals("while"))
				{
					cond = !cond;
				}
				if (cond) break;
			}
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
