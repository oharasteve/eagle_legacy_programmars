// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 29, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Bash_LogicalNotExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Bash_Punctuation logicalNotOperator = new Bash_Punctuation('!');
	public @S(20) Bash_Expression expr;

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
		return generator.newLogicalNotExpression(theExpr, this);
	}
}