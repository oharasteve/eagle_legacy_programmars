// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Scala.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Scala_Expression = com.eagle.programmar.Scala.Scala_Expression;
	using Scala_Variable = com.eagle.programmar.Scala.Scala_Variable;
	using Scala_Function = com.eagle.programmar.Scala.Statements.Scala_Function;
	using Scala_FunctionParameter = com.eagle.programmar.Scala.Statements.Scala_Function.Scala_FunctionParameter;
	using Scala_Identifier_Reference = com.eagle.programmar.Scala.Symbols.Scala_Identifier_Reference;
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

	public class Scala_FunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Scala.Scala_Variable methodName;
		public Scala_Variable methodName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<com.eagle.programmar.Scala.Scala_Expression, com.eagle.tokens.punctuation.PunctuationComma> argList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			Scala_Identifier_Reference id = methodName.vars.first();
			string name = id.getValue();

			// See if it is a subscript reference first
			EagleValue symb = interpreter.findSymbol(name);
			if (symb != null)
			{
				// Look up the variable
				if (!symb.isArray())
				{
					throw new Exception("Can only use subscripts on arrays");
				}
				EagleArray array = (EagleArray) symb;
				int subscr = interpreter.getIntValue(argList.first());
				interpreter.pushEagleValue(array.getValue(subscr));
			}
			else
			{
				// Look up the function
				AbstractFunction fn = interpreter.findFunction(name);
				if (fn == null)
				{
					throw new Exception("Unable to find a function named " + name);
				}
				Scala_Function func = (Scala_Function) fn;

				// Make sure the function args match up
				int argCount = argList.getPrimaryCount();
				int paramCount = func.@params.parameters.getPrimaryCount();
				if (argCount != paramCount)
				{
					throw new Exception("Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
				}

				// Now assign all the parameters
				List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
				for (int i = 0; i < argCount; i++)
				{
					Scala_Expression expr = argList.getPrimaryElement(i);
					Scala_Function.Scala_FunctionParameter param = func.@params.parameters.getPrimaryElement(i);
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param, param.var.getValue(), val);
					argTypes.Add(val.getType());
				}

				// Prepare to evaluate the method
				long startTime = System.nanoTime();

				// And transfer control to the method
				interpreter.callingFunction(name, func);
				interpreter.tryToInterpret(func.stmt);

				// The result was already put on the runtime stack
				long elapsedTime = System.nanoTime() - startTime;
				func._callMetrics.addCallFrom(this, elapsedTime);
				func._argumentsMetrics.calledWith(argTypes);

				// Now remove all those parameters
				interpreter.completedFunction(name, func);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = methodName.vars.first().getValue();
			if (generator.isKnownMethod(name))
			{
				List<AbstractExpression> args = new List<AbstractExpression>();
				int argCount = argList.getPrimaryCount();
				for (int i = 0; i < argCount; i++)
				{
					Scala_Expression arg = argList.getPrimaryElement(i);
					AbstractExpression newArg = transformer.transformExpression(generator, arg);
					args.Add(newArg);
				}

				AbstractVariable var = generator.newVariable(name);
				return generator.newMethodInvocation(var, args, methodName);
			}

			// Dang. Scala uses () for both arrays and function calls
			// It is not a function, so must be an array
			AbstractExpression index = transformer.transformExpression(generator, argList.first());
			return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, index, this);
		}
	}

}
