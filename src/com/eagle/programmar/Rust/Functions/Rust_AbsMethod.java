// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 6, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_AbsMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression expr = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword ABS = new Rust_Keyword("abs");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double num = interpreter.getIntValue(expr);
		interpreter.pushDouble(Math.abs(num));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression numExpr = transformer.transformExpression(generator, expr);
		return generator.newAbsFunction(numExpr, this);
	}

	public static Rust_Expression generateAbsFunc(Rust_Expression numExpr, AbstractToken source)
	{
		Rust_AbsMethod abs = new Rust_AbsMethod();
		abs.expr = numExpr;
		abs.dot = new PunctuationPeriod();
		abs.leftParen = new PunctuationLeftParen();
		abs.rightParen = new PunctuationRightParen();

		abs.setTransformationSource(source);
		return Rust_Generator.wrapExpression(abs);
	}
}
