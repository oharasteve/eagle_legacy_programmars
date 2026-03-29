// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

namespace com.eagle.programmar.Eaglish.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using Eaglish_Expression = com.eagle.programmar.Eaglish.Eaglish_Expression;
	using Eaglish_Statement = com.eagle.programmar.Eaglish.Eaglish_Statement;
	using Eaglish_Function = com.eagle.programmar.Eaglish.Statements.Eaglish_Function;
	using Eaglish_Parameter_Statement = com.eagle.programmar.Eaglish.Statements.Eaglish_Parameter_Statement;
	using Eaglish_Identifier_Reference = com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
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
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Eaglish_FunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference fnName;
		public Eaglish_Identifier_Reference fnName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<com.eagle.programmar.Eaglish.Eaglish_Expression, com.eagle.tokens.punctuation.PunctuationComma> arguments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			// Have to search for the FUNCTION definition
			string name = fnName.getValue();
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + name);
			}
			Eaglish_Function func = (Eaglish_Function) fn;

			// Make sure the function args match up
			if (!func.returnsStatement.isPresent())
			{
				throw new Exception("Function " + name + " doesn't return any value");
			}
			int argCount = arguments.getPrimaryCount();
			int paramCount = func.parameterStatements.size();
			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			for (int i = 0; i < argCount; i++)
			{
				Eaglish_Expression arg = arguments.getPrimaryElement(i);
				Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
				EagleValue val = interpreter.getEagleValue(arg);
				interpreter.setSymbol(param, param.param.getValue(), val);
				argTypes.Add(val.getType());
			}

			// Prepare to evaluate the function
			long startTime = System.nanoTime();

			// And transfer control to the function
			interpreter.callingFunction(name, func);
			foreach (Eaglish_Statement stmt in func.statements._elements)
			{
				Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
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
			List<AbstractExpression> args = new List<AbstractExpression>();
			int argCount = arguments.getPrimaryCount();
			for (int i = 0; i < argCount; i++)
			{
				Eaglish_Expression arg = arguments.getPrimaryElement(i);
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.Add(newArg);
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, this);
		}
	}

}
