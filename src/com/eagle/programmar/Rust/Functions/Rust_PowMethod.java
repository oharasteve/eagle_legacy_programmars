// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
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
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_PowMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_KeywordChoice POW = new Rust_KeywordChoice("pow", "powf");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Rust_Expression power;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int base = interpreter.getIntValue(left);
		int pow = interpreter.getIntValue(power);
		interpreter.pushDouble(Math.pow(base, pow));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression baseExpr = transformer.transformExpression(generator, left);
		AbstractExpression powerExpr = transformer.transformExpression(generator, power);
		return generator.newExponentExpression(baseExpr, powerExpr, this);
	}

	public static Rust_Expression generatePower(Rust_Expression baseExpr, Rust_Expression powerExpr, AbstractToken source)
	{
		Rust_PowMethod pow = new Rust_PowMethod();
		pow.left = baseExpr;
		pow.dot = new PunctuationPeriod();
		pow.leftParen = new PunctuationLeftParen();
		pow.power = powerExpr;
		pow.rightParen = new PunctuationRightParen();

		pow.setTransformationSource(source);
		return Rust_Generator.wrapExpression(pow);
	}
}
