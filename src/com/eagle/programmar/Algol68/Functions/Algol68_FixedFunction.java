// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 19, 2026

package com.eagle.programmar.Algol68.Functions;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.programmar.Algol68.Terminals.Algol68_Number;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Algol68_FixedFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Algol68_Keyword FIXED = new Algol68_Keyword("FIXED");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Algol68_Expression expr;
	public @S(40) PunctuationComma comma1;
	public @S(50) Algol68_Number width;
	public @S(60) PunctuationComma comma2;
	public @S(70) Algol68_Number decimals;
	public @S(80) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double value = interpreter.getDoubleValue(expr);
		String fmt = "%" + width + "." + decimals + "f";
		interpreter.pushStr(String.format(fmt, Double.valueOf(value)));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression valExpr = transformer.transformExpression(generator, expr);
		int wid = Integer.parseInt(width.getValue());
		int dec = Integer.parseInt(decimals.getValue());
		// Algol68 can have a negative width to prevent a leading plus sign (+)
		return generator.newFormatDecimal2(valExpr, Math.abs(wid), dec, this);
	}
}
