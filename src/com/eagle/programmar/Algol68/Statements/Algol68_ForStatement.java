// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Statement;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.programmar.Algol68.Terminals.Algol68_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Algol68_ForStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) Algol68_Keyword FOR = new Algol68_Keyword("FOR");
	public @S(20) Algol68_Variable var;
	public @S(30) TokenList<Algol68_ForClause> clauses;
	public @S(40) Algol68_Keyword DO = new Algol68_Keyword("DO");
	public @S(50) TokenList<Algol68_Statement> statements;
	public @S(60) Algol68_Keyword OD = new Algol68_Keyword("OD");
	public @S(70) @OPT PunctuationSemicolon semicolon;

	private @SKIP ForLoopMetrics _metrics = null;

	public static class Algol68_ForClause extends TokenSequence
	{
		public @S(10) Algol68_KeywordChoice FROM = new Algol68_KeywordChoice("FROM", "BY", "TO", "WHILE");
		public @S(20) Algol68_Expression expr;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		boolean haveFrom = false;
		boolean haveTo = false;
		int start = 0;
		int stop = 0;
		int by = 1;
		Algol68_Expression whileExpr = null;
		
		for (Algol68_ForClause clause : clauses._elements)
		{
			switch (clause.FROM.getValue())
			{
			case "BY":
				by = interpreter.getIntValue(clause.expr);
				break;
			case "FROM":
				start = interpreter.getIntValue(clause.expr);
				haveFrom = true;
				break;
			case "TO":
				stop = interpreter.getIntValue(clause.expr);
				haveTo = true;
				break;
			case "WHILE":
				whileExpr = clause.expr;
				break;
			}
		}
		
		if (!haveFrom) throw new RuntimeException("FOR FROM is required");
		if (!haveTo) throw new RuntimeException("FOR TO is required");
		
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, getFileName(), getStartLine(), getStartChar());
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		int i = start;
		while (true)
		{
			if (by > 0 && i > stop) break;
			if (by < 0 && i < stop) break;

			metric.iterate();
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
					var.vars.first().getValue(), new EagleInteger(i));

			for (Algol68_Statement stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
				
				if (whileExpr != null)
				{
					boolean whileResult = interpreter.getBoolValue(whileExpr);
					if (!whileResult)
					{
						result = Eagle_Statement_Result.BREAK;
						break;
					}
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

			i += by;
		}

		_metrics.competedLoop(metric);
		return result;
	}
}
