// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Delphi_Not_Operator extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Delphi_Keyword NOT = new Delphi_Keyword("Not");
	public @S(20) Delphi_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(!value);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newNotExpression(theExpr, this);
	}
}
