// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 22, 2024

package com.eagle.programmar.Rust.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Expressions.Rust_SubscriptExpression;
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
import com.eagle.transform.EagleGenerator.SubstringECEnum;
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
	public @S(60) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String text = interpreter.getStrValue(left);
		String patt = interpreter.getStrValue(arg);
		interpreter.pushBool(text.startsWith(patt));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, left);
		AbstractExpression thePattern = transformer.transformExpression(generator, arg);
		AbstractExpression theSC = null;
		return generator.newStartsWithFunction(theExpr, thePattern, theSC,
				SubstringSCEnum.FIRST_CHAR_IS_ZERO, this);
	}

	public static Rust_Expression generateStartsWith(Rust_Expression expr, Rust_Expression patt,
			Rust_Expression sc, SubstringSCEnum whichSC, AbstractToken source)
	{
		Rust_StartsWithMethod startsExpr = new Rust_StartsWithMethod();
		startsExpr.left = expr;
		startsExpr.dot = new PunctuationPeriod();
		startsExpr.leftParen = new PunctuationLeftParen();
		startsExpr.arg = patt;
		if (sc != null)
		{
			// Rust does not support str.StartsWith("patt",sc)
			// Have to use Substring instead
			startsExpr.left = Rust_SubscriptExpression.generateSubscriptExpression(
					expr, sc, whichSC, SubstringECEnum.TO_END, null, false, source);
		}
		startsExpr.rightParen = new PunctuationRightParen();

		startsExpr.setTransformationSource(source);
		return Rust_Generator.wrapExpression(startsExpr);
	}
}
