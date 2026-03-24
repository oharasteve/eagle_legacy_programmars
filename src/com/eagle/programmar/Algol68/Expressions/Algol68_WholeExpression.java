// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 14, 2025

package com.eagle.programmar.Algol68.Expressions;

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
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Algol68_WholeExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Algol68_Keyword WHOLE = new Algol68_Keyword("WHOLE");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Algol68_Expression expr;
	public @S(40) PunctuationComma comma;
	public @S(50) Algol68_Number size;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!size.getValue().equals("0"))
		{
			throw new RuntimeException("Only supporting field width of 0 for now");
			// If size is positive, the number will be right-justified and padded with
			// spaces to the left. If the number is too large for the specified width, it
			// will be represented by asterisks (*) to indicate an overflow.
			// If size is negative, the absolute value is used for the width, and a sign
			// will be printed even if the number is positive (e.g., +99).
		}
		int value = interpreter.getIntValue(expr);
		interpreter.pushStr(Integer.toString(value));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (!size.getValue().equals("0"))
		{
			throw new RuntimeException("Only supporting field width of 0 for now");
		}
		return transformer.transformExpression(generator, expr);
	}
}
