// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

namespace com.eagle.programmar.Rexx.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using Rexx_Element = com.eagle.programmar.Rexx.Rexx_Element;
	using Rexx_Expression = com.eagle.programmar.Rexx.Rexx_Expression;
	using Rexx_Identifier_Reference = com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference;
	using Rexx_Variable_Definition = com.eagle.programmar.Rexx.Symbols.Rexx_Variable_Definition;
	using Rexx_Keyword = com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Rexx_CallStatement : TokenSequence, AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("instructions-call") com.eagle.programmar.Rexx.Terminals.Rexx_Keyword CALL = new com.eagle.programmar.Rexx.Terminals.Rexx_Keyword("CALL");
		public @DOC("instructions-call") Rexx_Keyword CALL = new Rexx_Keyword("CALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rexx.Symbols.Rexx_Identifier_Reference subName;
		public Rexx_Identifier_Reference subName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT SeparatedList<com.eagle.programmar.Rexx.Rexx_Expression, com.eagle.tokens.punctuation.PunctuationComma> arguments;
		public @OPT SeparatedList<Rexx_Expression, PunctuationComma> arguments;

		public void interpret(EagleInterpreter interpreter)
		{
			string name = subName.getValue();

			// Look up the subroutine
			Rexx_Function func = (Rexx_Function) interpreter.findFunction(name);
			if (func == null)
			{
				throw new Exception("Unable to find a function named " + name);
			}

			// Make sure the function args match up
			int argCount = 0;
			if (arguments != null)
			{
				argCount = arguments.getPrimaryCount();
			}
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
				Rexx_Expression expr = arguments.getPrimaryElement(i);
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

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			func._callMetrics.addCallFrom(this, elapsedTime);
			func._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(name, func);
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractExpression> args = new List<AbstractExpression>();
			int argCount = arguments.getPrimaryCount();
			for (int i = 0; i < argCount; i++)
			{
				Rexx_Expression arg = arguments.getPrimaryElement(i);
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.Add(newArg);
			}

			AbstractVariable var = generator.newVariable(subName.getValue());
			AbstractExpression expr = generator.newMethodInvocation(var, args, subName);
			return generator.newExpressionStatement(expr, subName);
		}
	}

}
