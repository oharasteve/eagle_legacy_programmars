// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Functions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.SubstringECEnum;
import com.eagle.generate.EagleGenerator.SubstringSCEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rexx_SubstrFunction extends PrimaryOperator implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rexx_Keyword SUBSTR = new Rexx_Keyword("SUBSTR");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Rexx_Expression expr;
	public @S(40) PunctuationComma comma1;
	public @S(50) Rexx_Expression scExpr;
	public @S(60) PunctuationComma comma2;
	public @S(70) Rexx_Expression ncExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(scExpr) - 1;
		int nc = interpreter.getIntValue(ncExpr);
		int len = str.length();
		if (sc + nc > len) nc = len - sc;	// Don't go past the end of the string
		interpreter.pushStr(str.substring(sc, sc + nc));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		AbstractExpression sc = transformer.transformExpression(generator, scExpr);
		AbstractExpression nc = transformer.transformExpression(generator, ncExpr);
		return generator.newSubstringFunction(theExpr, sc, SubstringSCEnum.FIRST_CHAR_IS_ONE,
				SubstringECEnum.GIVEN_NC, nc, false, this);
	}
}
