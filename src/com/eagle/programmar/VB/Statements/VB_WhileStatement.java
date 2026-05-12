// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2024

package com.eagle.programmar.VB.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.VB.VB_Element;
import com.eagle.programmar.VB.VB_Element.VB_Statement;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class VB_WhileStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("statements/while-end-while-statement") VB_Keyword WHILE1 = new VB_Keyword("While");
	public @S(20) VB_Expression condition;
	public @S(30) VB_EndOfLine eoln;
	public @S(40) TokenList<VB_Element> actions;
	public @S(50) VB_KeywordChoice WEND = new VB_KeywordChoice("Wend", "End");
	public @S(60) @OPT VB_Keyword WHILE2 = new VB_Keyword("While");

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, WHILE1);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			boolean cond = interpreter.getBoolValue(condition);
			if (!cond) break;

			metric.iterate();

			for (VB_Element stmt : actions._elements)
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
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression cond = transformer.transformExpression(generator, condition);
		ArrayList<AbstractStatement> whileTrue = new ArrayList<AbstractStatement>();

		for (VB_Element statement : actions._elements)
		{
			for (int i = 0; i < statement.baseStatements.getPrimaryCount(); i++)
			{
				VB_Statement baseStatement = statement.baseStatements.getPrimaryElement(i);
				for (AbstractStatement stmt : transformer.transformStatement(generator, baseStatement.getWhich()))
				{
					whileTrue.add(stmt);
				}
			}
		}

		AbstractStatement stmt = generator.newWhileStatement(cond, whileTrue, this);
		return stmt;
	}
}
