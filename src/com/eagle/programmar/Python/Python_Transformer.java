package com.eagle.programmar.Python;

import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformer;

public class Python_Transformer extends EagleTransformer
{
	@Override
	public AbstractExpression transformExpression(EagleGenerator generator, AbstractExpression expression)
	{
		Python_Expression expr = (Python_Expression) expression;
		return super.transformExpressionToken(generator, expr.getWhich());
	}
}
