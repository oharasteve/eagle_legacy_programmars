// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.ForLoopMetric;
import com.eagle.metrics.ForLoopMetrics;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Eaglish_For_Block extends TokenSequence
		implements EagleRunnableWithResult, AbstractStatement,
		EagleTransformableStatement
{
	public @S(10) Eaglish_Keyword FOR = new Eaglish_Keyword("FOR");
	public @S(20) Eaglish_Variable_Definition variable;
	public @S(30) PunctuationEquals equals;
	public @S(40) Eaglish_Expression startValue;
	public @S(50) Eaglish_KeywordChoice TO = new Eaglish_KeywordChoice("TO", "DOWN_TO");
	public @S(60) Eaglish_Expression stopValue;
	public @S(70) Eaglish_EndOfLine eoln1;

	public @S(80) @OPT TokenList<Eaglish_Statement> statements;

	public @S(90) Eaglish_Keyword END_FOR = new Eaglish_Keyword("END_FOR");
	public @S(100) Eaglish_EndOfLine eoln2;

	private @SKIP ForLoopMetrics _metrics = null;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		int start = interpreter.getIntValue(startValue);
		int stop = interpreter.getIntValue(stopValue);

		if (_metrics == null)
		{
			_metrics = new ForLoopMetrics(interpreter._metrics, FOR);
		}
		ForLoopMetric metric = new ForLoopMetric();

		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		String which = TO.getValue();
		boolean backwards = which.equals("DOWN_TO");

		int i = start;
		while (true)
		{
			if (!backwards && i > stop) break;
			if (backwards && i < stop) break;

			metric.iterate();
			interpreter.setSymbol(variable, variable.toString(), new EagleInteger(i));

			for (Eaglish_Statement stmt : statements._elements)
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
		AbstractExpression initExpr = transformer.transformExpression(generator, startValue);
		AbstractExpression termExpr = transformer.transformExpression(generator, stopValue);
		AbstractExpression incrExpr;
		RelationalEnum relOp;

		switch (TO.getValue())
		{
		case "TO":
			incrExpr = generator.newNumberExpression("1", TO);
			relOp = RelationalEnum.LESS_EQUALS;
			break;
		case "DOWN_TO":
			incrExpr = generator.newNumberExpression("-1", TO);
			relOp = RelationalEnum.GREATER_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected direction: " + TO.getValue());
		}

		ArrayList<AbstractStatement> actionList = new ArrayList<AbstractStatement>();
		for (Eaglish_Statement statement : statements._elements)
		{
			ArrayList<AbstractStatement> stmts = transformer.transformStatement(generator,
					statement.getWhich());
			if (stmts != null)
			{
				for (AbstractStatement stmt : stmts)
				{
					actionList.add(stmt);
				}
			}
		}

		AbstractVariable var = generator.newVariable(variable.getValue());
		AbstractStatement stmt = generator.newForRangeStatement(var, TypeEnum.VOID, initExpr,
				relOp, termExpr, incrExpr, actionList, this);
		return stmt;
	}
}
