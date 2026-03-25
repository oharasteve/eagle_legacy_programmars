// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Function;
import com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
import com.eagle.programmar.AWK.Symbols.AWK_Parameter_Definition;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class AWK_CallFunction extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) AWK_Identifier_Reference functionName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT AWK_ArgumentList argList;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Have to search for the FUNCTION definition
		AbstractFunction fn = interpreter.findFunction(functionName.getValue());
		String name = functionName.getValue();
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		AWK_Function func = (AWK_Function) fn;

		// Doesn't do much, just set metrics
		interpreter.tryToInterpret(func);

		// Make sure the function args match up
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		int argCount = 0;
		if (argList != null && argList.isPresent())
		{
			argCount = 1;
			if (argList.more != null) argCount += argList.more.size();
			int paramCount = func.parameters.params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, func);

			// Now assign all the parameters
			AWK_Expression arg = argList.expr;
			for (int i = 0; i < argCount; i++)
			{
				AWK_Parameter_Definition param = func.parameters.params.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(arg);
				interpreter.setSymbol(param, param.getValue(), val);
				argTypes.add(val.getType());
				if (i < argCount - 1)
				{
					arg = argList.more._elements.get(i).expr;
				}
			}
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		for (AWK_StatementOrComment stmt : func.body.elements._elements)
		{
			Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
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
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		if (argList != null && argList.isPresent())
		{
			args.add(transformer.transformExpression(generator, argList.expr));
			for (AWK_MoreArguments more : argList.more._elements)
			{
				args.add(transformer.transformExpression(generator, more.expr));
			}
		}

		AbstractVariable var = generator.newVariable(functionName.getValue());
		return generator.newMethodInvocation(var, args, this);
	}
}
