// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Params.Python_Parameter;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Statements.Python_FunctionDefinition;
import com.eagle.programmar.Python.Symbols.Python_Function_Definition;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Python_Function_Call extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Python_Variable fnName;
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE @OPT @SYNTAX(Python_Multiline_Syntax.class) SeparatedList<Python_Expression, PunctuationComma> argList;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = "unknown";
		if (fnName.var.getWhich() instanceof Python_Identifier_Reference)
		{
			Python_Identifier_Reference id = (Python_Identifier_Reference) fnName.var.getWhich();
			name = id.getValue();
		}

		if (name.equals("print"))
		{
			String line = interpreter.getStrValue(argList.first());
			System.out.println(line);
			return;
		}
		if (name.equals("len"))
		{
			String line = interpreter.getStrValue(argList.first());
			interpreter.pushInt(line.length());
			return;
		}
		if (name.equals("str"))
		{
			EagleValue obj = interpreter.getEagleValue(argList.first());
			interpreter.pushStr(obj.forceStringValue());
			return;
		}

		// Look up the function in our function list
		Python_FunctionDefinition func = null;
		for (AbstractFunction token : interpreter._functionList)
		{
			Python_FunctionDefinition fn = (Python_FunctionDefinition) token;
			if (fn.fnName.getWhich() instanceof Python_Function_Definition)
			{
				Python_Function_Definition def = (Python_Function_Definition) fn.fnName.getWhich();
				if (def.getValue().equals(name))
				{
					func = fn;
					break;
				}
			}
		}
		if (func == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}

		// Make sure the function args match up
		int argCount = argList.getPrimaryCount();
		int paramCount = 1;
		if (func.params.params.moreParams != null && func.params.params.moreParams.isPresent())
		{
			paramCount += func.params.params.moreParams.size();
		}
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		Python_Parameter param = func.params.params.param;
		for (int i = 0; i < argCount; i++)
		{
			Python_Expression expr = argList.getPrimaryElement(i);
			if (i > 0)
			{
				param = func.params.params.moreParams._elements.get(i-1).param;
			}
			if (param.getWhich() instanceof Python_Variable)
			{
				Python_Variable var = (Python_Variable) param.getWhich();
				if (var.var.getWhich() instanceof Python_Identifier_Reference)
				{
					Python_Identifier_Reference ref = (Python_Identifier_Reference) var.var.getWhich();
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
							ref.getValue(), val);
				}
			}
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the function
		interpreter.tryToInterpret(func.defBody);

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Now remove all those parameters
		Python_Parameter param2 = func.params.params.param;
		for (int i = 0; i < argCount; i++)
		{
			if (i > 0)
			{
				param2 = func.params.params.moreParams._elements.get(i-1).param;
			}
			if (param2.getWhich() instanceof Python_Variable)
			{
				Python_Variable var = (Python_Variable) param2.getWhich();
				if (var.var.getWhich() instanceof Python_Identifier_Reference)
				{
					Python_Identifier_Reference ref = (Python_Identifier_Reference) var.var.getWhich();
					interpreter._symbolTable.removeSymbols(ref.getValue());
				}
			}
		}
	}
}
