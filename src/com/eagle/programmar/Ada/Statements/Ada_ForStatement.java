// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Ada_Variable;
import com.eagle.programmar.Ada.Expressions.Ada_RangeExpression;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ada_ForStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) Ada_Keyword FOR = new Ada_Keyword("for");
	public @S(20) Ada_Variable var;
	public @S(30) Ada_Keyword IN = new Ada_Keyword("in");
	public @S(40) @OPT Ada_Keyword REVERSE = new Ada_Keyword("reverse");
	public @S(50) Ada_Expression values;
	public @S(60) Ada_Keyword LOOP = new Ada_Keyword("loop");
	public @S(70) TokenList<Ada_Statement> statements;
	public @S(80) Ada_Keyword END = new Ada_Keyword("end");
	public @S(90) Ada_Keyword LOOP2 = new Ada_Keyword("loop");
	public @S(100) PunctuationSemicolon semicolon;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (!(values.getWhich() instanceof Ada_RangeExpression))
		{
			throw new RuntimeException("FOR statement requires a Range of values");
		}
		Ada_RangeExpression range = (Ada_RangeExpression) values.getWhich();
		int start = interpreter.getIntValue(range.left);
		int stop = interpreter.getIntValue(range.right);

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar());
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		boolean backwards = REVERSE.isPresent();

		int i = start;
		if (backwards) i = stop;
		while (true)
		{
			if (!backwards && i > stop) break;
			if (backwards && i < start) break;

			metric.iterate();
			interpreter.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
					var.vars.first().getValue(), new EagleInteger(i));

			for (Ada_Statement stmt : statements._elements)
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

			if (backwards)
				i--;
			else
				i++;
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
