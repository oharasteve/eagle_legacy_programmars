// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Statements.Ruby_Function;
import com.eagle.programmar.Ruby.Symbols.Ruby_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Ruby_FunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Ruby_Variable funcName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Ruby_Expression, PunctuationComma> arguments;
	public @S(40) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ruby_Identifier_Reference id = funcName.vars.first();
		String name = id.getValue();
		
		// Look up the function
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		Ruby_Function func = (Ruby_Function) fn;

		// Make sure the function args match up
		int argCount = arguments.getPrimaryCount();
		int paramCount = func.funcParamDefs.parameters.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		ArrayList<String> argTypes = new ArrayList<String>();
		for (int i = 0; i < argCount; i++)
		{
			Ruby_Expression expr = arguments.getPrimaryElement(i);
			Ruby_Variable param = func.funcParamDefs.parameters.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.vars.first().getValue(), val);
			argTypes.add(val.typeName());
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		interpreter.callingFunction(name, func);
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Ruby_Statement stmt : func.statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		interpreter.completedFunction(name, func);
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
				Ruby_Expression arg = arguments.getPrimaryElement(i);
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.add(newArg);
			}
	
			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, funcName);
		}

		// Dang. Scala uses () for both arrays and function calls
		// It is not a function, so must be an array
		AbstractExpression index = transformer.transformExpression(generator,
				arguments.first());
		return generator.newVariableExpression(name, SubscriptEnum.FIRST_IS_ZERO, index, this);
	}
}
