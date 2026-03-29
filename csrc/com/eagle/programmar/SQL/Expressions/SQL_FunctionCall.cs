// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.SQL.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using EagleValue = com.eagle.math.EagleValue;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_FunctionArg = com.eagle.programmar.SQL.SQL_FunctionArg;
	using SQL_StatementOrComment = com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
	using SQL_Variable = com.eagle.programmar.SQL.SQL_Variable;
	using SQL_CreateFunctionStatement = com.eagle.programmar.SQL.Statements.SQL_CreateFunctionStatement;
	using SQL_FunctionParameter = com.eagle.programmar.SQL.Statements.SQL_CreateFunctionStatement.SQL_FunctionParameter;
	using SQL_Identifier_Reference = com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
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

	public class SQL_FunctionCall : PrimaryOperator, EagleRunnableWithResult, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.SQL_Variable funcName;
		public SQL_Variable funcName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationLeftParen leftParen;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE @OPT SeparatedList<com.eagle.programmar.SQL.SQL_FunctionArg, com.eagle.tokens.punctuation.PunctuationComma> args;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationRightParen rightParen;
		public  NOSPACE;

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			SQL_Identifier_Reference id = funcName.ids.first();
			string name = id.getValue();
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + name);
			}
			SQL_CreateFunctionStatement func = (SQL_CreateFunctionStatement) fn;

			// Make sure the function args match up
			int argCount = args.getPrimaryCount();
			int paramCount = func.@params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, func);

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			if (argCount > 0)
			{
				for (int i = 0; i < argCount; i++)
				{
					SQL_FunctionArg arg = args.getPrimaryElement(i);
					if (!(arg.getWhich() is SQL_Expression))
					{
						throw new Exception("Unable to handle " + arg.getWhich());
					}
					SQL_Expression expr = (SQL_Expression) arg.getWhich();
					SQL_CreateFunctionStatement.SQL_FunctionParameter param = func.@params.getPrimaryElement(i);
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param.id, param.id.getValue(), val);
					argTypes.Add(val.getType());
				}
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (SQL_StatementOrComment stmtComm in func.statements._elements)
			{
				result = interpreter.tryToInterpret(stmtComm);
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
			return result;
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			SQL_Identifier_Reference id = funcName.ids.first();
			List<AbstractExpression> arguments = new List<AbstractExpression>();
			if (args != null && args.isPresent())
			{
				int argCount = args.getPrimaryCount();
				for (int i = 0; i < argCount; i++)
				{
					SQL_FunctionArg arg = args.getPrimaryElement(i);
					if (!(arg.getWhich() is SQL_Expression))
					{
						throw new Exception("Unable to handle " + arg.getWhich());
					}
					SQL_Expression expr = (SQL_Expression) arg.getWhich();
					arguments.Add(transformer.transformExpression(generator, expr));
				}
			}

			AbstractVariable var = generator.newVariable(id.getValue());
			return generator.newMethodInvocation(var, arguments, this);
		}
	}

}
