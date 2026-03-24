// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.PrimaryOperator;
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

public class AWK_SubstrFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) AWK_Keyword SUBSTR = new AWK_Keyword("substr");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Expression expr;
	public @S(40) PunctuationComma comma1;
	public @S(50) AWK_Expression scExpr;
	public @S(60) @OPT PunctuationComma comma2;
	public @S(70) @OPT AWK_Expression ncExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String strArg = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(scExpr) - 1;
		if (sc > strArg.length()) throw new RuntimeException("Error on substr for " + strArg);
		if (ncExpr != null && ncExpr.isPresent())
		{
			int nc = interpreter.getIntValue(ncExpr);
			if (sc + nc > strArg.length()) nc = strArg.length() - sc;
			interpreter.pushStr(strArg.substring(sc, sc + nc)); // AWK substr() starts with 1, not 0
		}
		else
		{
			interpreter.pushStr(strArg.substring(sc));
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		AbstractExpression sc = transformer.transformExpression(generator, scExpr);
		AbstractExpression nc = transformer.transformExpression(generator, ncExpr);
		return generator.newSubstringFunction(theExpr, sc, SubstringSCEnum.FIRST_CHAR_IS_ONE,
				SubstringECEnum.GIVEN_NC, nc, true, this);
	}
}
