// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Function;
import com.eagle.programmar.Javascript.Javascript_FunctionBody;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_FunctionParameter;
import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_FunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_Variable functionName;
	public @S(20) Javascript_ParenthesizedExpression arguments;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = "unknown";
		AbstractToken first = functionName.firstId.getWhich();
		if (first instanceof Javascript_Identifier_Reference)
		{
			// Look it up
			name = ((Javascript_Identifier_Reference) first).getValue();
		}

		// Make sure the function args match up
		int argCount = 0;
		if (arguments.expressions != null && arguments.expressions.isPresent())
		{
			argCount = arguments.expressions.getPrimaryCount();
		}

		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		Javascript_Function func = (Javascript_Function) fn;

		Javascript_FunctionParameters parameters = func.implementation.params;
		int paramCount = 0;
		if (parameters != null && parameters.isPresent())
		{
			if (parameters.param != null) paramCount = 1;
			if (parameters.moreParams != null && parameters.moreParams.isPresent())
			{
				paramCount += parameters.moreParams.size();
			}
		}

		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		if (argCount > 0)
		{
			Javascript_FunctionParameter param = parameters.param;
			for (int i = 0; i < argCount; i++)
			{
				Javascript_Expression expr = arguments.expressions.getPrimaryElement(i);
				if (i > 0)
				{
					param = parameters.moreParams._elements.get(i - 1).param;
				}
				EagleValue val = interpreter.getEagleValue(expr);
				AbstractToken which = param.paramName.getWhich();
				if (which instanceof Javascript_Variable_Definition)
				{
					Javascript_Variable_Definition id = (Javascript_Variable_Definition) which;
					interpreter.setSymbol(param, id.getValue(), val);
					argTypes.add(val.getType());
				}
			}
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		interpreter.callingFunction(name, func);
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		Javascript_FunctionBody body = func.implementation.body;
		for (Javascript_StatementOrComment stmt : body.statements._elements)
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
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Javascript_Variable variable = this.functionName;
		if (variable.firstId.getWhich() instanceof Javascript_Identifier_Reference)
		{
			Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) variable.firstId.getWhich();
			String name = id.getValue();
			ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);
			ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
			int numArgs = arguments.expressions.getPrimaryCount();
			for (int i = 0; i < numArgs; i++)
			{
				Javascript_Expression expr = arguments.expressions.getPrimaryElement(i);
				args.add(transformer.transformExpression(generator, expr));
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, types, this);
		}
		throw new RuntimeException("Can't handle: " + this);
	}
}
