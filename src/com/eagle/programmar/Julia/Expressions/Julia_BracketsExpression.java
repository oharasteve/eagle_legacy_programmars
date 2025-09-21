// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Julia_BracketsExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) SeparatedList<Julia_Expression, PunctuationComma> expressions;
	public @S(30) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray vals = new EagleArray();
		for (int i = 0; i < expressions.getPrimaryCount(); i++)
		{
			EagleValue val = interpreter.getEagleValue(expressions.getPrimaryElement(i));
			vals.addValue(val);
			// if (interpreter._TRACE) System.err.println("*** array += " + val.toString());
		}

		interpreter.pushEagleValue(vals);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		int numElts = expressions.getPrimaryCount();
		ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
		for (int i = 0; i < numElts; i++)
		{
			Julia_Expression expr = expressions.getPrimaryElement(i);
			AbstractExpression newExpr = transformer.transformExpression(generator, expr);
			exprs.add(newExpr);
		}
		return generator.newArrayExpression(exprs, this);
	}
}
