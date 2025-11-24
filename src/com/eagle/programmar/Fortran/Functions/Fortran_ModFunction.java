// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Fortran_ModFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Fortran_Keyword MOD = new Fortran_Keyword("MOD");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Fortran_Expression numerExpr;
	public @S(40) PunctuationComma comma;
	public @S(50) Fortran_Expression denomExpr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int numer = interpreter.getIntValue(numerExpr);
		int denom = interpreter.getIntValue(denomExpr);
		interpreter.pushInt(numer % denom);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, numerExpr);
		AbstractExpression rightExpr = transformer.transformExpression(generator, denomExpr);
		AbstractExpression newExpr = generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.REMAINDER,
				rightExpr, this);
		return generator.newParenthesizedExpression(newExpr, this);
	}
}