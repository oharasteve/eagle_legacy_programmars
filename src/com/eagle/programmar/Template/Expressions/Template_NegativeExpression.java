// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Template.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Template.Template_Expression;
import com.eagle.programmar.Template.Terminals.Template_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Template_NegativeExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Template_Punctuation negative = new Template_Punctuation('-');
	public @S(20) Template_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int value = interpreter.getIntValue(expr);
		interpreter.pushInt(-value);
	}
}
