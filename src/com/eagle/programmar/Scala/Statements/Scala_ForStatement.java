// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Statement;
import com.eagle.programmar.Scala.Scala_Variable;
import com.eagle.programmar.Scala.Expressions.Scala_ParenthesizedExpression;
import com.eagle.programmar.Scala.Expressions.Scala_RangeExpression;
import com.eagle.programmar.Scala.Functions.Scala_ReverseMethod;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.programmar.Scala.Terminals.Scala_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Scala_ForStatement extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
{
	public @S(10) @DOC("taste-control-structures.html#for-loops-and-expressions") Scala_Keyword FOR = new Scala_Keyword(
			"for");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Scala_Variable variable;
	public @S(40) Scala_Punctuation arrow = new Scala_Punctuation("<-");
	public @S(50) Scala_Expression values;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) Scala_Statement statement;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		AbstractToken which = values.getWhich();
		Scala_RangeExpression range = null;
		boolean backwards = false;
		int start = 0;
		int stop = 0;
		if (which instanceof Scala_RangeExpression)
		{
			range = (Scala_RangeExpression) which;
			start = interpreter.getIntValue(range.left);
			stop = interpreter.getIntValue(range.right);
		}
		if (which instanceof Scala_ReverseMethod)
		{
			Scala_ReverseMethod reversed = (Scala_ReverseMethod) which;
			if (reversed.leftExpr.getWhich() instanceof Scala_ParenthesizedExpression)
			{
				Scala_ParenthesizedExpression parens = (Scala_ParenthesizedExpression) reversed.leftExpr.getWhich();
				if (parens.expression.getWhich() instanceof Scala_RangeExpression)
				{
					range = (Scala_RangeExpression) parens.expression.getWhich();
					backwards = true;
					start = interpreter.getIntValue(range.right);
					stop = interpreter.getIntValue(range.left);
				}
			}
		}
		if (range == null)
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
			interpreter.setSymbol(variable, variable.vars.first().getValue(), new EagleInteger(i));

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

			if (backwards)
				i--;
			else
				i++;
		}

		_metrics.completedLoop(metric, backwards);
		return result;
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = values.getWhich();
		Scala_RangeExpression range = null;
		AbstractExpression initExpr = null;
		AbstractExpression termExpr = null;
		AbstractExpression incrExpr = null;
		RelationalEnum relOp = RelationalEnum.LESS_EQUALS;
		if (which instanceof Scala_RangeExpression)
		{
			range = (Scala_RangeExpression) which;
			initExpr = transformer.transformExpression(generator, range.left);
			termExpr = transformer.transformExpression(generator, range.right);
		}
		if (which instanceof Scala_ReverseMethod)
		{
			Scala_ReverseMethod reversed = (Scala_ReverseMethod) which;
			if (reversed.leftExpr.getWhich() instanceof Scala_ParenthesizedExpression)
			{
				Scala_ParenthesizedExpression parens = (Scala_ParenthesizedExpression) reversed.leftExpr.getWhich();
				if (parens.expression.getWhich() instanceof Scala_RangeExpression)
				{
					range = (Scala_RangeExpression) parens.expression.getWhich();
					initExpr = transformer.transformExpression(generator, range.right);
					termExpr = transformer.transformExpression(generator, range.left);
					incrExpr = generator.newNumberExpression("-1", null);
					relOp = RelationalEnum.GREATER_EQUALS;
				}
			}
		}
		if (range == null)
		{
			throw new RuntimeException("FOR statement requires a Range of values");
		}

		ArrayList<AbstractStatement> newStmts = Scala_BlockStatement.collectStatements(transformer, generator,
				statement);
		ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
		if (newStmts != null)
		{
			for (AbstractStatement stmt : newStmts)
			{
				actionList.add(stmt);
			}
		}

		AbstractVariable var = generator.newVariable(variable.vars.first().getValue());
		return generator.newForRangeStatement(var, TypeEnum.INTEGER, initExpr,
				relOp, termExpr, incrExpr, actionList, this);
	}
}
