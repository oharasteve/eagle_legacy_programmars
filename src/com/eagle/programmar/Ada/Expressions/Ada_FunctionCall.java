// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Ada.Expressions;

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
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Ada_FunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
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

		public @FIRST static class Ada_FunctionSetArg extends TokenSequence
		{
			public @S(10) Ada_Identifier_Reference id;
			public @S(20) Ada_Punctuation arrow = new Ada_Punctuation("=>");
			public @S(30) Ada_Expression expr;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Ada_Identifier_Reference id = functionName.vars.first();
		String name = id.getValue();

		// Have to search for the FUNCTION definition
		AbstractFunction fn = interpreter.findFunction(name);

		Ada_FunctionParams params;
		TokenList<Ada_Statement> stmts1;
		TokenList<Ada_Statement> statements;
		CallMetrics callMetrics;
		ArgumentsMetrics argumentsMetrics;

		Ada_Function func = null;
		Ada_Procedure proc = null;
		if (fn instanceof Ada_Function)
		{
			func = (Ada_Function) fn;
			params = func.funcParamDefs;
			callMetrics = func._callMetrics;
			argumentsMetrics = func._argumentsMetrics;
			stmts1 = func.statements1;
			statements = func.statements2;
			interpreter.tryToInterpret(func); // Doesn't do much, just set metrics
		}
		else if (fn instanceof Ada_Procedure)
		{
			proc = (Ada_Procedure) fn;
			params = proc.procParamDefs;
			callMetrics = proc._callMetrics;
			argumentsMetrics = proc._argumentsMetrics;
			stmts1 = proc.statements1;
			statements = proc.statements2;
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

		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		if (argCount > 0)
		{
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
					argTypes.add(val.getType());
				}
			}
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
			for (Ada_Statement stmt : statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		callMetrics.addCallFrom(this, elapsedTime);
		argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		if (func != null) interpreter.completedFunction(name, func);
		if (proc != null) interpreter.completedFunction(name, proc);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Ada_Identifier_Reference id = functionName.vars.first();
		String name = id.getValue();
		int argCount = argList.arguments.getPrimaryCount();

		// Have to search for the FUNCTION definition
		boolean anyCalls = transformer.findCallTo(name);
		if (anyCalls || argCount == 0 || argCount > 1)
		{
			// There is a function or procedure with this name
			ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
			ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);

			for (int i = 0; i < argCount; i++)
			{
				Ada_FunctionArg fnArg = argList.arguments.getPrimaryElement(i);
				AbstractToken which = fnArg.getWhich();
				if (which instanceof Ada_Expression)
				{
					Ada_Expression expr = (Ada_Expression) which;
					AbstractExpression newArg = transformer.transformExpression(generator, expr);
					args.add(newArg);
				}
				else
				{
					throw new RuntimeException("Unable to handle arg: " + which);
				}
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, types, functionName);
		}

		// Hopefully, it is an array with a subscript
		if (argCount == 1)
		{
			Ada_FunctionArg arg = argList.arguments.first();
			if (arg.getWhich() instanceof Ada_Expression)
			{
				AbstractExpression subscr = transformer.transformExpression(
						generator, (Ada_Expression) arg.getWhich());
				return generator.newVariableExpression(name,
						SubscriptEnum.FIRST_IS_ONE, subscr, this);
			}
		}

		throw new RuntimeException("Unable to handle " + name);
	}
}
