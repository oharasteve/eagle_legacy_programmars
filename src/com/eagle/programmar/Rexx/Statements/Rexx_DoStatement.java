// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Rexx.Rexx_Element;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference;
import com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine;
import com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Rexx_DoStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("instructions-do") Rexx_Keyword DO = new Rexx_Keyword("DO");
	public @S(20) @OPT Rexx_DoLoop loop;
	public @S(30) @OPT Rexx_DoWhile doWhile;
	public @S(40) Rexx_EndOfLine eoln1;
	public @S(50) TokenList<Rexx_Element> actions;
	public @S(60) Rexx_Keyword END = new Rexx_Keyword("END");
	
	public static class Rexx_DoLoop extends TokenSequence
	{
		public @S(10) Rexx_Identifier_Reference var;
		public @S(20) PunctuationEquals equals;
		public @S(30) Rexx_Expression from;
		public @S(40) Rexx_Keyword TO = new Rexx_Keyword("TO");
		public @S(50) Rexx_Expression to;
		public @S(60) @OPT Rexx_DoBy step;

		public static class Rexx_DoBy extends TokenSequence
		{
			public @S(10) Rexx_Keyword BY = new Rexx_Keyword("BY");
			public @S(20) Rexx_Expression step;
		}
	}
	
	public static class Rexx_DoWhile extends TokenSequence
	{
		public @S(10) Rexx_Keyword WHILE = new Rexx_Keyword("WHILE");
		public @S(20) Rexx_Expression condition;
	}
	
	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		// Just a DO ... END block, no iteration
		if ((loop == null || ! loop.isPresent()) && (doWhile == null || ! doWhile.isPresent()))
		{
			for (Rexx_Element stmt : actions._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			return result;
		}

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		int start = 0;
		int stop = 0;
		int by = 0;
		int current = 0;
		
		if (loop != null && loop.isPresent())
		{
			start = interpreter.getIntValue(loop.from);
			interpreter.setSymbol(this, loop.var.getValue(), new EagleInteger(start));
			
			current = interpreter.getIntValue(loop.from);
			stop = interpreter.getIntValue(loop.to);
			by = 1;
			
			if (loop.step != null && loop.step.isPresent())
			{
				by = interpreter.getIntValue(loop.step.step);
			}
		}
		
		while (true)
		{
			if (doWhile != null && doWhile.isPresent())
			{
				boolean keepGoing = interpreter.getBoolValue(doWhile.condition);
				if (!keepGoing) break;
			}

			if (loop != null && loop.isPresent())
			{
				if (by < 0)
				{
					if (current < stop) break;
				}
				else
				{
					if (current > stop) break;
				}
			}

			metric.iterate();
			interpreter.setSymbol(this, loop.var.getValue(), new EagleInteger(current));

			for (Rexx_Element stmt : actions._elements)
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

			if (loop != null && loop.isPresent())
			{
				current += by;
			}
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
