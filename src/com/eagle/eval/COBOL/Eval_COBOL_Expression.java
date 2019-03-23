// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 29, 2017

package com.eagle.eval.COBOL;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Expression.COBOL_AndCondition;
import com.eagle.programmar.COBOL.COBOL_Expression.COBOL_BuiltIn;
import com.eagle.programmar.COBOL.COBOL_Expression.COBOL_OrCondition;
import com.eagle.tokens.AbstractToken;

public class Eval_COBOL_Expression
{
	public void interpret(COBOL_Expression expr, EagleInterpreter interpreter)
	{
		AbstractToken which = expr.getWhich();
		
		if (which instanceof EagleRunnable)
		{
			EagleRunnable runnable = (EagleRunnable) which;
			runnable.interpret(interpreter);
		}
		else if (which instanceof COBOL_BuiltIn)
		{
			interpretBuiltin((COBOL_BuiltIn) which, interpreter);
		}
		else if (which instanceof COBOL_AndCondition)
		{
			interpretAnd((COBOL_AndCondition) which, interpreter);
		}
		else if (which instanceof COBOL_OrCondition)
		{
			interpretOr((COBOL_OrCondition) which, interpreter);
		}
		else
		{
			throw new RuntimeException("Unable to evaulate expression " + (which.getClass().getName()));
		}
	}
	
	private static void interpretBuiltin(COBOL_BuiltIn expr, EagleInterpreter interpreter)
	{
		String name = expr.logicalConstant.toString();
		switch (name)
		{
		case "FALSE":
			interpreter.pushBool(false);
			break;
		case "TRUE":
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than TRUE/FALSE: " + name);
		}
	}
	
	private static void interpretAnd(COBOL_AndCondition expr, EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(expr.left);
		boolean rightValue = interpreter.getBoolValue(expr.right);
		interpreter.pushBool(leftValue && rightValue);
	}
	
	private static void interpretOr(COBOL_OrCondition expr, EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(expr.left);
		boolean rightValue = interpreter.getBoolValue(expr.right);
		interpreter.pushBool(leftValue || rightValue);
	}
}
