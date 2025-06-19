// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class TCL_NotExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) TCL_Punctuation bang = new TCL_Punctuation('!');
	public @S(20) TCL_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean val = interpreter.getBoolValue(expr);
		interpreter.pushBool(!val);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newNotExpression(theExpr, this);
	}
}
