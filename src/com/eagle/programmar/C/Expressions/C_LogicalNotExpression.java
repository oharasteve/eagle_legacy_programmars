// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_LogicalNotExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) C_Punctuation notOperator = new C_Punctuation('!');
	public @S(20) C_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(!value);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newNotExpression(theExpr, this);
	}
}
