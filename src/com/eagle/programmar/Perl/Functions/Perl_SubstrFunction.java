// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
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

public class Perl_SubstrFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Perl_Keyword SUBSTR = new Perl_Keyword("substr");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Perl_Expression expr;
	public @S(40) PunctuationComma comma1;
	public @S(50) Perl_Expression scExpr;
	public @S(60) PunctuationComma comma2;
	public @S(70) Perl_Expression ncExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(scExpr);
		int nc = interpreter.getIntValue(ncExpr);
		int len = str.length();
		if (sc + nc > len) nc = len - sc; // Don't go past the end of the string
		interpreter.pushStr(str.substring(sc, sc + nc));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		AbstractExpression sc = transformer.transformExpression(generator, scExpr);
		AbstractExpression nc = transformer.transformExpression(generator, ncExpr);
		SubstringECEnum given = SubstringECEnum.GIVEN_NC;
		return generator.newSubstringFunction(theExpr, sc, SubstringSCEnum.FIRST_CHAR_IS_ZERO,
				given, nc, true, this);
	}
}
