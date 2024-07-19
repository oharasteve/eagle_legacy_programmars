// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Function;
import com.eagle.programmar.Javascript.Javascript_FunctionBody;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_FunctionParameter;
import com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
import com.eagle.programmar.Javascript.Javascript_Statement.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Javascript_Variable.Javascript_DotField;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Variable functionName;
	public @S(20) Javascript_ParenthesizedExpression arguments;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = "unknown";
		String name1 = null;
		AbstractToken first = functionName.firstId.getWhich();
		if (first instanceof Javascript_Identifier_Reference)
		{
			// Look it up
			name = ((Javascript_Identifier_Reference) first).getValue();
			name1 = name;
			if (functionName.moreIds != null && functionName.moreIds.isPresent())
			{
				for (Javascript_DotField next : functionName.moreIds._elements)
				{
					name = name1 + "." + next.id.getValue();
				}
			}
		}
		
		// Make sure the function args match up
		int argCount = 0;
		if (arguments.expressions != null && arguments.expressions.isPresent())
		{
			argCount = arguments.expressions.getPrimaryCount();
		}

		if (name.equals("Math.floor") && argCount == 1)
		{
			// Hand it off to the Expression evaluator
			Javascript_BuiltinFunction.floor(interpreter, arguments.expressions.getPrimaryElement(0));
			return;
		}
		
		if (name.equals("document.writeln") && argCount == 1)
		{
			// Hand it off to the Expression evaluator
			Javascript_BuiltinFunction.writeln(interpreter, arguments.expressions.getPrimaryElement(0));
			return;
		}
		
		if (name.endsWith(".startsWith"))
		{
			// Hand it off to the Expression evaluator
			switch (argCount)
			{
			case 1:
				Javascript_BuiltinFunction.endsWith1(interpreter, name1,
						arguments.expressions.getPrimaryElement(0));
				return;
			case 2:
				Javascript_BuiltinFunction.endsWith2(interpreter, name1,
						arguments.expressions.getPrimaryElement(0),
						arguments.expressions.getPrimaryElement(1));
				return;
			}
		}

		Javascript_Function func = null;
		for (AbstractFunction absFn : interpreter._functionList)
		{
			Javascript_Function fn = (Javascript_Function) absFn;
			if (fn.implementation.functionName.getValue().equals(name))
			{
				func = fn;
				break;
			}
		}
		if (func == null)
		{
			throw new RuntimeException("Unable to find a method named " + name);
		}

		Javascript_FunctionParameters parameters = func.implementation.params;
		int paramCount = 0;
		if (parameters.param != null && parameters.param.isPresent()) paramCount = 1;
		if (parameters.moreParams != null && parameters.moreParams.isPresent())
		{
			paramCount += parameters.moreParams.size();
		}
		
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		if (argCount > 0)
		{
			Javascript_FunctionParameter param = parameters.param;
			for (int i = 0; i < argCount; i++)
			{
				Javascript_Expression expr = arguments.expressions.getPrimaryElement(i);
				if (i > 0)
				{
					param = parameters.moreParams._elements.get(i-1).param;
				}
				EagleValue val = interpreter.getEagleValue(expr);
				AbstractToken which = param.paramName.getWhich();
				if (which instanceof Javascript_Variable_Definition)
				{
					Javascript_Variable_Definition id = (Javascript_Variable_Definition) which;
					interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
							id.getValue(), val);
				}
			}
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		// EagleValue returnValue = null;
		Javascript_FunctionBody body = func.implementation.body;
		for (Javascript_StatementOrComment stmt : body.statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Now remove all those parameters
		if (argCount > 0)
		{
			Javascript_FunctionParameter param = parameters.param;
			AbstractToken which = param.paramName.getWhich();
			if (which instanceof Javascript_Variable_Definition)
			{
				Javascript_Variable_Definition id = (Javascript_Variable_Definition) which;
				interpreter._symbolTable.removeSymbols(id.getValue());
				for (int i = 1; i < argCount; i++)
				{
					param = parameters.moreParams._elements.get(i-1).param;
					interpreter._symbolTable.removeSymbols(id.getValue());
				}
			}
		}
		else
		{
			throw new RuntimeException("Unable to call function " + name);
		}
	}
}
