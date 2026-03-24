// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
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
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Julia_StringFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Julia_Keyword STRING = new Julia_Keyword("string");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Julia_Expression, PunctuationComma> argList;
	public @S(40) PunctuationRightParen rightParen;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, STRING.getValue(), STRING);
		}
		ArrayList<String> argTypes = new ArrayList<String>();

		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < argList.getPrimaryCount(); i++)
		{
			Julia_Expression expr = argList.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			String piece = val.forceStringValue();
			argTypes.add(val.typeName());
			buff.append(piece);
		}
		interpreter.pushStr(buff.toString());
		_metrics.calledWith(argTypes);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression line = null;
		Oper2Types types = null;

		// Pick up metrics, if known
		ArrayList<String> metrics = transformer.findArgumentsMetric(STRING);
		if (metrics != null)
		{
			types = new Oper2Types();
		}

		int numPieces = argList.getPrimaryCount();
		for (int i = 0; i < numPieces; i++)
		{
			if (metrics != null)
			{
				types._type1 = EagleString.STRING;
				types._type2 = metrics.get(i);
			}

			Julia_Expression piece = argList.getPrimaryElement(i);
			if (i == 0)
			{
				line = transformer.transformExpression(generator, piece);
			}
			else
			{
				AbstractExpression next = transformer.transformExpression(generator, piece);
				line = generator.newAdditiveExpression(types, line, AdditiveEnum.PLUS, next, piece);
			}
		}

		return line;
	}
}
