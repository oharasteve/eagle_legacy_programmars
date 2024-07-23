// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.Statements.VB_FunctionDeclaration;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Symbols.VB_Variable_Definition;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class VB_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) VB_Identifier_Reference fnName;
	public @S(20) VB_FnCallArguments callArguments;

	public static class VB_FnCallArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<VB_Expression, PunctuationComma> args;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = fnName.getValue();
		
		// See if it is a subscripted variable first
		EagleValue value = interpreter._symbolTable.findSymbol(name);
		if (value != null && value.isArray())
		{
			EagleArray array = (EagleArray) value;
			int index = interpreter.getIntValue(callArguments.args.first());
			interpreter.pushEagleValue(array.getValue(index));
			return;
		}
		
		// See if it is a predefined function, like LEN()
		if (name.equalsIgnoreCase("LEN"))
		{
			String str = interpreter.getStrValue(callArguments.args.first());
			interpreter.pushInt(str.length());
			return;
		}
		if (name.equalsIgnoreCase("MID"))
		{
			String str = interpreter.getStrValue(callArguments.args.getPrimaryElement(0));
			int sc = interpreter.getIntValue(callArguments.args.getPrimaryElement(1)) - 1;
			int nc = interpreter.getIntValue(callArguments.args.getPrimaryElement(2));
			int len = str.length();
			if (sc + nc > len) nc = len - sc;	// Don't go past the end of the string
			interpreter.pushStr(str.substring(sc, sc + nc));
			return;
		}
		
		// Look up the function
		VB_FunctionDeclaration func = null;
		for (AbstractFunction token : interpreter._functionList)
		{
			if (token instanceof VB_FunctionDeclaration)
			{
				VB_FunctionDeclaration fn = (VB_FunctionDeclaration) token;
				if (fn.name.getValue().equals(name))
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
		int argCount = callArguments.args.getPrimaryCount();
		int paramCount = func.params.params.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			VB_Expression expr = callArguments.args.getPrimaryElement(i);
			VB_Variable_Definition param = func.params.params.getPrimaryElement(i).var;

			EagleValue val = interpreter.getEagleValue(expr);
			interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
					param.getValue(), val);
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (VB_Statement stmt : func.stmts._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break; 
		}
		
		// Need to put the result on the runtime stack
		// VB uses the function name for the return value
		// Sort-of like this: function sqrt(x) { sqrt = x*x }
		EagleValue val = interpreter._symbolTable.findSymbol(name);
		if (val != null)
		{
			interpreter.pushEagleValue(val);
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Now remove all those parameters
		for (int i = 0; i < argCount; i++)
		{
			VB_Variable_Definition param = func.params.params.getPrimaryElement(i).var;
			interpreter._symbolTable.removeSymbols(param.getValue());
		}
	}
}
