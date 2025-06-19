// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.VB.VB_Element;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.RelationalEnum;

public class VB_ForStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("statements/for-next-statement") VB_Keyword FOR = new VB_Keyword("for");
	public @S(20) VB_Identifier_Reference variable;
	public @S(30) PunctuationEquals equals;
	public @S(40) VB_Expression from;
	public @S(50) VB_Keyword TO = new VB_Keyword("to");
	public @S(60) VB_Expression to;
	public @S(70) @OPT VB_ForStep step;
	public @S(80) VB_EndOfLine eoln;
	public @S(90) TokenList<VB_Element> actions;
	public @S(100) VB_Keyword NEXT = new VB_Keyword("next");
	public @S(110) @OPT VB_Identifier_Reference var2;

	public static class VB_ForStep extends TokenSequence
	{
		public @S(10) VB_Keyword STEP = new VB_Keyword("step");
		public @S(20) VB_Expression step;
	}
	
	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		int current = interpreter.getIntValue(from);
		int stop = interpreter.getIntValue(to);
		int by = 1;
		
		if (step != null && step.isPresent())
		{
			by = interpreter.getIntValue(step.step);
		}
		
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		while (true)
		{
			if (by < 0)
			{
				if (current < stop) break;
			}
			else
			{
				if (current > stop) break;
			}

			metric.iterate();
			
			interpreter.setSymbol(variable, variable.getValue(), new EagleInteger(current));

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

			current += by;
		}

		_metrics.competedLoop(metric);
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression initExpr = transformer.transformExpression(generator, from);
		AbstractExpression termExpr = transformer.transformExpression(generator, to);
		AbstractExpression incrExpr = null;
		if (step != null && step.isPresent())
		{
			incrExpr = transformer.transformExpression(generator, step.step);
		}
		
		ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
		for (VB_Element statement : actions._elements)
		{
			ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator,
					statement.baseStatement.getWhich());
			if (stmts != null)
			{
				for (AbstractStatement stmt : stmts)
				{
					actionList.add(stmt);
				}
			}
		}
		
		AbstractVariable var = generator.newVariable(variable.getValue());
		AbstractStatement stmt = generator.newForRangeStatement(var, initExpr,
				RelationalEnum.LESS_EQUALS, termExpr, incrExpr, actionList, this);
		return stmt;
	}
}
