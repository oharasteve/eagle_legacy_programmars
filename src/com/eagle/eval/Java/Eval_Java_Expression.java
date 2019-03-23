// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 29, 2017

package com.eagle.eval.Java;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Expression.Java_AdditiveExpression;
import com.eagle.programmar.Java.Java_Expression.Java_BuiltIn;
import com.eagle.programmar.Java.Java_Expression.Java_ConditionalAndExpression;
import com.eagle.programmar.Java.Java_Expression.Java_ConditionalOrExpression;
import com.eagle.programmar.Java.Java_Expression.Java_MethodInvocation;
import com.eagle.programmar.Java.Java_Expression.Java_MultiplicativeExpression;
import com.eagle.programmar.Java.Java_Expression.Java_ParenthesizedExpression;
import com.eagle.programmar.Java.Java_Expression.Java_VariableExpression;
import com.eagle.tokens.AbstractToken;

public class Eval_Java_Expression
{
	public void interpret(Java_Expression expr, EagleInterpreter interpreter)
	{
		AbstractToken which = expr.getWhich();
		
		if (which instanceof EagleRunnable)
		{
			EagleRunnable runnable = (EagleRunnable) which;
			runnable.interpret(interpreter);
		}
		else if (which instanceof Java_VariableExpression)
		{
			interpretVar((Java_VariableExpression) which, interpreter);
		}
		else if (which instanceof Java_ParenthesizedExpression)
		{
			interpretParens((Java_ParenthesizedExpression) which, interpreter);
		}
		else if (which instanceof Java_MethodInvocation)
		{
			interpretMethod((Java_MethodInvocation) which, interpreter);
		}
		else if (which instanceof Java_BuiltIn)
		{
			interpretBuiltin((Java_BuiltIn) which, interpreter);
		}
		else if (which instanceof Java_MultiplicativeExpression)
		{
			interpretMult((Java_MultiplicativeExpression) which, interpreter);
		}
		else if (which instanceof Java_AdditiveExpression)
		{
			interpretAdd((Java_AdditiveExpression) which, interpreter);
		}
		else if (which instanceof Java_ConditionalAndExpression)
		{
			interpretAnd((Java_ConditionalAndExpression) which, interpreter);
		}
		else if (which instanceof Java_ConditionalOrExpression)
		{
			interpretOr((Java_ConditionalOrExpression) which, interpreter);
		}
		else
		{
			throw new RuntimeException("Unable to evaulate expression " + (which.getClass().getName()));
		}
	}

	private static void interpretVar(Java_VariableExpression expr, EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr.variable);
	}

	private static void interpretParens(Java_ParenthesizedExpression expr, EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr.expression);
	}

	private static void interpretMethod(Java_MethodInvocation expr, EagleInterpreter interpreter)
	{
		// Assume System.out.println(expr.exp);
		EagleValue result = interpreter.getEagleValue(expr.argList.arg);
		System.out.println(result.toString());
	}
	
	private static void interpretBuiltin(Java_BuiltIn expr, EagleInterpreter interpreter)
	{
		switch (expr.builtinConstant.toString())
		{
		case "false" :
			interpreter.pushBool(false);
			break;
		case "true" :
			interpreter.pushBool(true);
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than true/false: " + expr.builtinConstant);
		}
	}

	private static void interpretMult(Java_MultiplicativeExpression expr, EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(expr.left);
		int rightValue = interpreter.getIntValue(expr.right);
		switch (expr.operator.toString())
		{
		case "*" :
			interpreter.pushInt(leftValue * rightValue);
			break;
		case "/" :
			interpreter.pushInt(leftValue / rightValue);
			break;
		case "%" :
			interpreter.pushInt(leftValue % rightValue);
			break;
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + expr.operator);
		}
	}

	private static void interpretAdd(Java_AdditiveExpression expr, EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(expr.left);
		int rightValue = interpreter.getIntValue(expr.right);
		switch (expr.operator.toString())
		{
		case "+" :
			interpreter.pushInt(leftValue + rightValue);
			break;
		case "-" :
			interpreter.pushInt(leftValue - rightValue);
			break;
		default:
			throw new RuntimeException("Unexpected additive operator: " + expr.operator);
		}
	}

	private static void interpretAnd(Java_ConditionalAndExpression expr, EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(expr.left);
		boolean rightValue = interpreter.getBoolValue(expr.right);
		interpreter.pushBool(leftValue && rightValue);
	}

	private static void interpretOr(Java_ConditionalOrExpression expr, EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(expr.left);
		boolean rightValue = interpreter.getBoolValue(expr.right);
		interpreter.pushBool(leftValue || rightValue);
	}
}
