// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleRange;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Statement;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Expressions.Rust_RangeExpression;
import com.eagle.programmar.Rust.Functions.Rust_RevMethod;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Rust_ForStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("expressions/loop-expr.html#iterator-loops") @NEWLINE Rust_Keyword FOR =
			new Rust_Keyword("for");
	public @S(20) Rust_Variable variable;
	public @S(30) Rust_Keyword IN = new Rust_Keyword("in");
	public @S(40) Rust_Expression values;
	public @S(50) Rust_Statement statement;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleRange range = interpreter.getRangeValue(values);
		int start = range._lowValue;
		int stop = range._highValue;
		int step = range._step;

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		int i = start;
		boolean backwards = false;
		if (step < 0)
		{
			// Careful!
			// 1..4 does 1, 2, 3
			// (1..4).rev() does 3, 2, 1
			backwards = true;
			i = stop + step; // Careful!
		}

		while (true)
		{
			if (backwards && i < start) break;
			if (!backwards && i >= stop) break;

			metric.iterate();
			interpreter.setSymbol(variable, variable.var.toString(), new EagleInteger(i));

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

			i += step; // Might be negative
		}

		_metrics.competedLoop(metric, backwards);
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractToken which = values.getWhich();
		Rust_RangeExpression range = null;
		AbstractExpression initExpr = null;
		AbstractExpression termExpr = null;
		AbstractExpression incrExpr = null;
		RelationalEnum relOp = RelationalEnum.LESS_THAN;
		if (which instanceof Rust_RangeExpression)
		{
			range = (Rust_RangeExpression) which;
			initExpr = transformer.transformExpression(generator, range.lowExpression);
			termExpr = transformer.transformExpression(generator, range.highExpression);
		}
		if (which instanceof Rust_RevMethod)
		{
			Rust_RevMethod reversed = (Rust_RevMethod) which;
			if (reversed.left.getWhich() instanceof Rust_ParenthesizedExpression)
			{
				Rust_ParenthesizedExpression parens = (Rust_ParenthesizedExpression) reversed.left.getWhich();
				if (parens.expressions.first().getWhich() instanceof Rust_RangeExpression)
				{
					range = (Rust_RangeExpression) parens.expressions.first().getWhich();
					initExpr = transformer.transformExpression(generator, range.highExpression);
					AbstractExpression oneExpr = generator.newNumberExpression("1", null);
					initExpr = generator.newAdditiveExpression(null, initExpr, AdditiveEnum.MINUS, oneExpr, null);
					termExpr = transformer.transformExpression(generator, range.lowExpression);
					incrExpr = generator.newNumberExpression("-1", null);
					relOp = RelationalEnum.GREATER_EQUALS;
				}
			}
		}
		if (range == null)
		{
			throw new RuntimeException("FOR statement requires a Range of values, not " + which);
		}

		ArrayList<AbstractStatement> newStmts = Rust_Block_Statement.collectStatements(transformer, generator,
				statement);
		ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
		if (newStmts != null)
		{
			for (AbstractStatement stmt : newStmts)
			{
				actionList.add(stmt);
			}
		}

		AbstractVariable var = generator.newVariable(variable.var.getValue());
		return generator.newForRangeStatement(var, TypeEnum.INTEGER, initExpr,
				relOp, termExpr, incrExpr, actionList, this);
	}
}
