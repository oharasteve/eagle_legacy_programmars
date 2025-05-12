// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2024

package com.eagle.programmar.COBOL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.COBOL.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformUntil;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformVarying;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_PerformInline extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) @OPT TokenList<COBOL_PerformClause> clauseList;
	public @S(20) TokenList<COBOL_StatementOrComment> statements;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		COBOL_PerformVarying varyingClause = null;
		COBOL_PerformUntil untilClause = null;
		int i = 0;
		int incr = 0;
		
		for (COBOL_PerformClause clause : clauseList._elements)
		{
			AbstractToken which = clause.getWhich();
			if (which instanceof COBOL_PerformVarying)
			{
				varyingClause = (COBOL_PerformVarying) which;
				i = interpreter.getIntValue(varyingClause.from);
				incr = interpreter.getIntValue(varyingClause.by);
			}
			else if (which instanceof COBOL_PerformUntil)
			{
				untilClause = (COBOL_PerformUntil) which;
			}
		}

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		// Evaluate the paragraph
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			if (varyingClause != null)
			{
				interpreter.setSymbol(varyingClause, varyingClause.id.getValue(), new EagleInteger(i));
			}
			if (untilClause != null)
			{
				boolean stop = interpreter.getBoolValue(untilClause.condition);
				if (stop) break;
			}
			
			metric.iterate();

			for (COBOL_StatementOrComment sentence : statements._elements)
			{
				result = interpreter.tryToInterpret(sentence.getWhich());
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
			
			if (varyingClause != null)	// Don't need this test really
			{
				i += incr;
			}
		}
		
		_metrics.competedLoop(metric);
		return result;
	}
}