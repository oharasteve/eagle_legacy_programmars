// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.AWK.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using AWK_StatementOrComment = com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
	using AWK_ArgumentList = com.eagle.programmar.AWK.AWK_ArgumentList;
	using AWK_MoreArguments = com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
	using AWK_Expression = com.eagle.programmar.AWK.AWK_Expression;
	using AWK_Function = com.eagle.programmar.AWK.AWK_Function;
	using AWK_Identifier_Reference = com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference;
	using AWK_Parameter_Definition = com.eagle.programmar.AWK.Symbols.AWK_Parameter_Definition;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_CallFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.Symbols.AWK_Identifier_Reference functionName;
		public AWK_Identifier_Reference functionName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT AWK_ArgumentList argList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			// Have to search for the FUNCTION definition
			AbstractFunction fn = interpreter.findFunction(functionName.getValue());
			string name = functionName.getValue();
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + name);
			}
			AWK_Function func = (AWK_Function) fn;

			// Doesn't do much, just set metrics
			interpreter.tryToInterpret(func);

			// Make sure the function args match up
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			int argCount = 0;
			if (argList != null && argList.isPresent())
			{
				argCount = 1;
				if (argList.more != null)
				{
					argCount += argList.more.size();
				}
				int paramCount = func.parameters.@params.getPrimaryCount();
				if (argCount != paramCount)
				{
					throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
				}

				interpreter.callingFunction(name, func);

				// Now assign all the parameters
				AWK_Expression arg = argList.expr;
				for (int i = 0; i < argCount; i++)
				{
					AWK_Parameter_Definition param = func.parameters.@params.getPrimaryElement(i);
					EagleValue val = interpreter.getEagleValue(arg);
					interpreter.setSymbol(param, param.getValue(), val);
					argTypes.Add(val.getType());
					if (i < argCount - 1)
					{
						arg = argList.more._elements.get(i).expr;
					}
				}
			}

			// Prepare to evaluate the function
			long startTime = System.nanoTime();

			// And transfer control to the function
			foreach (AWK_StatementOrComment stmt in func.body.elements._elements)
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
			List<AbstractExpression> args = new List<AbstractExpression>();
			if (argList != null && argList.isPresent())
			{
				args.Add(transformer.transformExpression(generator, argList.expr));
				foreach (AWK_ArgumentList.AWK_MoreArguments more in argList.more._elements)
				{
					args.Add(transformer.transformExpression(generator, more.expr));
				}
			}

			AbstractVariable var = generator.newVariable(functionName.getValue());
			return generator.newMethodInvocation(var, args, this);
		}
	}

}
