// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Julia_SubStringFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Julia_Keyword SUBSTRING = new Julia_Keyword("SubString");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Julia_Expression strExpr;
	public @S(40) PunctuationComma comma;
	public @S(50) Julia_Expression scExpr;
	public @S(60) @OPT Julia_SubStringEC ecGiven;
	public @S(70) PunctuationRightParen rightParen;

	public static class Julia_SubStringEC extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Julia_Expression ecExpr;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(strExpr);
		int sc = interpreter.getIntValue(scExpr);
		if (ecGiven != null && ecGiven.isPresent())
		{
			int ec = interpreter.getIntValue(ecGiven.ecExpr);
			interpreter.pushStr(str.substring(sc - 1, ec));
		}
		else
		{
			interpreter.pushStr(str.substring(sc - 1));
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, strExpr);
		AbstractExpression theSC = transformer.transformExpression(generator, scExpr);
		AbstractExpression theEC = null;
		if (ecGiven != null && ecGiven.isPresent())
		{
			theEC = transformer.transformExpression(generator, ecGiven.ecExpr);
		}

		return generator.newSubstringFunction(theExpr, theSC,
				SubstringSCEnum.FIRST_CHAR_IS_ONE, SubstringECEnum.GIVEN_EC, theEC, true, this);
	}
}
