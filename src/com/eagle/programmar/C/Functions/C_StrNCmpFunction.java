// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_StrNCmpFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) C_Keyword STRNCMP = new C_Keyword("strncmp");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression str1;
	public @S(40) PunctuationComma comma1;
	public @S(50) C_Expression str2;
	public @S(60) PunctuationComma comma2;
	public @S(70) C_Expression ncExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String left = interpreter.getStrValue(str1);
		String right = interpreter.getStrValue(str2);
		int nc = interpreter.getIntValue(ncExpr);
		if (left.length() > nc) left = left.substring(0, nc);
		if (right.length() > nc) right = right.substring(0, nc);
		interpreter.pushInt(left.compareTo(right));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		Oper2Types types = new Oper2Types(EagleString.STRING, EagleString.STRING);
		AbstractExpression newNc = transformer.transformExpression(generator, ncExpr);
		AbstractExpression zero = generator.newNumberExpression("0", null);
		AbstractExpression newStr1 = transformer.transformExpression(generator, str1);
		AbstractExpression newStr2 = transformer.transformExpression(generator, str2);
		AbstractExpression substr1 = generator.newSubstringFunction(newStr1, zero,
				SubstringSCEnum.FIRST_CHAR_IS_ZERO, SubstringECEnum.GIVEN_NC, newNc, true, str1);
		AbstractExpression substr2 = generator.newSubstringFunction(newStr2, zero,
				SubstringSCEnum.FIRST_CHAR_IS_ZERO, SubstringECEnum.GIVEN_NC, newNc, true, str2);
		return generator.newRelationalExpression(types, substr1, RelationalEnum.EQUALS, substr2, this);
	}
}
