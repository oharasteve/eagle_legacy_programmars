// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_ArgumentList;
import com.eagle.programmar.C.C_Function;
import com.eagle.programmar.C.C_Function.C_FunctionImplementation;
import com.eagle.programmar.C.C_Function.C_FunctionRegularParameter;
import com.eagle.programmar.C.C_Function.C_Function_ParameterDefs;
import com.eagle.programmar.C.C_Function.C_Function_TypeAndName;
import com.eagle.programmar.C.C_Generic;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_FunctionCall extends PrimaryOperator implements EagleRunnable
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
			C_Function func = null;
			for (AbstractFunction absFn : interpreter._functionList)
			{
				C_Function fn = (C_Function) absFn;
				AbstractToken which = fn.typeName.getWhich();
				if (which instanceof C_Function_TypeAndName)
				{
					C_Function_TypeAndName typeAndName = (C_Function_TypeAndName) which;
					String thisName = typeAndName.functionName.getValue();
					if (thisName.equals(fnName))
					{
						// Found it!
						func = fn;
						break;
					}
				}
			}

			if (func == null)
			{
				throw new RuntimeException("Unable to find a function named " + fnName);
			}

			// Count the parameters
			C_Function_ParameterDefs params = func.parameters;
			int expected = 0;
			if (params.param != null && params.param.isPresent())
			{
				C_FunctionRegularParameter param = (C_FunctionRegularParameter) params.param.getWhich();
				String paramName = param.id.getValue();
				if (paramName != null && ! paramName.equals("void"))
				{
					// 'f(void)' is special here meaning no arguments
					expected++;
				}
			}
			if (params.moreParams != null && params.moreParams.isPresent()) expected += params.moreParams.size();

			int actual = 0;
			if (argList.arg != null && argList.arg.isPresent()) actual++;
			if (argList.moreArgs != null && argList.moreArgs.isPresent()) actual += argList.moreArgs.size();
			
			if (actual != expected)
			{
				throw new RuntimeException(
						"Function " + fnName + ", expected params = " + expected + ", but actual args = " + actual);
			}
			if (interpreter._TRACE)
			{
				System.out.println("** Calling " + fnName + " with #args=" + actual);
			}
			
			// Assign all the parameters
			AbstractToken arg = argList.arg.getWhich();
			C_FunctionRegularParameter param = (C_FunctionRegularParameter) params.param.getWhich();
			for (int i = 0; i < actual; i++)
			{
				if (i > 0)
				{
					arg = argList.moreArgs._elements.get(i-1).arg;
					param = (C_FunctionRegularParameter) params.moreParams._elements.get(i-1).param.getWhich();
				}
				
				EagleValue val = interpreter.getEagleValue(arg);
				interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
						param.id.getValue(), val);
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
			func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

			// Remove all the parameters
			param = (C_FunctionRegularParameter) params.param.getWhich();
			for (int i = 0; i < actual; i++)
			{
				if (i > 0)
				{
					param = (C_FunctionRegularParameter) params.moreParams._elements.get(i-1).param.getWhich();
				}
				interpreter._symbolTable.removeSymbols(param.id.getValue());
			}
		}
	}
}
