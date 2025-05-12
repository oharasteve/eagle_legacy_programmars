// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Bash.Bash_Condition;
import com.eagle.programmar.Bash.Bash_EndOfLine;
import com.eagle.programmar.Bash.Bash_Statement;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_WhileStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("#Looping-Constructs") Bash_Keyword WHILE = new Bash_Keyword("while");
	public @S(20) Bash_Condition condition;
	public @S(30) Bash_EndOfLine eoln1;
	public @S(40) Bash_Keyword DO = new Bash_Keyword("do");
	public @S(50) @OPT Bash_EndOfLine eoln2;
	public @S(60) TokenList<Bash_Statement> statements;
	public @S(70) Bash_Keyword DONE = new Bash_Keyword("done");
	
	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean keepGoing = interpreter.getBoolValue(condition);
			if (!keepGoing) break;

			metric.iterate();
			for (Bash_Statement stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt.element);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
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
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
