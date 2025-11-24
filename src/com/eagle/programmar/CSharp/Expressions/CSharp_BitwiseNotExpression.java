// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_BitwiseNotExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Punctuation operator = new CSharp_Punctuation('~');
	public @S(20) CSharp_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int value = interpreter.getIntValue(expr);
		interpreter.pushInt(~value);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression value = transformer.transformExpression(generator, expr);
		return generator.newBitwiseNotExpression(value, this);
	}

	public CSharp_Expression generateBitwiseNot(CSharp_Expression value, AbstractToken source)
	{
		this.expr = value;
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
