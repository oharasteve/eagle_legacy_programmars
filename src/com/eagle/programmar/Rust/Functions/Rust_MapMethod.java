// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 3, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Type;
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
import com.eagle.tokens.punctuation.PunctuationVerticalBar;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_MapMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword MAP = new Rust_Keyword("map");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE PunctuationVerticalBar bar1;
	public @S(60) @NOSPACE Rust_Keyword IDX1 = new Rust_Keyword("idx");
	public @S(70) @NOSPACE PunctuationVerticalBar bar2;
	public @S(80) Rust_Keyword IDX2 = new Rust_Keyword("idx");
	public @S(90) Rust_Keyword AS = new Rust_Keyword("as");
	public @S(100) Rust_Type type;
	public @S(110) @NOSPACE PunctuationRightParen rightParen;

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

	public static Rust_Expression generateMap(Rust_Expression expr, AbstractToken source)
	{
		Rust_MapMethod mapMeth = new Rust_MapMethod();
		mapMeth.left = expr;
		
		mapMeth.dot = new PunctuationPeriod();
		mapMeth.leftParen = new PunctuationLeftParen();
		mapMeth.bar1 = new PunctuationVerticalBar();
		mapMeth.bar2 = new PunctuationVerticalBar();
		mapMeth.type = Rust_Type.transformType(TypeEnum.INTEGER, null, null);
		mapMeth.rightParen = new PunctuationRightParen();

		return Rust_Generator.wrapExpression(mapMeth);
	}
}
