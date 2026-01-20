// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_StartsWithMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword STARTSWITH = new Rust_Keyword("starts_with");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Rust_Expression arg;
	public @S(60) @OPT @NOSPACE PunctuationComma comma;
	public @S(70) @OPT Rust_Expression scExpr;
	public @S(80) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String text = interpreter.getStrValue(left);
		String patt = interpreter.getStrValue(arg);
		if (scExpr != null && scExpr.isPresent())
		{
			int sc = interpreter.getIntValue(scExpr);
			interpreter.pushBool(text.startsWith(patt, sc));
		}
		else
		{
			interpreter.pushBool(text.startsWith(patt));
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, left);
		AbstractExpression thePattern = transformer.transformExpression(generator, arg);
		AbstractExpression theSC = null;
		if (scExpr != null && scExpr.isPresent())
		{
			theSC = transformer.transformExpression(generator, scExpr);
		}

		return generator.newStartsWithFunction(theExpr, thePattern, theSC,
				SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
	}

	public Rust_Expression generateStartsWith(Rust_Expression expr, Rust_Expression patt,
			Rust_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		this.left = expr;
		this.dot = new PunctuationPeriod();
		this.leftParen = new PunctuationLeftParen();
		this.arg = patt;
		if (sc != null)
		{
			this.comma = new PunctuationComma();
			this.comma.setPresent(true);
			this.scExpr = sc;
			this.scExpr.setPresent(true);
		}
		this.rightParen = new PunctuationRightParen();

		this.setTransformationSource(source);
		return Rust_Generator.wrapExpression(this);
	}
}
