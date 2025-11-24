// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_ArgumentList;
import com.eagle.programmar.C.C_ArgumentList.C_MoreArgument;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Function;
import com.eagle.programmar.C.C_Function.C_FunctionImplementation;
import com.eagle.programmar.C.C_Function.C_FunctionRegularParameter;
import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.C_Generic;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_FunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) @OPT CPlus_NamespaceList namespace;
	public @S(20) C_Variable functionName;
	public @S(30) @OPT C_Generic generic;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT C_ArgumentList argList;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken token = functionName.firstId.getWhich();
		if (token instanceof C_Identifier_Reference)
		{
			C_Identifier_Reference id = (C_Identifier_Reference) token;
			String fnName = id.getValue();

			// Look through our list of functions
			AbstractFunction fn = interpreter.findFunction(fnName);
			if (fn == null)
			{
				throw new RuntimeException("Unable to find a function named " + fnName);
			}
			C_Function func = (C_Function) fn;

			// Count the parameters
			C_Function_ParameterDefs params = func.parameters;
			int expected = 0;
			if (params.param != null && params.param.isPresent())
			{
				C_FunctionRegularParameter param = (C_FunctionRegularParameter) params.param.getWhich();
				if (param.id != null)
				{
					String paramName = param.id.getValue();
					if (paramName != null && !paramName.equals("void"))
					{
						// 'f(void)' is special here meaning no arguments
						expected++;
					}
				}
			}
			if (params.moreParams != null && params.moreParams.isPresent()) expected += params.moreParams.size();

			int actual = 0;
			if (argList != null)
			{
				if (argList.arg != null && argList.arg.getWhich() != null) actual++;
				if (argList.moreArgs != null && argList.moreArgs.isPresent()) actual += argList.moreArgs.size();
			}

			if (actual != expected)
			{
				throw new RuntimeException(
						"Function " + fnName + ", expected params = " + expected + ", but actual args = " + actual);
			}

			interpreter.callingFunction(fnName, func);

			// Assign all the parameters
			ArrayList<String> argTypes = new ArrayList<String>();
			if (argList != null)
			{
				AbstractToken arg = argList.arg.getWhich();
				C_FunctionRegularParameter param = (C_FunctionRegularParameter) params.param.getWhich();
				for (int i = 0; i < actual; i++)
				{
					if (i > 0)
					{
						arg = argList.moreArgs._elements.get(i - 1).arg;
						param = (C_FunctionRegularParameter) params.moreParams._elements.get(i - 1).param.getWhich();
					}

					EagleValue val = interpreter.getEagleValue(arg);
					interpreter.setSymbol(param.id, param.id.getValue(), val);
					argTypes.add(val.typeName());
				}
			}

			// Evaluate the function
			long startTime = System.nanoTime();

			C_FunctionImplementation impl = (C_FunctionImplementation) func.body.getWhich();
			for (C_StatementOrComment stmt : impl.elements._elements)
			{
				Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}

			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Remove all the parameters
			interpreter.completedFunction(fnName, func);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractToken which0 = functionName.firstId.getWhich();
		if (!(which0 instanceof C_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which0);
		}
		C_Identifier_Reference id = (C_Identifier_Reference) which0;
		String name = id.getValue();
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();

		if (argList != null && argList.isPresent())
		{
			AbstractToken which1 = argList.arg.getWhich();
			if (which1 instanceof C_Expression)
			{
				C_Expression arg1 = (C_Expression) which1;
				AbstractExpression newArg1 = transformer.transformExpression(generator, arg1);
				args.add(newArg1);

				if (argList.moreArgs != null)
				{
					for (C_MoreArgument more : argList.moreArgs._elements)
					{
						AbstractToken which2 = more.arg.getWhich();
						if (which2 instanceof C_Expression)
						{
							C_Expression arg2 = (C_Expression) which2;
							AbstractExpression newArg2 = transformer.transformExpression(generator, arg2);
							args.add(newArg2);
						}
					}
				}
			}
		}

		AbstractVariable var = generator.newVariable(name);
		return generator.newMethodInvocation(var, args, id);
	}
}
