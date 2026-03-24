// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Function;
import com.eagle.programmar.Perl.Perl_Function.Perl_FunctionTypeAndVariable;
import com.eagle.programmar.Perl.Perl_Function.Perl_FunctionVariable;
import com.eagle.programmar.Perl.Perl_Function.Perl_FunctionVariableOrTypeVariable;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
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
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Perl_FunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Perl_Identifier_Reference fnName;
	public @S(20) @OPT TokenList<Perl_MoreFunctionName> moreName;
	public @S(30) @OPT TokenList<Perl_Method> perlMethods;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT Perl_Punctuation at = new Perl_Punctuation('@');
	public @S(60) @OPT Perl_Expression argument;
	public @S(70) @OPT TokenList<Perl_MoreFnArguments> moreArgs;
	public @S(80) PunctuationRightParen rightParen;

	public static class Perl_MoreFunctionName extends TokenSequence
	{
		public @S(10) Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public @S(20) Perl_Identifier_Reference fnName;
	}

	public static class Perl_Method extends TokenSequence
	{
		public @S(10) Perl_Punctuation colonColon = new Perl_Punctuation("::");
		public @S(20) Perl_Identifier_Reference fnName;
	}

	public static class Perl_MoreFnArguments extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT Perl_Comment comment;
		public @S(30) @OPT Perl_Punctuation at = new Perl_Punctuation('@');
		public @S(40) Perl_Expression argument;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = fnName.getValue();

		// Look up the function
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		Perl_Function func = (Perl_Function) fn;

		// Make sure the function args match up
		int argCount = 0;
		if (argument != null && argument.isPresent()) argCount++;
		if (moreArgs != null && moreArgs.isPresent()) argCount += moreArgs.size();
		int paramCount = 0;
		if (func.params.parameters != null && func.params.parameters.isPresent())
		{
			paramCount = func.params.parameters.getPrimaryCount();
		}

		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		ArrayList<String> argTypes = new ArrayList<String>();
		Perl_Expression arg = argument;
		for (int i = 0; i < argCount; i++)
		{
			if (i > 0)
			{
				arg = moreArgs._elements.get(i - 1).argument;
			}
			Perl_FunctionVariableOrTypeVariable param = func.params.parameters.getPrimaryElement(i);
			Perl_FunctionVariable fnVar;
			if (param.getWhich() instanceof Perl_FunctionVariable)
			{
				fnVar = (Perl_FunctionVariable) param.getWhich();
			}
			else
			{
				Perl_FunctionTypeAndVariable typedVar = (Perl_FunctionTypeAndVariable) param.getWhich();
				fnVar = typedVar.var;
			}
			EagleValue val = interpreter.getEagleValue(arg);
			interpreter.setSymbol(param, fnVar.param.getValue(), val);
			argTypes.add(val.typeName());
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		interpreter.callingFunction(name, func);
		interpreter.tryToInterpret(func.block);

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		interpreter.completedFunction(name, func);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String name = fnName.getValue();
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		int argCount = 0;
		if (argument != null && argument.isPresent()) argCount++;
		if (moreArgs != null && moreArgs.isPresent()) argCount += moreArgs.size();
		for (int i = 0; i < argCount; i++)
		{
			Perl_Expression arg;
			if (i == 0)
			{
				arg = argument;
			}
			else
			{
				arg = moreArgs._elements.get(i - 1).argument;
			}
			AbstractExpression newArg = transformer.transformExpression(generator, arg);
			args.add(newArg);
		}

		AbstractVariable var = generator.newVariable(name);
		return generator.newMethodInvocation(var, args, fnName);
	}
}
