// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Perl_BitwiseNotExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Perl_Punctuation logicalNotOperator = new Perl_Punctuation('~');
	public @S(20) Perl_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int value = interpreter.getIntValue(expr);
		interpreter.pushInt(~value);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newBitwiseNotExpression(theExpr, this);
	}
}
