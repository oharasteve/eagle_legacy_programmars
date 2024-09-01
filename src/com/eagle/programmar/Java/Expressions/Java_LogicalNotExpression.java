// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGeneratableExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_LogicalNotExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression, EagleGeneratableExpression
{
	public @S(10) Java_Punctuation notOperator = new Java_Punctuation('!');
	public @S(20) Java_Expression expr;

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
		return generator.newNotExpression(theExpr);
	}
	
	public static Java_LogicalNotExpression generateExpression(AbstractExpression theExpr)
	{
		Java_LogicalNotExpression expr = new Java_LogicalNotExpression();
		expr.expr = (Java_Expression) theExpr;
		return expr;
	}
}
