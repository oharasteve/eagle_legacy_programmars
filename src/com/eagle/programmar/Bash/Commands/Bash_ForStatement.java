// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Bash.Bash_EndOfLine;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Element;
import com.eagle.programmar.Bash.Expressions.Bash_RangeExpression;
import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_ForStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("#Looping-Constructs") Bash_Keyword FOR = new Bash_Keyword("for");
	public @S(20) Bash_Identifier_Reference id;
	public @S(30) Bash_Keyword IN = new Bash_Keyword("in");
	public @S(40) TokenList<Bash_Expression> values;
	public @S(50) Bash_EndOfLine eoln1;
	public @S(60) Bash_Keyword DO = new Bash_Keyword("do");
	public @S(70) @OPT Bash_EndOfLine eoln2;
	public @S(80) TokenList<Bash_Element> statements;
	public @S(90) Bash_Keyword DONE = new Bash_Keyword("done");

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (!(values._elements.get(0).getWhich() instanceof Bash_RangeExpression))
		{
			throw new RuntimeException("FOR statement requires a Range of values");
		}
		Bash_RangeExpression range = (Bash_RangeExpression) values._elements.get(0).getWhich();
		int start = interpreter.getIntValue(range.start);
		int stop = interpreter.getIntValue(range.stop);

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		int i = start;
		while (true)
		{
			if (start < stop && i > stop) break;
			if (start > stop && i < stop) break;

			metric.iterate();
			interpreter.setSymbol(id, id.getValue(), new EagleInteger(i));

			for (Bash_Element stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt.element);
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

			if (start < stop) i++;
			if (start > stop) i--;
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
