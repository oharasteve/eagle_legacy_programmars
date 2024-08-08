// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 18, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Label;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_DoStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @OPT PLI_Label label1;
	public @S(20) @DOC("7.15") PLI_Keyword DO = new PLI_Keyword("DO");
	public @S(30) @OPT PLI_DoLoop doLoop;
	public @S(40) @OPT PLI_DoUntil doUntil;
	public @S(50) @OPT PLI_DoWhile doWhile;
	public @S(60) @OPT PLI_Keyword FOREVER = new PLI_Keyword("FOREVER");
	public @S(70) PunctuationSemicolon semicolon1;
	public @S(80) @OPT TokenList<PLI_StatementOrComment> statements;
	public @S(90) PLI_Keyword END = new PLI_Keyword("END");
	public @S(100) @OPT PLI_Identifier_Reference label2;
	public @S(110) PunctuationSemicolon semicolon2;

	public static class PLI_DoLoop extends TokenSequence
	{
		public @S(10) PLI_Identifier_Reference var;
		public @S(20) PunctuationEquals equals;
		public @S(30) PLI_Expression fromExpr;
		public @S(40) PLI_Keyword TO = new PLI_Keyword("TO");
		public @S(50) PLI_Expression toExpr;
		public @S(60) @OPT PLI_DoBy by;

		public static class PLI_DoBy extends TokenSequence
		{
			public @S(10) PLI_Keyword BY = new PLI_Keyword("BY");
			public @S(20) PLI_Expression byExpr;
		}
	}

	public static class PLI_DoUntil extends TokenSequence
	{
		public @S(10) PLI_Keyword UNTIL = new PLI_Keyword("UNTIL");
		public @S(20) PLI_Expression condition;
	}

	public static class PLI_DoWhile extends TokenSequence
	{
		public @S(10) PLI_Keyword WHILE = new PLI_Keyword("WHILE");
		public @S(20) PLI_Expression condition;
	}
	
	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		boolean simpleDo = true;
		PLI_Expression whileCond = null;
		PLI_Expression untilCond = null;
		PLI_Identifier_Reference loopVar = null;
		int start = 0;
		int stop = 0;
		int step = 1;
		boolean hasLoop = false;
		
		if (doLoop != null && doLoop.isPresent())
		{
			hasLoop = true;
			loopVar = doLoop.var;
			start = interpreter.getIntValue(doLoop.fromExpr);
			stop = interpreter.getIntValue(doLoop.toExpr);
			if (doLoop.by != null && doLoop.by.isPresent())
			{
				step = interpreter.getIntValue(doLoop.by.byExpr);
			}
			simpleDo = false;
		}
		if (doWhile != null && doWhile.isPresent())
		{
			whileCond = doWhile.condition;
			simpleDo = false;
		}
		if (doUntil != null && doUntil.isPresent())
		{
			untilCond = doUntil.condition;
			simpleDo = false;
		}
		if (FOREVER != null && FOREVER.isPresent())
		{
			simpleDo = false;
		}
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		
		if (simpleDo)
		{
			// No iteration, no metrics, no logic, just a groups of statements
			for (PLI_StatementOrComment stmt : statements._elements)
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
		
		int i = start;
		while (true)
		{
			if (whileCond != null)
			{
				boolean cond = interpreter.getBoolValue(whileCond);
				if (!cond) break;
			}
			if (hasLoop)
			{
				if (step > 0 && i > stop) break;
				if (step < 0 && i < stop) break;
				interpreter.setSymbol(loopVar, loopVar.getValue(), new EagleInteger(i));
			}

			metric.iterate();

			for (PLI_StatementOrComment stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}

			if (result == Eagle_Statement_Result.RETURN)
			{
				break;
			}

			if (hasLoop)
			{
				i += step;
			}
			if (untilCond != null)
			{
				boolean cond = interpreter.getBoolValue(untilCond);
				if (cond) break;
			}
		}

		_metrics.competedLoop(metric);
		
		return result;
	}
}
