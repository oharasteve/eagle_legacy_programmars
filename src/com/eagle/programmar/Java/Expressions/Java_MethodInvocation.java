// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_ArgumentList.Java_MoreArguments;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Method;
import com.eagle.programmar.Java.Java_Method.Java_MethodImplementation;
import com.eagle.programmar.Java.Java_Method.Java_MethodType;
import com.eagle.programmar.Java.Java_ParameterList;
import com.eagle.programmar.Java.Java_ParameterList.Java_MethodParameter;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
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

public class Java_MethodInvocation extends PrimaryOperator
		implements EagleRunnableWithResult, EagleTransformableExpression
{
	public @S(10) Java_Variable methodName;
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE @OPT Java_ArgumentList argList;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		AbstractToken token = methodName.firstId.getWhich();
		if (token instanceof Java_Identifier_Reference)
		{
			// Look it up
			String name = ((Java_Identifier_Reference) token).getValue();
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new RuntimeException("Unable to find a method named " + name);
			}

			Java_Method meth = (Java_Method) fn;
			Java_ParameterList parameters = null;
			AbstractToken which = meth.typeAndName.getWhich();
			if (which instanceof Java_MethodType)
			{
				Java_MethodType methodType = (Java_MethodType) which;
				parameters = methodType.parameters;
			}

			// Make sure the function args match up
			int argCount = 0;
			if (argList != null)
			{
				if (argList.arg != null && argList.arg.getWhich() != null) argCount = 1;
				if (argList.moreArgs != null && argList.moreArgs.isPresent()) argCount += argList.moreArgs.size();
			}

			int paramCount = parameters.params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, meth);

			// Now assign all the parameters
			ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
			if (argCount > 0)
			{
				Java_Expression expr = argList.arg;
				for (int i = 0; i < argCount; i++)
				{
					if (i > 0)
					{
						expr = argList.moreArgs._elements.get(i - 1).arg;
					}
					Java_MethodParameter param = parameters.params.getPrimaryElement(i);
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param.id, param.id.getValue(), val);
					argTypes.add(val.getType());
				}
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			AbstractToken body = meth.body.getWhich();
			if (body instanceof Java_MethodImplementation)
			{
				Java_MethodImplementation impl = (Java_MethodImplementation) body;
				result = interpreter.tryToInterpret(impl.block);
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			meth._callMetrics.addCallFrom(this, elapsedTime);
			meth._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(name, meth);
		}
		else
		{
			throw new RuntimeException("Unable to call method " + methodName);
		}
		return result;
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Java_Variable variable = this.methodName;
		if (variable.firstId.getWhich() instanceof Java_Identifier_Reference)
		{
			Java_Identifier_Reference id = (Java_Identifier_Reference) variable.firstId.getWhich();
			String name = id.getValue();
			ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
			ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);
			if (this.argList != null && this.argList.isPresent())
			{
				args.add(transformer.transformExpression(generator, this.argList.arg));
				if (this.argList.moreArgs != null && this.argList.moreArgs.isPresent())
				{
					for (Java_MoreArguments arg : this.argList.moreArgs._elements)
					{
						args.add(transformer.transformExpression(generator, arg.arg));
					}
				}
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, types, this);
		}
		throw new RuntimeException("Can't handle: " + this);
	}

	public static Java_Expression generateInvocation(Java_Variable var,
			ArrayList<Java_Expression> args, ArrayList<TypeEnum> types, AbstractToken source)
	{
		Java_MethodInvocation invoke = new Java_MethodInvocation();
		invoke.methodName = new Java_Variable();
		invoke.methodName.firstId = var.firstId;
		invoke.leftParen = new PunctuationLeftParen();
		invoke.argList = Java_ArgumentList.createArgumentList(args);
		if (invoke.argList != null)
		{
			invoke.argList.setPresent(true);
		}
		invoke.rightParen = new PunctuationRightParen();

		invoke.setTransformationSource(source);
		return Java_Generator.wrapExpression(invoke);
	}
}
