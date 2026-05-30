// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2025

package com.eagle.programmar.Powershell.Methods;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubstringECEnum;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Powershell_SubStringMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Powershell_Expression strExpr = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Powershell_Keyword SUBSTRING = new Powershell_Keyword("substring");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Powershell_Expression scExpr;
	public @S(60) @OPT Powershell_SubStringNC ncGiven;
	public @S(70) PunctuationRightParen rightParen;

	public static class Powershell_SubStringNC extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Powershell_Expression ncExpr;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(strExpr);
		int sc = interpreter.getIntValue(scExpr);
		if (ncGiven != null && ncGiven.isPresent())
		{
			int nc = interpreter.getIntValue(ncGiven.ncExpr);
			interpreter.pushStr(str.substring(sc, sc + nc));
		}
		else
		{
			interpreter.pushStr(str.substring(sc));
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, strExpr);
		AbstractExpression theSC = transformer.transformExpression(generator, scExpr);
		AbstractExpression theNC = null;
		SubstringECEnum whichEC = SubstringECEnum.TO_END;
		if (ncGiven != null && ncGiven.isPresent())
		{
			theNC = transformer.transformExpression(generator, ncGiven.ncExpr);
			whichEC = SubstringECEnum.GIVEN_NC;
		}

		return generator.newSubstringFunction(theExpr, theSC,
				SubstringSCEnum.FIRST_CHAR_IS_ZERO, whichEC, theNC, false, this);
	}
}
