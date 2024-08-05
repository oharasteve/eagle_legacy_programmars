// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_Argument;
import com.eagle.programmar.CSharp.CSharp_Argument.CSharp_ArgumentOut;
import com.eagle.programmar.CSharp.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Method;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter;
import com.eagle.programmar.CSharp.CSharp_MethodImplementation;
import com.eagle.programmar.CSharp.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_MethodInvocation extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CSharp_Variable methodName;
	public @S(20) @OPT CSharp_GenericType generic;
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @OPT @NOSPACE CSharp_ArgumentList argList;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken token = methodName.firstId.getWhich();
		if (token instanceof CSharp_Identifier_Reference)
		{
			// Look it up
			String name = ((CSharp_Identifier_Reference) token).getValue();
			AbstractFunction fn = interpreter._functionList.get(name);
			if (fn == null)
			{
				throw new RuntimeException("Unable to find a method named " + name);
			}
			CSharp_Method meth = (CSharp_Method) fn;

			// Make sure the function args match up
			int argCount = 0;
			if (argList.arg.isPresent()) argCount = 1;
			if (argList.moreArgs != null && argList.moreArgs.isPresent()) argCount = 1 + argList.moreArgs.size();

			int paramCount = 0;
			if (meth.parameters.param.isPresent()) paramCount = 1;
			if (meth.parameters.moreParams != null && meth.parameters.moreParams.isPresent())
			{
				paramCount = 1 + meth.parameters.moreParams.size();
			}
			
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			if (argCount > 0)
			{
				CSharp_Argument arg = argList.arg;
				CSharp_MethodParameter param = meth.parameters.param;
				for (int i = 0; i < argCount; i++)
				{
					if (i > 0)
					{
						arg = argList.moreArgs._elements.get(i-1).arg;
						param = meth.parameters.moreParams._elements.get(i-1).param;
					}
					AbstractToken which = arg.getWhich();
					if (which instanceof CSharp_ArgumentOut)
					{
						CSharp_Expression expr = ((CSharp_ArgumentOut) which).arg;
						EagleValue val = interpreter.getEagleValue(expr);
						interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
								param.id.getValue(), val);
					}
				}
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			// EagleValue returnValue = null;
			AbstractToken body = meth.body.getWhich();
			if (body instanceof CSharp_MethodImplementation)
			{
				CSharp_MethodImplementation impl = (CSharp_MethodImplementation) body;
				for (CSharp_StatementOrComment stmt : impl.block.statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			meth._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

			// Now remove all those parameters
			if (argCount > 0)
			{
				CSharp_MethodParameter param = meth.parameters.param;
				interpreter._symbolTable.removeSymbols(param.id.getValue());
				for (int i = 1; i < argCount; i++)
				{
					param = meth.parameters.moreParams._elements.get(i-1).param;
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
