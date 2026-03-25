// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2025

package com.eagle.programmar.SQL.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class SQL_ConcatFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) SQL_Keyword CONCAT = new SQL_Keyword("CONCAT");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<SQL_Expression, PunctuationComma> exprs;
	public @S(40) PunctuationRightParen rightParen;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, CONCAT.getValue(), CONCAT);
		}
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();

		StringBuffer result = new StringBuffer();
		for (int i = 0; i < exprs.getPrimaryCount(); i++)
		{
			SQL_Expression expr = exprs.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			String piece = val.forceStringValue();
			argTypes.add(val.getType());
			result.append(piece.replaceAll("\\\\n", "\n"));	// Replace \\n with a newline)
		}
		interpreter.pushStr(result.toString());
		_metrics.calledWith(argTypes);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression line = null;
		Oper2Types types = null;

		// Pick up metrics, if known
		ArrayList<TypeEnum> metrics = transformer.findArgumentsMetric(CONCAT);
		if (metrics != null)
		{
			types = new Oper2Types();
		}

		int numPieces = exprs.getPrimaryCount();
		for (int i = 0; i < numPieces; i++)
		{
			if (metrics != null)
			{
				types._type1 = TypeEnum.STRING;
				types._type2 = metrics.get(i);
			}

			SQL_Expression piece = exprs.getPrimaryElement(i);
			AbstractExpression next = transformer.transformExpression(generator, piece);
			if (i == 0)
			{
				line = next;
			}
			else
			{
				line = generator.newAdditiveExpression(types, line, AdditiveEnum.PLUS, next, piece);
			}
		}

		return line;
	}
}
