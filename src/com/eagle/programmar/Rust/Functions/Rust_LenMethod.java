// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Type;
import com.eagle.programmar.Rust.Expressions.Rust_AsExpression;
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

public class Rust_LenMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression left = new Rust_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Rust_Keyword LEN = new Rust_Keyword("len");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(left);
		interpreter.pushInt(str.length());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, left);
		return generator.newLengthFunction(theExpr, this);
	}

	public static Rust_Expression generateLengthUsize(Rust_Expression expr)
	{
		Rust_LenMethod lenMeth = new Rust_LenMethod();
		lenMeth.left = expr;
		lenMeth.dot = new PunctuationPeriod();
		lenMeth.leftParen = new PunctuationLeftParen();
		lenMeth.rightParen = new PunctuationRightParen();

		return Rust_Generator.wrapExpression(lenMeth);
	}
	
	public static Rust_Expression generateLengthI32(Rust_Expression expr, AbstractToken source)
	{
		return Rust_AsExpression.generateAsExpr(generateLengthUsize(expr),
				Rust_Type.newPrimitiveType("i32"), source);
	}
}
