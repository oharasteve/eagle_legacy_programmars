// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Fortran_BracketExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @OPT SeparatedList<Fortran_Expression, PunctuationComma> expressions;
	public @S(30) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		for (int i = 0; i < expressions.getPrimaryCount(); i++)
		{
			Fortran_Expression expr = expressions.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			array.addValue(val);
		}
		interpreter.pushEagleValue(array);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (expressions != null && expressions.isPresent())
		{
			ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
			for (int i = 0; i < expressions.getPrimaryCount(); i++)
			{
				Fortran_Expression expr = expressions.getPrimaryElement(i);
				exprs.add(transformer.transformExpression(generator, expr));
			}
			return generator.newArrayExpression(exprs, this);
		}
		return null;
	}
}
