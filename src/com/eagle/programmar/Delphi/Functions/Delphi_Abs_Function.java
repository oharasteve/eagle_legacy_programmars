// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 28, 2026

package com.eagle.programmar.Delphi.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Delphi_Abs_Function extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Delphi_Keyword ABS = new Delphi_Keyword("Abs");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Delphi_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		if (val instanceof EagleInteger)
		{
			int num = val.forceIntegerValue();
			interpreter.pushInt(Math.abs(num));
		}
		else
		{
			double dbl = val.forceDoubleValue();
			interpreter.pushDouble(Math.abs(dbl));
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		AbstractExpression one = generator.newNumberExpression("1", null);
		AbstractExpression two = generator.newNumberExpression("2", null);
		Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);

		AbstractExpression theExpr2 = generator.newParenthesizedExpression(theExpr, null);
		AbstractExpression rem = generator.newMultiplicativeExpression(
				theExpr2, MultiplicativeEnum.REMAINDER, two, null);
		AbstractExpression result = generator.newRelationalExpression(
				types, rem, RelationalEnum.EQUALS, one, null);
		return generator.newParenthesizedExpression(result, this);
	}
}
