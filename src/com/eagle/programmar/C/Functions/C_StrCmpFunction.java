// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_StrCmpFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) C_KeywordChoice STRCMP = new C_KeywordChoice("strcmp", "stricmp", "strcasecmp");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) C_Expression str1;
	public @S(40) PunctuationComma comma;
	public @S(50) C_Expression str2;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String left = interpreter.getStrValue(str1);
		String right = interpreter.getStrValue(str2);
		switch (STRCMP.toString())
		{
		case "strcmp":
			interpreter.pushInt(left.compareTo(right));
			return;
		case "strcasecmp":
		case "stricmp":
			interpreter.pushInt(left.compareToIgnoreCase(right));
			return;
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		Oper2Types types = new Oper2Types(EagleString.STRING, EagleString.STRING);
		AbstractExpression newStr1 = transformer.transformExpression(generator, str1);
		AbstractExpression newStr2 = transformer.transformExpression(generator, str2);
		return generator.newRelationalExpression(types, newStr1, RelationalEnum.EQUALS, newStr2, this);
	}
}
