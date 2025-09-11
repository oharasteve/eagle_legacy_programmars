// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2024

package com.eagle.programmar.Ada.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Ada_SliceFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Ada_Keyword SLICE = new Ada_Keyword("Slice");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Ada_Expression arg;
	public @S(40) PunctuationComma comma1;
	public @S(50) Ada_Expression scExpr;
	public @S(60) PunctuationComma comma2;
	public @S(70) Ada_Expression ecExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(arg);
		int sc = interpreter.getIntValue(scExpr) - 1;
		int ec = interpreter.getIntValue(ecExpr);
		interpreter.pushStr(str.substring(sc, ec));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, arg);
		AbstractExpression sc = transformer.transformExpression(generator, scExpr);
		AbstractExpression ec = transformer.transformExpression(generator, ecExpr);
		return generator.newSubstringFunction(theExpr, sc, SubstringSCEnum.FIRST_CHAR_IS_ONE,
				SubstringECEnum.GIVEN_EC, ec, true, this);
	}
}
