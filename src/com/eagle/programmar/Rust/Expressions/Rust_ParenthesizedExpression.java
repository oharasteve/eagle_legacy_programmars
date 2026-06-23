// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_ParenthesizedExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @NOSPACE SeparatedList<Rust_Expression, PunctuationComma> expressions;
	public @S(30) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expressions.first());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expressions.first());
		return generator.newParenthesizedExpression(theExpr, this);
	}

	public static Rust_Expression generateParentheses(Rust_Expression theExpr, AbstractToken source)
	{
		if (theExpr.getWhich() instanceof Rust_ParenthesizedExpression)
		{
			// Never need ((x+y))
			return theExpr;
		}
		Rust_ParenthesizedExpression par = new Rust_ParenthesizedExpression();
		par.leftParen = new PunctuationLeftParen();
		par.expressions = new SeparatedList<Rust_Expression, PunctuationComma>();
		par.expressions.addPrimaryElement(theExpr);
		par.rightParen = new PunctuationRightParen();
		par.setTransformationSource(source);
		return Rust_Generator.wrapExpression(par);
	}
}
