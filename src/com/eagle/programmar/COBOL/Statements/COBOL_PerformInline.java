// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2024

package com.eagle.programmar.COBOL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.COBOL.COBOL_StatementOrComment;
import com.eagle.programmar.COBOL.Statements.COBOL_PerformClause.COBOL_PerformVarying;
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
		if (clauseList == null || clauseList.size() != 1)
		{
			throw new RuntimeException("Can only handle simple inline PERFORMs right now");
		}
		COBOL_PerformVarying clause = (COBOL_PerformVarying) clauseList._elements.get(0).getWhich();

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, this);
		}
		ForLoopMetric metric = new ForLoopMetric();

		// Evaluate the paragraph
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		int i = interpreter.getIntValue(clause.from);
		int incr = interpreter.getIntValue(clause.by);
		while (true)
		{
			interpreter.setSymbol(clause, clause.id.getValue(), new EagleInteger(i));

			boolean stop = interpreter.getBoolValue(clause.until.condition);
			if (stop) break;
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
			
			i += incr;
		}
		
		_metrics.competedLoop(metric);
		return result;
	}
}