// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2025

package com.eagle.programmar.SQL.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
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

public class SQL_SubstringFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) SQL_Keyword SUBSTRING = new SQL_Keyword("SUBSTRING");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SQL_Expression expr;
	public @S(40) PunctuationComma comma1;
	public @S(50) SQL_Expression scExpr;
	public @S(60) PunctuationComma comma2;
	public @S(70) SQL_Expression ncExpr;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String strArg = interpreter.getStrValue(expr);
		int sc = interpreter.getIntValue(scExpr) - 1;		// In SQL, first char is 1
		int nc = interpreter.getIntValue(ncExpr);
		if (sc + nc > strArg.length()) nc = strArg.length() - sc;
		interpreter.pushStr(strArg.substring(sc, sc + nc));
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		AbstractExpression sc = transformer.transformExpression(generator, scExpr);
		AbstractExpression nc = null;
		SubstringECEnum given = SubstringECEnum.GIVEN_NEITHER;
		if (ncExpr != null && ncExpr.isPresent())
		{
			nc = transformer.transformExpression(generator, ncExpr);
			given = SubstringECEnum.GIVEN_NC;
		}
		return generator.newSubstringFunction(theExpr, sc, SubstringSCEnum.FIRST_CHAR_IS_ONE,
				given, nc, true, this);
	}
}
