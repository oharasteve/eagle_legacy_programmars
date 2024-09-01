package com.eagle.programmar.VB;

import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformer;

public class VB_Transformer extends EagleTransformer
{
	@Override
	public AbstractExpression transformExpression(EagleGenerator generator, AbstractExpression expression)
	{
		VB_Expression expr = (VB_Expression) expression;
		return super.transformExpressionToken(generator, expr.getWhich());
	}
}
