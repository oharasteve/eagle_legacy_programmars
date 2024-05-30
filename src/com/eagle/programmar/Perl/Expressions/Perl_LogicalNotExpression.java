// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Perl_LogicalNotExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Perl_Punctuation logicalNotOperator = new Perl_Punctuation('~');
	public @S(20) Perl_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean value = interpreter.getBoolValue(expr);
		interpreter.pushBool(! value);
	}
}
