package com.eagle.programmar.CSharp;

import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformer;

public class CSharp_Transformer extends EagleTransformer
{
	@Override
	public AbstractExpression transformExpression(EagleGenerator generator, AbstractExpression expression)
	{
		CSharp_Expression expr = (CSharp_Expression) expression;
		return super.transformExpressionToken(generator, expr.getWhich());
	}
}
