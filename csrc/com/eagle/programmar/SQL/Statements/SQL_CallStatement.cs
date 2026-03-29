// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

namespace com.eagle.programmar.SQL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using SQL_Expression = com.eagle.programmar.SQL.SQL_Expression;
	using SQL_FunctionArg = com.eagle.programmar.SQL.SQL_FunctionArg;
	using SQL_StatementOrComment = com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
	using SQL_Variable = com.eagle.programmar.SQL.SQL_Variable;
	using SQL_VariableExpression = com.eagle.programmar.SQL.Expressions.SQL_VariableExpression;
	using SQL_ProcedureParameter = com.eagle.programmar.SQL.Statements.SQL_CreateProcedureStatement.SQL_ProcedureParameter;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_CallStatement : TokenSequence, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword CALL = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CALL");
		public SQL_Keyword CALL = new SQL_Keyword("CALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.SQL_Variable procName;
		public SQL_Variable procName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT SeparatedList<com.eagle.programmar.SQL.SQL_FunctionArg, com.eagle.tokens.punctuation.PunctuationComma> args;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public override void interpret(EagleInterpreter interpreter)
		{
			string name = procName.ids.first().getValue();
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a Stored Procedure named " + name);
			}
			SQL_CreateProcedureStatement proc = (SQL_CreateProcedureStatement) fn;

			// Make sure the function args match up
			int argCount = 0;
			if (args != null)
			{
				argCount = args.getPrimaryCount();
			}

			int paramCount = 0;
			if (proc.@params != null)
			{
				paramCount = proc.@params.getPrimaryCount();
			}

			if (argCount != paramCount)
			{
				throw new Exception("Stored Procoedure " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, proc);

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			if (argCount > 0)
			{
				for (int i = 0; i < argCount; i++)
				{
					SQL_ProcedureParameter param = proc.@params.getPrimaryElement(i);
					EagleValue val = interpreter.getEagleValue(args.getPrimaryElement(i));
					interpreter.setSymbol(param.param, param.param.getValue(), val);
					argTypes.Add(val.getType());
				}
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the Stored Procedure
			foreach (SQL_StatementOrComment stmt in proc.statements._elements)
			{
				Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL)
				{
					break;
				}
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			proc._callMetrics.addCallFrom(this, elapsedTime);
			proc._argumentsMetrics.calledWith(argTypes);

			for (int i = 0; i < argCount; i++)
			{
				// Set values for any OUT parameters
				SQL_ProcedureParameter param = proc.@params.getPrimaryElement(i);
				if (param.OUT != null && param.OUT.isPresent() && param.OUT.getValue().ToUpper().Equals("OUT"))
				{
					EagleValue val = interpreter.findSymbol(param.param.getValue());
					SQL_FunctionArg arg = args.getPrimaryElement(i);
					if (!(arg.getWhich() is SQL_Expression))
					{
						throw new Exception("OUT parameter must be an Expression");
					}
					SQL_Expression expr = (SQL_Expression) arg.getWhich();
					if (!(expr.getWhich() is SQL_VariableExpression))
					{
						throw new Exception("OUT parameter must be a Variable");
					}
					SQL_VariableExpression var = (SQL_VariableExpression) expr.getWhich();
					string varName = var.variable.ids.first().getValue();
					// System.out.println("******** Setting OUT param " + varName + " to " + val);
					interpreter.setSymbol(var, varName, val);
				}
			}

			// Now remove all those parameters
			interpreter.completedFunction(name, proc);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = procName.ids.first().getValue();

			int argCount = 0;
			if (args != null)
			{
				argCount = args.getPrimaryCount();
			}

	//		// Are any parameters defined as OUT? Can only be one, and it must be returned not passed in
	//		SQL_ProcedureParameter outParam = null;
	//		for (int i = 0; i < argCount; i++)
	//		{
	//			SQL_ProcedureParameter param = proc.params.getPrimaryElement(i);
	//			if (param.OUT != null && param.OUT.isPresent())
	//			{
	//				if (param.OUT.toString().toUpperCase().equals("OUT"))
	//				{
	//					if (outParam != null)
	//					{
	//						throw new RuntimeException("Can only handle one OUT parameter at a time");
	//					}
	//					outParam = param;
	//				}
	//			}
	//		}

			List<AbstractExpression> arguments = new List<AbstractExpression>();
			for (int i = 0; i < argCount; i++)
			{
				SQL_FunctionArg arg = args.getPrimaryElement(i);
				if (arg.getWhich() is SQL_Expression)
				{
					SQL_Expression expr = (SQL_Expression) arg.getWhich();
					AbstractExpression newArg = transformer.transformExpression(generator, expr);
					arguments.Add(newArg);
				}
			}

			AbstractVariable newName = generator.newVariable(name);
			AbstractExpression newExpr = generator.newMethodInvocation(newName, arguments, this);
			return generator.newExpressionStatement(newExpr, CALL);
		}
	}

}
