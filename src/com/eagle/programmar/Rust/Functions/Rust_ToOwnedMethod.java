// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, April 22, 2026

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
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
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_ToOwnedMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) @NOSPACE Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword TOOWNED = new Rust_Keyword("to_owned");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(left);
		interpreter.pushStr(val);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, left);
		return theExpr;
	}

	public static Rust_Expression generateOwned(Rust_Expression expr, AbstractToken source)
	{
		Rust_ToOwnedMethod ownMeth = new Rust_ToOwnedMethod();
		ownMeth.left = expr;
		
		ownMeth.dot = new PunctuationPeriod();
		ownMeth.leftParen = new PunctuationLeftParen();
		ownMeth.rightParen = new PunctuationRightParen();
		return Rust_Generator.wrapExpression(ownMeth);
	}
}
