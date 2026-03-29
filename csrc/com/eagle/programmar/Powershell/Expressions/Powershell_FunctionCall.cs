// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Powershell.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using Powershell_Element = com.eagle.programmar.Powershell.Powershell_Element;
	using Powershell_Expression = com.eagle.programmar.Powershell.Powershell_Expression;
	using Powershell_Library = com.eagle.programmar.Powershell.Powershell_Library;
	using Powershell_Function = com.eagle.programmar.Powershell.Statements.Powershell_Function;
	using Powershell_FunctionParam = com.eagle.programmar.Powershell.Statements.Powershell_Function.Powershell_FunctionParam;
	using Powershell_Function_Reference = com.eagle.programmar.Powershell.Symbols.Powershell_Function_Reference;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Powershell_FunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Powershell_DiscardResult discardResult;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Powershell_Library library;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Symbols.Powershell_Function_Reference funcRef;
		public Powershell_Function_Reference funcRef;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<Powershell_FunctionArg> arguments;
		public  OPT;

		public class Powershell_DiscardResult : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword VOID = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("void");
			public Powershell_Keyword VOID = new Powershell_Keyword("void");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public class Powershell_FunctionArg : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Powershell_Expression expr;
			public Powershell_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationComma comma;
			public  OPT;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// Is it one of the defined Functions?
			AbstractFunction fn = interpreter.findFunction(funcRef.getValue());
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + funcRef.getValue());
			}
			Powershell_Function func = (Powershell_Function) fn;
			string name = func.id.getValue();

			// Call the function
			// Make sure the function args match up
			int argCount = arguments.size();
			int paramCount = func.@params.@params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, func);

			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			// Now assign all the parameters
			for (int i = 0; i < argCount; i++)
			{
				Powershell_Expression expr = arguments._elements.get(i).expr;
				Powershell_Function.Powershell_FunctionParam param = func.@params.@params.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.var.id.getValue(), val);
				argTypes.Add(val.getType());
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Powershell_Element stmt in func.stmts._elements)
			{
				result = interpreter.tryToInterpret(stmt.element);
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
			if (arguments != null && arguments.isPresent())
			{
				foreach (Powershell_FunctionArg arg in arguments._elements)
				{
					args.Add(transformer.transformExpression(generator, arg.expr));
				}
			}

			AbstractVariable var = generator.newVariable(funcRef.getValue());
			return generator.newMethodInvocation(var, args, this);
		}
	}

}
