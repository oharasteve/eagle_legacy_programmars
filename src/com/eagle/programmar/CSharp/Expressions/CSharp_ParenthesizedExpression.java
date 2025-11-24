// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_ParenthesizedExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @NOSPACE CSharp_Expression expression;
	public @S(30) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expression);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expression);
		return generator.newParenthesizedExpression(theExpr, this);
	}

	public CSharp_Expression generateParentheses(CSharp_Expression theExpr,
			AbstractToken source)
	{
		this.leftParen = new PunctuationLeftParen();
		this.expression = theExpr;
		this.rightParen = new PunctuationRightParen();
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
