// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_Expression;
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
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_MethodInvocation extends PrimaryOperator implements EagleRunnableWithResult
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
				if (argList.arg != null && argList.arg.isPresent()) argCount = 1;
				if (argList.moreArgs != null && argList.moreArgs.isPresent()) argCount += argList.moreArgs.size();
			}

			int paramCount = 0;
			if (parameters != null)
			{
				if (parameters.param != null && parameters.param.isPresent()) paramCount = 1;
				if (parameters.moreParams != null && parameters.moreParams.isPresent())
				{
					paramCount += parameters.moreParams.size();
				}
			}
			
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, meth);

			// Now assign all the parameters
			if (argCount > 0)
			{
				Java_Expression expr = argList.arg;
				Java_MethodParameter param = parameters.param;
				for (int i = 0; i < argCount; i++)
				{
					if (i > 0)
					{
						expr = argList.moreArgs._elements.get(i-1).arg;
						param = parameters.moreParams._elements.get(i-1).param;
					}
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param.id, param.id.getValue(), val);
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
			meth._metrics.addCallFrom(this, elapsedTime);

			// Now remove all those parameters
			interpreter.completedFunction(name, meth);
		}
		else
		{
			throw new RuntimeException("Unable to call method " + methodName);
		}
		return result;
	}
}
