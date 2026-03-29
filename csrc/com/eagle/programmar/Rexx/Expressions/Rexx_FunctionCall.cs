// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Rexx_Element = com.eagle.programmar.Rexx.Rexx_Element;
	using Rexx_Expression = com.eagle.programmar.Rexx.Rexx_Expression;
	using Rexx_Function = com.eagle.programmar.Rexx.Statements.Rexx_Function;
	using Rexx_Identifier_Reference = com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference;
	using Rexx_Variable_Definition = com.eagle.programmar.Rexx.Symbols.Rexx_Variable_Definition;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rexx_FunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference fnName;
		public Rexx_Identifier_Reference fnName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Rexx_FnCallArguments callArguments;
		public Rexx_FnCallArguments callArguments;

		public class Rexx_FnCallArguments : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<com.eagle.programmar.Rexx.Rexx_Expression, com.eagle.tokens.punctuation.PunctuationComma> arguments;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			string name = fnName.getValue();

			// See if it is a subscripted variable first
			EagleValue value = interpreter.findSymbol(name);
			if (value != null && value.isArray())
			{
				EagleArray array = (EagleArray) value;
				int index = interpreter.getIntValue(callArguments.arguments.first());
				interpreter.pushEagleValue(array.getValue(index));
				return;
			}

			// Look up the function
			Rexx_Function func = (Rexx_Function) interpreter.findFunction(name);
			if (func == null)
			{
				throw new Exception("Unable to find a function named " + name);
			}

			// Make sure the function args match up
			int argCount = callArguments.arguments.getPrimaryCount();
			int paramCount = func.@params.@params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, func);

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			for (int i = 0; i < argCount; i++)
			{
				Rexx_Expression expr = callArguments.arguments.getPrimaryElement(i);
				Rexx_Variable_Definition param = func.@params.@params.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.getValue(), val);
				argTypes.Add(val.getType());
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Rexx_Element stmt in func.stmts._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}

			// Need to put the result on the runtime stack
			// Rexx uses the function name for the return value
			// Sort-of like this: Function sqrt(x) ; sqrt = x*x ; End Function
			EagleValue val = interpreter.findSymbol(name);
			if (val != null)
			{
				interpreter.pushEagleValue(val);
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(name, func);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = fnName.getValue();
			if (generator.isKnownMethod(name))
			{
				List<AbstractExpression> args = new List<AbstractExpression>();
				int argCount = callArguments.arguments.getPrimaryCount();
				for (int i = 0; i < argCount; i++)
				{
					Rexx_Expression arg = callArguments.arguments.getPrimaryElement(i);
					AbstractExpression newArg = transformer.transformExpression(generator, arg);
					args.Add(newArg);
				}

				AbstractVariable var = generator.newVariable(name);
				return generator.newMethodInvocation(var, args, fnName);
			}

			// Dang. Rexx uses () for both arrays and function calls
			// It is not a function, so must be an array
			AbstractExpression index = transformer.transformExpression(generator, callArguments.arguments.first());
			return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, index, this);
		}
	}

}
