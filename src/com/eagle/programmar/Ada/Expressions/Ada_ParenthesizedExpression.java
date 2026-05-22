// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ada.Ada_Expression;
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
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Ada_ParenthesizedExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) SeparatedList<Ada_Expression, PunctuationComma> expressions;
	public @S(30) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int numArgs = expressions.getPrimaryCount();
		if (numArgs == 1)
		{
			Ada_Expression expr = expressions.first();
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.pushEagleValue(val);
		}
		else
		{
			EagleArray array = new EagleArray();
			for (int i = 0; i < numArgs; i++)
			{
				EagleValue val = interpreter.getEagleValue(expressions.getPrimaryElement(i));
				array.addValue(val);
			}
			interpreter.pushEagleValue(array);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		int numElts = expressions.getPrimaryCount();
		if (numElts == 1)
		{
			AbstractExpression theExpr = transformer.transformExpression(generator, expressions.first());
			return generator.newParenthesizedExpression(theExpr, this);
		}

		// Must be an array of values
		ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
		for (int i = 0; i < numElts; i++)
		{
			Ada_Expression expr = expressions.getPrimaryElement(i);
			AbstractExpression newExpr = transformer.transformExpression(generator, expr);
			exprs.add(newExpr);
		}
		return generator.newArrayExpression(exprs, this);
	}
}