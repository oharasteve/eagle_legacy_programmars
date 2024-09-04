// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Python_Power_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Python_Punctuation stars = new Python_Punctuation("**");
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		interpreter.pushInt((int) Math.round(Math.pow(leftValue, rightValue)));
	}
	
	public static Python_Power_Expression generateExpression(AbstractExpression leftExpr, AbstractExpression rightExpr, AbstractToken source)
	{
		Python_Power_Expression expr = new Python_Power_Expression();
		expr.left = (Python_Expression) leftExpr;
		expr.right = (Python_Expression) rightExpr;
		expr.setTransformationSource(source);
		return expr;
	}
}