// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 14, 2024

package com.eagle.programmar.Template.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Template.Template_Expression;
import com.eagle.programmar.Template.Terminals.Template_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class Template_AndExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Template_Expression left = new Template_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Template_Keyword AND = new Template_Keyword("and");
	public @S(30) Template_Expression right = new Template_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		boolean rightValue = interpreter.getBoolValue(right);
		interpreter.pushBool(leftValue && rightValue);
	}
}
