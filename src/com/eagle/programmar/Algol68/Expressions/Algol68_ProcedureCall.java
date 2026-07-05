// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Statement;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Statements.Algol68_Procedure;
import com.eagle.programmar.Algol68.Statements.Algol68_Procedure.Algol68_Parameter;
import com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
import com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Algol68_ProcedureCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Algol68_Variable procName;
	public @S(20) @OPT Algol68_Punctuation question = new Algol68_Punctuation("?");
	public @S(30) Algol68_FunctionArguments argList;

	public static class Algol68_FunctionArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Algol68_FunctionArg, PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Algol68_FunctionArg extends TokenChooser
	{
		public @CHOICE Algol68_Expression XXexpr;

		public @CHOICE static class Algol68_FunctionSetArg extends TokenSequence
		{
			public @S(10) Algol68_Identifier_Reference id;
			public @S(20) Algol68_Punctuation arrow = new Algol68_Punctuation("=>");
			public @S(30) Algol68_Expression expr;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Algol68_Identifier_Reference id = procName.vars.first();
		String name = id.getValue();

		// Have to search for the PROC definition
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a procedure named " + name);
		}
		Algol68_Procedure proc = (Algol68_Procedure) fn;

		// Make sure the function args match up
		int argCount = argList.arguments.getPrimaryCount();
		int paramCount = proc.params.parameters.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Proc " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		for (int i = 0; i < argCount; i++)
		{
			Algol68_FunctionArg arg = argList.arguments.getPrimaryElement(i);
			Algol68_Parameter param = proc.params.parameters.getPrimaryElement(i);
			AbstractToken which = arg.getWhich();
			if (which instanceof Algol68_Expression)
			{
				Algol68_Expression expr = (Algol68_Expression) which;
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.param.getValue(), val);
				argTypes.add(val.getType());
			}
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function / procedure
		interpreter.callingFunction(name, proc);
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Algol68_Statement stmt : proc.statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		proc._callMetrics.addCallFrom(this, elapsedTime);
		proc._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		interpreter.completedFunction(name, proc);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		Algol68_Identifier_Reference id = procName.vars.first();
		String name = id.getValue();

		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);
		int argCount = argList.arguments.getPrimaryCount();
		for (int i = 0; i < argCount; i++)
		{
			Algol68_FunctionArg arg = argList.arguments.getPrimaryElement(i);
			if (!(arg.getWhich() instanceof Algol68_Expression))
			{
				throw new RuntimeException("Unable to handle: " + arg);
			}
			Algol68_Expression expr = (Algol68_Expression) arg.getWhich();
			AbstractExpression newArg = transformer.transformExpression(generator, expr);
			args.add(newArg);
		}

		AbstractVariable var = generator.newVariable(name);
		return generator.newMethodInvocation(var, args, types, procName);
	}
}
