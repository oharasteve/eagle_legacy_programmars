// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Method;
import com.eagle.programmar.Java.Java_Method.Java_MethodBody.Java_MethodImplementation;
import com.eagle.programmar.Java.Java_Method.Java_MethodTypeAndName.Java_MethodType;
import com.eagle.programmar.Java.Java_ParameterList;
import com.eagle.programmar.Java.Java_ParameterList.Java_MethodParameter;
import com.eagle.programmar.Java.Java_StatementOrComment;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_MethodInvocation extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Java_Variable methodName;
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE @OPT Java_ArgumentList argList;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken token = methodName.firstId.getWhich();
		if (token instanceof Java_Identifier_Reference)
		{
			// Look it up
			String name = ((Java_Identifier_Reference) token).getValue();
			Java_Method proc = null;
			Java_ParameterList parameters = null;
			for (AbstractFunction fn : interpreter._functionList)
			{
				Java_Method meth = (Java_Method) fn;
				AbstractToken which = meth.typeAndName.getWhich();
				if (which instanceof Java_MethodType)
				{
					Java_MethodType methodType = (Java_MethodType) which;
					if (methodType.methodName.getValue().equals(name))
					{
						proc = meth;
						parameters = methodType.parameters;
						break;
					}
				}
			}
			if (proc == null)
			{
				throw new RuntimeException("Unable to find a method named " + name);
			}

			// Make sure the function args match up
			int argCount = 0;
			if (argList.arg.isPresent()) argCount = 1;
			if (argList.moreArgs.isPresent()) argCount = 1 + argList.moreArgs.size();

			int paramCount = 0;
			if (parameters.param.isPresent()) paramCount = 1;
			if (parameters.moreParams.isPresent()) paramCount = 1 + parameters.moreParams.size();
			
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

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
					interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
							param.id.getValue(), val);
				}
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			// EagleValue returnValue = null;
			AbstractToken body = proc.body.getWhich();
			if (body instanceof Java_MethodImplementation)
			{
				Java_MethodImplementation impl = (Java_MethodImplementation) body;
				for (Java_StatementOrComment stmt : impl.block.statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			proc._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

			// Now remove all those parameters
			if (argCount > 0)
			{
				Java_MethodParameter param = parameters.param;
				interpreter._symbolTable.removeSymbols(param.id.getValue());
				for (int i = 1; i < argCount; i++)
				{
					param = parameters.moreParams._elements.get(i-1).param;
					interpreter._symbolTable.removeSymbols(param.id.getValue());
				}
			}
		}
		else
		{
			throw new RuntimeException("Unable to call method " + methodName);
		}
	}
}
