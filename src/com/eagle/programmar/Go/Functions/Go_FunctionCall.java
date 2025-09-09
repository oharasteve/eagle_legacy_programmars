// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Statements.Go_Function;
import com.eagle.programmar.Go.Statements.Go_Function.Go_FunctionParameter;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Go_FunctionCall extends PrimaryOperator
		implements EagleRunnable, AbstractStatement, EagleTransformableExpression
{
	public @S(10) Go_Variable funcName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Go_Expression, PunctuationComma> arguments;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = funcName.vars.first().getValue();
		
		AbstractFunction fn = interpreter.findFunction(fnName);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + fnName);
		}
		Go_Function func = (Go_Function) fn;

		// Make sure the function args match up
		int argCount = arguments.getPrimaryCount();

		int paramCount = 0;
		if (func.funcParamDefs != null && func.funcParamDefs.isPresent())
		{
			paramCount = func.funcParamDefs.getPrimaryCount();
		}
		
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + fnName + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		ArrayList<String> argTypes = new ArrayList<String>();
		for (int i = 0; i < argCount; i++)
		{
			Go_Expression expr = arguments.getPrimaryElement(i);
			Go_FunctionParameter param = func.funcParamDefs.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.var.getValue(), val);
			argTypes.add(val.typeName());
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		interpreter.callingFunction(fnName, func);
		interpreter.tryToInterpret(func.stmt);

		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		interpreter.completedFunction(fnName, func);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		String name = funcName.vars.first().getValue();
		if (generator.isKnownMethod(name))
		{
			ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
			int argCount = arguments.getPrimaryCount();
			for (int i = 0; i < argCount; i++)
			{
				Go_Expression arg = arguments.getPrimaryElement(i);
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.add(newArg);
			}
	
			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, funcName);
		}

		// Dang. Scale uses () for both arrays and function calls
		// It is not a function, so must be an array
		AbstractExpression index = transformer.transformExpression(generator,
				arguments.first());
		return generator.newVariableExpression(name, SubscriptEnum.FIRST_IS_ZERO, index, this);
	}
}
