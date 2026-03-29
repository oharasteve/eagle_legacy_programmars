// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

namespace com.eagle.programmar.Go.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Go_Expression = com.eagle.programmar.Go.Go_Expression;
	using Go_Variable = com.eagle.programmar.Go.Go_Variable;
	using Go_Function = com.eagle.programmar.Go.Statements.Go_Function;
	using Go_FunctionParameter = com.eagle.programmar.Go.Statements.Go_Function.Go_FunctionParameter;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
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

	public class Go_FunctionCall : PrimaryOperator, EagleRunnable, AbstractStatement, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Go.Go_Variable funcName;
		public Go_Variable funcName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<com.eagle.programmar.Go.Go_Expression, com.eagle.tokens.punctuation.PunctuationComma> arguments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			string fnName = funcName.vars.first().getValue();

			AbstractFunction fn = interpreter.findFunction(fnName);
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + fnName);
			}
			Go_Function func = (Go_Function) fn;

			// Make sure the function args match up
			int argCount = arguments.getPrimaryCount();

			int paramCount = 0;
			if (func.funcParamDefs != null && func.funcParamDefs.isPresent())
			{
				paramCount = func.funcParamDefs.getPrimaryCount();
			}

			if (argCount != paramCount)
			{
				throw new Exception("Function " + fnName + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(fnName, func);

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			for (int i = 0; i < argCount; i++)
			{
				Go_Expression expr = arguments.getPrimaryElement(i);
				Go_Function.Go_FunctionParameter param = func.funcParamDefs.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.var.getValue(), val);
				argTypes.Add(val.getType());
			}

			// Prepare to evaluate the function
			long startTime = System.nanoTime();

			// And transfer control to the function
			interpreter.tryToInterpret(func.stmt);

			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(fnName, func);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = funcName.vars.first().getValue();
			if (generator.isKnownMethod(name))
			{
				List<AbstractExpression> args = new List<AbstractExpression>();
				int argCount = arguments.getPrimaryCount();
				for (int i = 0; i < argCount; i++)
				{
					Go_Expression arg = arguments.getPrimaryElement(i);
					AbstractExpression newArg = transformer.transformExpression(generator, arg);
					args.Add(newArg);
				}

				AbstractVariable var = generator.newVariable(name);
				return generator.newMethodInvocation(var, args, funcName);
			}

			// Dang. Go uses () for both arrays and function calls
			// It is not a function, so must be an array
			AbstractExpression index = transformer.transformExpression(generator, arguments.first());
			return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, index, this);
		}
	}

}
