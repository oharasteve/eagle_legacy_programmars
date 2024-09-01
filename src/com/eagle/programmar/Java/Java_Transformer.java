package com.eagle.programmar.Java;

import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformer;

public class Java_Transformer extends EagleTransformer
{
	@Override
	public AbstractExpression transformExpression(EagleGenerator generator, AbstractExpression expression)
	{
		Java_Expression expr = (Java_Expression) expression;
		return super.transformExpressionToken(generator, expr.getWhich());
	}
}
