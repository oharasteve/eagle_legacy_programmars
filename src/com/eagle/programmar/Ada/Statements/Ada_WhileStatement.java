// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 11, 2025

package com.eagle.programmar.Ada.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Ada_WhileStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Ada_Keyword WHILE = new Ada_Keyword("while");
	public @S(20) Ada_Expression condition;
	public @S(30) Ada_Keyword LOOP = new Ada_Keyword("loop");
	public @S(40) TokenList<Ada_Statement> statements;
	public @S(50) Ada_Keyword END = new Ada_Keyword("end");
	public @S(60) Ada_Keyword LOOP2 = new Ada_Keyword("loop");
	public @S(70) PunctuationSemicolon semicolon;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, WHILE);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		while (true)
		{
			boolean keepGoing = interpreter.getBoolValue(condition);
			if (!keepGoing) break;

			metric.iterate();
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
		}

		_metrics.completedLoop(metric, false);
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> whileTrue = new ArrayList<AbstractStatement>();

		for (Ada_Statement statement : statements._elements)
		{
			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, statement.getWhich());
			for (AbstractStatement stmt : newStmts)
			{
				whileTrue.add(stmt);
			}
		}

		AbstractStatement stmt = generator.newWhileStatement(cond, whileTrue, this);
		return stmt;
	}
}
