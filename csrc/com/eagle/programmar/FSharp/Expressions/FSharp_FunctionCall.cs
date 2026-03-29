// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.FSharp.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using FSharp_Element = com.eagle.programmar.FSharp.FSharp_Element;
	using FSharp_Expression = com.eagle.programmar.FSharp.FSharp_Expression;
	using FSharp_Variable = com.eagle.programmar.FSharp.FSharp_Variable;
	using FSharp_Function = com.eagle.programmar.FSharp.Statements.FSharp_Function;
	using FSharp_FunctionParam = com.eagle.programmar.FSharp.Statements.FSharp_Function.FSharp_FunctionParam;
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

	public class FSharp_FunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.FSharp.FSharp_Variable functionName;
		public FSharp_Variable functionName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<com.eagle.programmar.FSharp.FSharp_Expression, com.eagle.tokens.punctuation.PunctuationComma> argList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			// Look up the function in our function list
			string name = functionName.id.getValue();
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + name);
			}
			FSharp_Function func = (FSharp_Function) fn;

			// Make sure the function args match up
			int argCount = argList.getPrimaryCount();
			int paramCount = func.@params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			for (int i = 0; i < argCount; i++)
			{
				FSharp_Expression expr = argList.getPrimaryElement(i);
				FSharp_Function.FSharp_FunctionParam param = func.@params.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.var.getValue(), val);
				argTypes.Add(val.getType());
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			interpreter.callingFunction(name, func);
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (FSharp_Element stmt in func.statements._elements)
			{
				result = interpreter.tryToInterpret(stmt.statementOrComment);
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
			string name = functionName.id.getValue();
			List<AbstractExpression> args = new List<AbstractExpression>();

			if (argList != null && argList.size() > 0)
			{
				for (int i = 0; i < argList.getPrimaryCount(); i++)
				{
					FSharp_Expression expr = argList.getPrimaryElement(i);
					AbstractExpression newExpr = transformer.transformExpression(generator, expr);
					args.Add(newExpr);
				}
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, functionName.id);
		}
	}

}
