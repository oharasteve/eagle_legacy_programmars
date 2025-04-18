// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.Expressions.Eagle_Generate_Parentheses;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_ParenthesizedExpression extends PrimaryOperator implements EagleRunnable,
		EagleTransformableExpression, Eagle_Generate_Parentheses<Java_Expression>
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @NOSPACE Java_Expression expression;
	public @S(30) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expression);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expression);
		return generator.newParenthesizedExpression(theExpr, this);
	}
	
	@Override
	public Java_Expression generateParentheses(Java_Expression theExpr, AbstractToken source)
	{
		this.leftParen = new PunctuationLeftParen();
		this.expression = theExpr;
		this.rightParen = new PunctuationRightParen();
		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
