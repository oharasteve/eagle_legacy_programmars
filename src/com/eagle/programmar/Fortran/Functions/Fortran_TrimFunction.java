// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Fortran_TrimFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Fortran_Keyword TRIM = new Fortran_Keyword("TRIM");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Fortran_Expression expr;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = interpreter.getStrValue(expr);
		interpreter.pushStr(str.stripTrailing());	// Only removes trailing spaces
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression theExpr = transformer.transformExpression(generator, expr);
		return generator.newTrimFunction(theExpr, this);
	}
}