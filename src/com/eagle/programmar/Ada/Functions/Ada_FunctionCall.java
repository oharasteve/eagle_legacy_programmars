// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Ada.Ada_Expression;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Ada_Variable;
import com.eagle.programmar.Ada.Statements.Ada_Function;
import com.eagle.programmar.Ada.Statements.Ada_Function.Ada_FunctionParams;
import com.eagle.programmar.Ada.Statements.Ada_Function.Ada_Parameter;
import com.eagle.programmar.Ada.Statements.Ada_Procedure;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Ada_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Ada_Variable functionName;
	public @S(20) @OPT Ada_Punctuation question = new Ada_Punctuation("?");
	public @S(30) Ada_FunctionArguments argList;

	public static class Ada_FunctionArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Ada_FunctionArg, PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Ada_FunctionArg extends TokenChooser
	{
		public @CHOICE Ada_Expression XXexpr;

		public @CHOICE static class Ada_FunctionSetArg extends TokenSequence
		{
			public @S(10) Ada_Identifier_Reference id;
			public @S(20) Ada_Punctuation arrow = new Ada_Punctuation("=>");
			public @S(30) Ada_Expression expr;
		}
	}

	private @SKIP ArgumentsMetrics _metrics = null;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ada_Identifier_Reference id = functionName.vars.first();
		String name = id.getValue();

		// Have to search for the FUNCTION definition
		AbstractFunction fn = interpreter.findFunction(id.getValue());

		Ada_FunctionParams params;
		TokenList<Ada_Statement> stmts1;
		TokenList<Ada_Statement> stmts2;
		CallMetrics metrics;

		Ada_Function func = null;
		Ada_Procedure proc = null;
		if (fn instanceof Ada_Function)
		{
			func = (Ada_Function) fn;
			params = func.params;
			metrics = func._metrics;
			stmts1 = func.stmts1;
			stmts2 = func.stmts2;
			interpreter.tryToInterpret(func); // Doesn't do much, just set metrics
		}
		else if (fn instanceof Ada_Procedure)
		{
			proc = (Ada_Procedure) fn;
			params = proc.params;
			metrics = proc._metrics;
			stmts1 = proc.stmts1;
			stmts2 = proc.stmts2;
			interpreter.tryToInterpret(proc); // Doesn't do much, just set metrics
		}
		else
		{
			EagleValue val = interpreter.findSymbol(name);
			if (val != null && val.isArray())
			{
				EagleArray array = (EagleArray) val;
				Ada_FunctionArg arg = argList.arguments.getPrimaryElement(0);
				AbstractToken which = arg.getWhich();
				if (which instanceof Ada_Expression)
				{
					Ada_Expression expr = (Ada_Expression) which;
					int subscr = interpreter.getIntValue(expr);
					interpreter.pushEagleValue(array.getValue(subscr - 1));
					return;
				}
			}

			throw new RuntimeException("Unable to find a Function, Procedure or Array named " + name);
		}

		// Make sure the function args match up
		int argCount = argList.arguments.getPrimaryCount();
		int paramCount = params.parameters.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		if (argCount > 0)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, this, name);
			}
			ArrayList<String> argTypes = new ArrayList<String>();
	
			// Now assign all the parameters
			for (int i = 0; i < argCount; i++)
			{
				Ada_FunctionArg arg = argList.arguments.getPrimaryElement(i);
				Ada_Parameter param = params.parameters.getPrimaryElement(i);
				AbstractToken which = arg.getWhich();
				if (which instanceof Ada_Expression)
				{
					Ada_Expression expr = (Ada_Expression) which;
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param, param.param.getValue(), val);
					argTypes.add(val.typeName());
				}
			}
			_metrics.called(argTypes);
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function / procedure
		if (func != null) interpreter.callingFunction(name, func);
		if (proc != null) interpreter.callingFunction(name, proc);
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Ada_Statement stmt : stmts1._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}
		if (result == Eagle_Statement_Result.NORMAL)
		{
			for (Ada_Statement stmt : stmts2._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		metrics.addCallFrom(this, elapsedTime);

		// Now remove all those parameters
		if (func != null) interpreter.completedFunction(name, func);
		if (proc != null) interpreter.completedFunction(name, proc);
	}
}
