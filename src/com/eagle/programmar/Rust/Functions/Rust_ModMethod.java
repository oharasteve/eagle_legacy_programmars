// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 21, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
import com.eagle.programmar.Rust.Expressions.Rust_MultiplicativeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
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
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_ModMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression numer = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword REM = new Rust_Keyword("rem_euclid");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Rust_Expression denom;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int x = interpreter.getIntValue(numer);
		int y = interpreter.getIntValue(denom);
		interpreter.pushInt(Math.floorMod(x, y));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression numExpr = transformer.transformExpression(generator, numer);
		AbstractExpression denomExpr = transformer.transformExpression(generator, denom);
		return generator.newMultiplicativeExpression(numExpr, MultiplicativeEnum.MODULUS, denomExpr, this);
	}

	public static Rust_Expression generateModFunc(Rust_Expression numer, Rust_Expression denom, AbstractToken source)
	{
		// ((a % b) + b) % b works better than ._rem_euclid
		Rust_Expression pct = Rust_MultiplicativeExpression.generateMultiplicative(numer,
				MultiplicativeEnum.REMAINDER, denom, null);
		Rust_Expression paren1 = Rust_ParenthesizedExpression.generateParentheses(pct, null);
		Rust_Expression add = Rust_AdditiveExpression.generateAdditive(null, paren1,
				AdditiveEnum.PLUS, denom, null);
		Rust_Expression paren2 = Rust_ParenthesizedExpression.generateParentheses(add, null);
		return Rust_MultiplicativeExpression.generateMultiplicative(paren2,
				MultiplicativeEnum.REMAINDER, denom, source);

//		All this fails for denom < 0 -- it always returns a value > 0 which is wrong
//		Rust_ModMethod mod = new Rust_ModMethod();
//		mod.dot = new PunctuationPeriod();
//		mod.leftParen = new PunctuationLeftParen();
//		mod.rightParen = new PunctuationRightParen();
//		Rust_Type typ = Rust_Type.newPrimitiveType("i32");
//		Rust_Expression left = Rust_AsExpression.generateAsExpr(numer, typ, null);
//		mod.numer = Rust_ParenthesizedExpression.generateParentheses(left, null);
//		mod.denom = denom;
//
//		mod.setTransformationSource(source);
//		return Rust_Generator.wrapExpression(mod);
	}
}
