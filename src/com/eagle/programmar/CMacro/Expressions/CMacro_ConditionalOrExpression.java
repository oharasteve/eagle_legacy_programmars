package com.eagle.programmar.CMacro.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class CMacro_ConditionalOrExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CMacro_Expression left = new CMacro_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) CMacro_Punctuation orOperator = new CMacro_Punctuation("||");
	public @S(30) CMacro_Expression right = new CMacro_Expression(this, AllowedPrecedence.HIGHER);
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftVal = interpreter.getBoolValue(left);
		if (leftVal)
		{
			// Short circuit a bit
			interpreter.pushBool(true);
		}
		else
		{
			boolean rightVal = interpreter.getBoolValue(right);
			interpreter.pushBool(rightVal);
		}
	}
}
