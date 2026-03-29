// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Algol68.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using Algol68_Expression = com.eagle.programmar.Algol68.Algol68_Expression;
	using Algol68_Statement = com.eagle.programmar.Algol68.Algol68_Statement;
	using Algol68_Variable = com.eagle.programmar.Algol68.Algol68_Variable;
	using Algol68_Procedure = com.eagle.programmar.Algol68.Statements.Algol68_Procedure;
	using Algol68_Parameter = com.eagle.programmar.Algol68.Statements.Algol68_Procedure.Algol68_Parameter;
	using Algol68_Identifier_Reference = com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
	using Algol68_Punctuation = com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
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

	public class Algol68_ProcedureCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Algol68_Variable procName;
		public Algol68_Variable procName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Algol68_Punctuation question = new com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation("?");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Algol68_FunctionArguments argList;
		public Algol68_FunctionArguments argList;

		public class Algol68_FunctionArguments : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Algol68_FunctionArg, com.eagle.tokens.punctuation.PunctuationComma> arguments;
			public SeparatedList<Algol68_FunctionArg, PunctuationComma> arguments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public class Algol68_FunctionArg : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Algol68_Expression XXexpr;
			public Algol68_Expression XXexpr;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Algol68_FunctionSetArg extends com.eagle.tokens.TokenSequence
			public class Algol68_FunctionSetArg : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference id;
				public Algol68_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation arrow = new com.eagle.programmar.Algol68.Terminals.Algol68_Punctuation("=>");
				public Algol68_Punctuation arrow = new Algol68_Punctuation("=>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Algol68.Algol68_Expression expr;
				public Algol68_Expression expr;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			Algol68_Identifier_Reference id = procName.vars.first();
			string name = id.getValue();

			// Have to search for the PROC definition
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a procedure named " + name);
			}
			Algol68_Procedure proc = (Algol68_Procedure) fn;

			// Make sure the function args match up
			int argCount = argList.arguments.getPrimaryCount();
			int paramCount = proc.@params.parameters.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new Exception("Proc " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			for (int i = 0; i < argCount; i++)
			{
				Algol68_FunctionArg arg = argList.arguments.getPrimaryElement(i);
				Algol68_Procedure.Algol68_Parameter param = proc.@params.parameters.getPrimaryElement(i);
				AbstractToken which = arg.getWhich();
				if (which is Algol68_Expression)
				{
					Algol68_Expression expr = (Algol68_Expression) which;
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param, param.param.getValue(), val);
					argTypes.Add(val.getType());
				}
			}

			// Prepare to evaluate the function
			long startTime = System.nanoTime();

			// And transfer control to the function / procedure
			interpreter.callingFunction(name, proc);
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Algol68_Statement stmt in proc.statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			proc._callMetrics.addCallFrom(this, elapsedTime);
			proc._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(name, proc);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractExpression> args = new List<AbstractExpression>();
			int argCount = argList.arguments.getPrimaryCount();
			for (int i = 0; i < argCount; i++)
			{
				Algol68_FunctionArg arg = argList.arguments.getPrimaryElement(i);
				if (!(arg.getWhich() is Algol68_Expression))
				{
					throw new Exception("Unable to handle: " + arg);
				}
				Algol68_Expression expr = (Algol68_Expression) arg.getWhich();
				AbstractExpression newArg = transformer.transformExpression(generator, expr);
				args.Add(newArg);
			}

			Algol68_Identifier_Reference id = procName.vars.first();
			string name = id.getValue();
			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, procName);
		}
	}

}
