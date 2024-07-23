// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Type;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_ClassCastExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Perl_Type ptype;
	public @S(30) PunctuationRightParen rightParen;
	public @S(40) Perl_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (ptype.getWhich() instanceof Perl_KeywordChoice)
		{
			Perl_KeywordChoice kw = (Perl_KeywordChoice) ptype.getWhich();
			if (kw.getValue().equals("int"))
			{
				double x = interpreter.getDoubleValue(expr);
				interpreter.pushInt((int) x);
			}
		}
	}
}
