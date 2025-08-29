// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_ExpressionArray extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationAmpersand ampersand;
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) SeparatedList<Rust_Expression, PunctuationComma> values;
	public @S(40) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray vals = new EagleArray();
		for (int i = 0; i < values.getPrimaryCount(); i++)
		{
			Rust_Expression expr = values.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			vals.addValue(val);
		}

		interpreter.pushEagleValue(vals);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
		int nValues = values.getPrimaryCount();
		for (int i = 0; i < nValues; i++)
		{
			Rust_Expression expr = values.getPrimaryElement(i);
			AbstractExpression newExpr = transformer.transformExpression(generator, expr);
			exprs.add(newExpr);
		}
		return generator.newArrayExpression(exprs, this);
	}
}
