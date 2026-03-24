// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rexx.Rexx_Element;
import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.programmar.Rexx.Statements.Rexx_Function;
import com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference;
import com.eagle.programmar.Rexx.Symbols.Rexx_Variable_Definition;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rexx_FunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rexx_Identifier_Reference fnName;
	public @S(20) Rexx_FnCallArguments callArguments;

	public static class Rexx_FnCallArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Rexx_Expression, PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = fnName.getValue();

		// See if it is a subscripted variable first
		EagleValue value = interpreter.findSymbol(name);
		if (value != null && value.isArray())
		{
			EagleArray array = (EagleArray) value;
			int index = interpreter.getIntValue(callArguments.arguments.first());
			interpreter.pushEagleValue(array.getValue(index));
			return;
		}

		// Look up the function
		Rexx_Function func = (Rexx_Function) interpreter.findFunction(name);
		if (func == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}

		// Make sure the function args match up
		int argCount = callArguments.arguments.getPrimaryCount();
		int paramCount = func.params.params.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(name, func);

		// Now assign all the parameters
		ArrayList<String> argTypes = new ArrayList<String>();
		for (int i = 0; i < argCount; i++)
		{
			Rexx_Expression expr = callArguments.arguments.getPrimaryElement(i);
			Rexx_Variable_Definition param = func.params.params.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.getValue(), val);
			argTypes.add(val.typeName());
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Rexx_Element stmt : func.stmts._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// Need to put the result on the runtime stack
		// Rexx uses the function name for the return value
		// Sort-of like this: Function sqrt(x) ; sqrt = x*x ; End Function
		EagleValue val = interpreter.findSymbol(name);
		if (val != null)
		{
			interpreter.pushEagleValue(val);
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
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String name = fnName.getValue();
		if (generator.isKnownMethod(name))
		{
			ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
			int argCount = callArguments.arguments.getPrimaryCount();
			for (int i = 0; i < argCount; i++)
			{
				Rexx_Expression arg = callArguments.arguments.getPrimaryElement(i);
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.add(newArg);
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, fnName);
		}

		// Dang. Rexx uses () for both arrays and function calls
		// It is not a function, so must be an array
		AbstractExpression index = transformer.transformExpression(generator,
				callArguments.arguments.first());
		return generator.newVariableExpression(name, SubscriptEnum.FIRST_IS_ZERO, index, this);
	}
}
