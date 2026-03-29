// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Ada.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using Ada_Expression = com.eagle.programmar.Ada.Ada_Expression;
	using Ada_Statement = com.eagle.programmar.Ada.Ada_Statement;
	using Ada_Variable = com.eagle.programmar.Ada.Ada_Variable;
	using Ada_Function = com.eagle.programmar.Ada.Statements.Ada_Function;
	using Ada_FunctionParams = com.eagle.programmar.Ada.Statements.Ada_Function.Ada_FunctionParams;
	using Ada_Parameter = com.eagle.programmar.Ada.Statements.Ada_Function.Ada_Parameter;
	using Ada_Procedure = com.eagle.programmar.Ada.Statements.Ada_Procedure;
	using Ada_Identifier_Reference = com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
	using Ada_Punctuation = com.eagle.programmar.Ada.Terminals.Ada_Punctuation;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
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

	public class Ada_FunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Ada_Variable functionName;
		public Ada_Variable functionName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Ada_Punctuation question = new com.eagle.programmar.Ada.Terminals.Ada_Punctuation("?");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Ada_FunctionArguments argList;
		public Ada_FunctionArguments argList;

		public class Ada_FunctionArguments : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Ada_FunctionArg, com.eagle.tokens.punctuation.PunctuationComma> arguments;
			public SeparatedList<Ada_FunctionArg, PunctuationComma> arguments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public class Ada_FunctionArg : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Ada_Expression XXexpr;
			public Ada_Expression XXexpr;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class Ada_FunctionSetArg extends com.eagle.tokens.TokenSequence
			public class Ada_FunctionSetArg : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference id;
				public Ada_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Terminals.Ada_Punctuation arrow = new com.eagle.programmar.Ada.Terminals.Ada_Punctuation("=>");
				public Ada_Punctuation arrow = new Ada_Punctuation("=>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Ada_Expression expr;
				public Ada_Expression expr;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			Ada_Identifier_Reference id = functionName.vars.first();
			string name = id.getValue();

			// Have to search for the FUNCTION definition
			AbstractFunction fn = interpreter.findFunction(name);

			Ada_Function.Ada_FunctionParams @params;
			TokenList<Ada_Statement> stmts1;
			TokenList<Ada_Statement> statements;
			CallMetrics callMetrics;
			ArgumentsMetrics argumentsMetrics;

			Ada_Function func = null;
			Ada_Procedure proc = null;
			if (fn is Ada_Function)
			{
				func = (Ada_Function) fn;
				@params = func.funcParamDefs;
				callMetrics = func._callMetrics;
				argumentsMetrics = func._argumentsMetrics;
				stmts1 = func.statements1;
				statements = func.statements2;
				interpreter.tryToInterpret(func); // Doesn't do much, just set metrics
			}
			else if (fn is Ada_Procedure)
			{
				proc = (Ada_Procedure) fn;
				@params = proc.procParamDefs;
				callMetrics = proc._callMetrics;
				argumentsMetrics = proc._argumentsMetrics;
				stmts1 = proc.statements1;
				statements = proc.statements2;
				interpreter.tryToInterpret(proc); // Doesn't do much, just set metrics
			}
			else
			{
				EagleValue val = interpreter.findSymbol(name);
				if (val != null && val.isArray())
				{
					EagleArray array = (EagleArray) val;
					Ada_FunctionArg arg = argList.arguments.getPrimaryElement(0);
					AbstractToken which = arg.getWhich();
					if (which is Ada_Expression)
					{
						Ada_Expression expr = (Ada_Expression) which;
						int subscr = interpreter.getIntValue(expr);
						interpreter.pushEagleValue(array.getValue(subscr - 1));
						return;
					}
				}

				throw new Exception("Unable to find a Function, Procedure or Array named " + name);
			}

			// Make sure the function args match up
			int argCount = argList.arguments.getPrimaryCount();
			int paramCount = @params.parameters.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			if (argCount > 0)
			{
				// Now assign all the parameters
				for (int i = 0; i < argCount; i++)
				{
					Ada_FunctionArg arg = argList.arguments.getPrimaryElement(i);
					Ada_Function.Ada_Parameter param = @params.parameters.getPrimaryElement(i);
					AbstractToken which = arg.getWhich();
					if (which is Ada_Expression)
					{
						Ada_Expression expr = (Ada_Expression) which;
						EagleValue val = interpreter.getEagleValue(expr);
						interpreter.setSymbol(param, param.param.getValue(), val);
						argTypes.Add(val.getType());
					}
				}
			}

			// Prepare to evaluate the function
			long startTime = System.nanoTime();

			// And transfer control to the function / procedure
			if (func != null)
			{
				interpreter.callingFunction(name, func);
			}
			if (proc != null)
			{
				interpreter.callingFunction(name, proc);
			}
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Ada_Statement stmt in stmts1._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}
			if (result == Eagle_Statement_Result.NORMAL)
			{
				foreach (Ada_Statement stmt in statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			callMetrics.addCallFrom(this, elapsedTime);
			argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			if (func != null)
			{
				interpreter.completedFunction(name, func);
			}
			if (proc != null)
			{
				interpreter.completedFunction(name, proc);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Ada_Identifier_Reference id = functionName.vars.first();
			string name = id.getValue();
			int argCount = argList.arguments.getPrimaryCount();

			// Have to search for the FUNCTION definition
			bool anyCalls = transformer.findCallTo(name);
			if (anyCalls || argCount == 0 || argCount > 1)
			{
				// There is a function or procedure with this name
				List<AbstractExpression> args = new List<AbstractExpression>();
				for (int i = 0; i < argCount; i++)
				{
					Ada_FunctionArg fnArg = argList.arguments.getPrimaryElement(i);
					AbstractToken which = fnArg.getWhich();
					if (which is Ada_Expression)
					{
						Ada_Expression expr = (Ada_Expression) which;
						AbstractExpression newArg = transformer.transformExpression(generator, expr);
						args.Add(newArg);
					}
					else
					{
						throw new Exception("Unable to handle arg: " + which);
					}
				}

				AbstractVariable var = generator.newVariable(name);
				return generator.newMethodInvocation(var, args, functionName);
			}

			// Hopefully, it is an array with a subscript
			if (argCount == 1)
			{
				Ada_FunctionArg arg = argList.arguments.first();
				if (arg.getWhich() is Ada_Expression)
				{
					AbstractExpression subscr = transformer.transformExpression(generator, (Ada_Expression) arg.getWhich());
					return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ONE, subscr, this);
				}
			}

			throw new Exception("Unable to handle " + name);
		}
	}

}
