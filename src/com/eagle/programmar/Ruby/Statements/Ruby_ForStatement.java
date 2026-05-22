// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Expressions.Ruby_RangeExpression;
import com.eagle.programmar.Ruby.Functions.Ruby_DownToMethod;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Ruby_ForStatement extends TokenSequence
		implements AbstractStatement, EagleRunnableWithResult, EagleTransformableStatement
{
	public @S(10) @DOC("control_expressions_rdoc.html#label-for+Loop") Ruby_Keyword FOR = new Ruby_Keyword("for");
	public @S(20) Ruby_Variable var;
	public @S(30) Ruby_Keyword IN = new Ruby_Keyword("in");
	public @S(40) Ruby_Expression values;
	public @S(50) Ruby_EOLN eoln1;
	public @S(60) TokenList<Ruby_Statement> statements;
	public @S(70) Ruby_Keyword END = new Ruby_Keyword("end");
	public @S(80) Ruby_EOLN eoln2;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		AbstractToken which = values.getWhich();
		boolean backwards = false;
		int start = 0;
		int stop = 0;
		if (which instanceof Ruby_RangeExpression)
		{
			Ruby_RangeExpression range = (Ruby_RangeExpression) which;
			start = interpreter.getIntValue(range.left);
			stop = interpreter.getIntValue(range.right);
		}
		else if (which instanceof Ruby_DownToMethod)
		{
			// Could look like this: (3).downto(1)
			Ruby_DownToMethod reversed = (Ruby_DownToMethod) which;
			start = interpreter.getIntValue(reversed.init);
			stop = interpreter.getIntValue(reversed.stop);
			backwards = true;
		}
		else
		{
			throw new RuntimeException("FOR statement requires a Range of values");
		}

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;

		int i = start;
		while (true)
		{
			if (!backwards && i > stop) break;
			if (backwards && i < stop) break;

			metric.iterate();
			interpreter.setSymbol(var, var.vars.first().getValue(), new EagleInteger(i));

			for (Ruby_Statement statement : statements._elements)
			{
				result = interpreter.tryToInterpret(statement);
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

			if (backwards)
				i--;
			else
				i++;
		}

		_metrics.completedLoop(metric, backwards);
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = values.getWhich();
		Ruby_RangeExpression range = null;
		AbstractExpression initExpr = null;
		AbstractExpression termExpr = null;
		AbstractExpression incrExpr = null;
		RelationalEnum relOp = RelationalEnum.LESS_EQUALS;
		if (which instanceof Ruby_RangeExpression)
		{
			range = (Ruby_RangeExpression) which;
			initExpr = transformer.transformExpression(generator, range.left);
			termExpr = transformer.transformExpression(generator, range.right);
		}
		else if (which instanceof Ruby_DownToMethod)
		{
			Ruby_DownToMethod reversed = (Ruby_DownToMethod) which;
			initExpr = transformer.transformExpression(generator, reversed.init);
			termExpr = transformer.transformExpression(generator, reversed.stop);
			incrExpr = generator.newNumberExpression("-1", null);
			relOp = RelationalEnum.GREATER_EQUALS;
		}
		else
		{
			throw new RuntimeException("FOR statement requires a Range of values");
		}

		ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
		for (Ruby_Statement statement : statements._elements)
		{
			ArrayList<AbstractStatement> newStmts = transformer.transformStatement(
					generator, statement.getWhich());
			if (newStmts != null)
			{
				for (AbstractStatement stmt : newStmts)
				{
					actionList.add(stmt);
				}
			}
		}

		AbstractVariable varName = generator.newVariable(var.vars.first().getValue());
		return generator.newForRangeStatement(varName, null, initExpr,
				relOp, termExpr, incrExpr, actionList, this);
	}
}
