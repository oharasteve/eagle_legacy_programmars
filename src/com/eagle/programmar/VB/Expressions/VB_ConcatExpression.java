// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class VB_ConcatExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) VB_Punctuation ampersand = new VB_Punctuation('&');
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftValue = interpreter.getStrValue(left);
		String rightValue = interpreter.getStrValue(right);
		interpreter.pushStr(leftValue + rightValue);
	}
}
