// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Fortran_DoStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("6j4m0vn8c/index.html") Fortran_Keyword DO1 = new Fortran_Keyword("DO");
	public @S(20) Fortran_Variable_Reference var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Fortran_Expression startValue;
	public @S(50) PunctuationComma comma;
	public @S(60) Fortran_Expression stopValue;
	public @S(70) @OPT Fortran_DoIncrement incrValue;
	public @S(80) Fortran_EOLN eoln1;
	public @S(90) TokenList<Fortran_Statement> statements;
	public @S(100) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(110) Fortran_Keyword DO2 = new Fortran_Keyword("DO");
	public @S(120) Fortran_EOLN eoln2;

	public static class Fortran_DoIncrement extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Fortran_Expression incr;
	}

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		int start = interpreter.getIntValue(startValue);
		int stop = interpreter.getIntValue(stopValue);
		int incr = 1;
		if (incrValue != null && incrValue.isPresent())
		{
			incr = interpreter.getIntValue(incrValue.incr);
		}

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		int i = start;
		while (true)
		{
			if (incr > 0 && i > stop) break;
			if (incr < 0 && i < stop) break;

			metric.iterate();
			interpreter.setSymbol(var, var.toString(), new EagleInteger(i));

			for (Fortran_Statement stmt : statements._elements)
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

			i += incr;
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
