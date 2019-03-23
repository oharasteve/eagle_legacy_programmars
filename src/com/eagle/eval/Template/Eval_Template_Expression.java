// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 29, 2017

package com.eagle.eval.Template;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Template.Template_Expression;
import com.eagle.programmar.Template.Template_Expression.Template_Additive_Expression;
import com.eagle.tokens.AbstractToken;

public class Eval_Template_Expression
{
	public void interpret(Template_Expression expr, EagleInterpreter interpreter)
	{
		AbstractToken which = expr.getWhich();
		
		if (which instanceof EagleRunnable)
		{
			EagleRunnable runnable = (EagleRunnable) which;
			runnable.interpret(interpreter);
		}
		else if (which instanceof Template_Additive_Expression)
		{
			interpretAdd((Template_Additive_Expression) which, interpreter);
		}
	}
	
	private static void interpretAdd(Template_Additive_Expression expr, EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(expr.left);
		int rightValue = interpreter.getIntValue(expr.right);
		String oper = expr.operator.toString();
		switch (oper)
		{
		case "+":
			interpreter.pushInt(leftValue + rightValue);
			break;
		case "-":
			interpreter.pushInt(leftValue - rightValue);
			break;
		default:
			throw new RuntimeException("Unexpected additive operator: " + expr.operator);
		}
	}
}
