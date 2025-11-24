// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 18, 2022

package com.eagle.programmar.Powershell.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Powershell.Powershell_Element;
import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Powershell_WhileStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#841-the-while-statement") Powershell_Keyword WHILE = new Powershell_Keyword(
			"While");
	public @S(20) Powershell_Expression condition;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT Powershell_EndOfLine eoln;
	public @S(50) TokenList<Powershell_Element> statements;
	public @S(60) PunctuationRightBrace rightBrace;

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
			if (!interpreter.getBoolValue(condition))
			{
				break;
			}

			metric.iterate();

			for (Powershell_Element stmt : statements._elements)
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
		}

		_metrics.competedLoop(metric, false);
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> actions = new ArrayList<AbstractStatement>();
		for (Powershell_Element stmt1 : statements._elements)
		{
			ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator,
					stmt1.element.getWhich());
			for (AbstractStatement stmt2 : stmts)
			{
				actions.add(stmt2);
			}
		}
		return generator.newWhileStatement(cond, actions, this);
	}
}
