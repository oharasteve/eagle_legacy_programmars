// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 3, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_UnwrapMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_KeywordChoice UNWRAP = new Rust_KeywordChoice("unwrap_or");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Rust_Expression noneExpr;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(left);
		interpreter.pushEagleValue(val);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		return transformer.transformExpression(generator, left);
	}

	public static Rust_Expression generateUnwrap(Rust_Expression expr, Rust_Expression none, AbstractToken source)
	{
		Rust_UnwrapMethod unwrapMeth = new Rust_UnwrapMethod();
		unwrapMeth.left = expr;
		
		unwrapMeth.dot = new PunctuationPeriod();
		unwrapMeth.leftParen = new PunctuationLeftParen();
		unwrapMeth.noneExpr = none;
		unwrapMeth.rightParen = new PunctuationRightParen();

		return Rust_Generator.wrapExpression(unwrapMeth);
	}
}
