// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 17, 2025

package com.eagle.programmar.TCL.Functions;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class TCL_BracketStringCat extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) TCL_Keyword STRING = new TCL_Keyword("string");
	public @S(30) TCL_Keyword CAT = new TCL_Keyword("cat");
	public @S(40) TokenList<TCL_Expression> strings;
	public @S(50) PunctuationRightBracket rightBracket;
	
	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, CAT.getValue(), CAT);
		}
		ArrayList<String> argTypes = new ArrayList<String>();

		StringBuffer sb = new StringBuffer();
		for (TCL_Expression expr : strings._elements)
		{
			EagleValue val = interpreter.getEagleValue(expr);
			String str = val.forceStringValue();
			argTypes.add(val.typeName());
			sb.append(str);
		}
		_metrics.calledWith(argTypes);
		interpreter.pushStr(sb.toString());
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		Oper2Types types = null;

		// Pick up metrics, if known
		ArrayList<String> metrics = transformer.findArgumentsMetric(CAT);
		if (metrics != null)
		{
			types = new Oper2Types();
		}

		AbstractExpression result = null;
		int i = 0;
		for (TCL_Expression expr : strings._elements)
		{
			AbstractExpression piece = transformer.transformExpression(generator, expr);
			if (result == null)
			{
				result = piece;
			}
			else
			{
				if (metrics != null)
				{
					types._type1 = metrics.get(i-1);
					types._type2 = metrics.get(i);
				}

				result = generator.newAppendExpression(types, result, piece, expr);
			}
			i++;
		}
		return result;
	}
}
