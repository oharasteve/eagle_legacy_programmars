// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Fortran.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using Fortran_Expression = com.eagle.programmar.Fortran.Fortran_Expression;
	using Fortran_Statement = com.eagle.programmar.Fortran.Fortran_Statement;
	using Fortran_Function = com.eagle.programmar.Fortran.Statements.Fortran_Function;
	using Fortran_Identifier_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Identifier_Reference;
	using Fortran_Variable_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
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

	public class Fortran_FunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Fortran.Symbols.Fortran_Identifier_Reference variable;
		public Fortran_Identifier_Reference variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<com.eagle.programmar.Fortran.Fortran_Expression, com.eagle.tokens.punctuation.PunctuationComma> argList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			string fnName = variable.getValue().ToUpper();
			int argCount = argList.getPrimaryCount();

			// Check for subscripts
			EagleValue var = interpreter.findSymbol(fnName);
			if (var != null && var.isArray() && argCount == 1)
			{
				EagleArray array = (EagleArray) var;
				int subscr = interpreter.getIntValue(argList.getPrimaryElement(0));
				EagleValue val = array.getValue(subscr - 1);
				interpreter.pushEagleValue(val);
				return;
			}

			// Check for user functions
			AbstractFunction fn = interpreter.findFunction(fnName);
			if (fn == null || !(fn is Fortran_Function))
			{
				throw new Exception("Unable to find a function named " + fnName);
			}
			Fortran_Function func = (Fortran_Function) fn;

			// Make sure the function args match up
			int paramCount = func.parameters.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new Exception("Function " + fnName + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			for (int i = 0; i < argCount; i++)
			{
				Fortran_Expression expr = argList.getPrimaryElement(i);
				Fortran_Variable_Reference param = func.parameters.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.getValue(), val);
				argTypes.Add(val.getType());
			}

			// Prepare to evaluate the procedure or function
			long startTime = System.nanoTime();

			// And transfer control to the procedure or function
			interpreter.callingFunction(fnName, func);
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Fortran_Statement stmt in func.statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}

			// Need to put the result on the runtime stack
			// Fortran uses the function name for the return value
			// Sort-of like this: function sqrt(x) { sqrt = x*x }
			EagleValue val = interpreter.findSymbol(fnName);
			if (val != null)
			{
				interpreter.pushEagleValue(val);
			}

			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(fnName, func);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = variable.getValue();
			List<AbstractExpression> args = new List<AbstractExpression>();
			if (argList != null && argList.isPresent())
			{
				for (int i = 0; i < argList.getPrimaryCount(); i++)
				{
					Fortran_Expression expr = argList.getPrimaryElement(i);
					AbstractExpression newExpr = transformer.transformExpression(generator, expr);
					args.Add(newExpr);
				}
			}

			// Check for subscripts
			if (!generator.isKnownMethod(name) && args.Count == 1)
			{
				return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, args[0], argList);
			}

			// Must be a function call
			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, this);
		}
	}
}
