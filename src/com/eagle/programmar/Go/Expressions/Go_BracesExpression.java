// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Go_BracesExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) SeparatedList<Go_Expression, PunctuationComma> expressions;
	public @S(30) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		for (int i = 0; i < expressions.getPrimaryCount(); i++)
		{
			EagleValue val = interpreter.getEagleValue(expressions.getPrimaryElement(i));
			array.addValue(val);
		}
		interpreter.pushEagleValue(array);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
		int numElts = expressions.getPrimaryCount();
		for (int i = 0; i < numElts; i++)
		{
			Go_Expression expr = expressions.getPrimaryElement(i);
			AbstractExpression newExpr = transformer.transformExpression(generator, expr);
			exprs.add(newExpr);
		}
		return generator.newArrayExpression(exprs, this);
	}
}
