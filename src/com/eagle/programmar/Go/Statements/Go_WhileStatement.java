// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 15, 2025

package com.eagle.programmar.Go.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Statement;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Go_WhileStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
		EagleTransformableStatement
{
	public @S(10) Go_Keyword FOR = new Go_Keyword("for");
	public @S(20) Go_Expression condition;
	public @S(30) Go_Statement statement;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean keepGoing = interpreter.getBoolValue(condition);
			if (!keepGoing) break;

			metric.iterate();

			result = interpreter.tryToInterpret(statement);
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

		_metrics.competedLoop(metric, false);
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> whileTrue = new ArrayList<AbstractStatement>();

		Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, statement.getWhich());
		for (AbstractStatement stmt : newStmts)
		{
			whileTrue.add(stmt);
		}

		AbstractStatement stmt = generator.newWhileStatement(cond, whileTrue, this);
		return stmt;
	}
}