// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.PLI.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using PLI_Expression = com.eagle.programmar.PLI.PLI_Expression;
	using PLI_Procedure = com.eagle.programmar.PLI.PLI_Procedure;
	using PLI_StatementOrComment = com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
	using PLI_Subscript = com.eagle.programmar.PLI.PLI_Subscript;
	using PLI_ExpressionOrStar = com.eagle.programmar.PLI.PLI_Subscript.PLI_ExpressionOrStar;
	using PLI_Identifier_Reference = com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using BuiltInEnum = com.eagle.transform.EagleGenerator.BuiltInEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class PLI_VariableOrFunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference id;
		public PLI_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PLI_Subscript subscript;
		public  OPT;

		public override void interpret(EagleInterpreter interpreter)
		{
			string name = id.getValue();
			if (subscript != null && subscript.isPresent())
			{
				int argCount = subscript.args.getPrimaryCount();

				// First: search user variables
				EagleValue var = interpreter.findSymbol(name);
				if (var != null && var.isArray() && argCount == 1)
				{
					EagleArray array = (EagleArray) var;
					int subscr = interpreter.getIntValue(subscript.args.getPrimaryElement(0));
					EagleValue val = array.getValue(subscr);
					interpreter.pushEagleValue(val);
					return;
				}

				// Next: search for the Procedure definition
				AbstractFunction fn = interpreter.findFunction(name);
				if (fn == null)
				{
					throw new Exception("Unable to find a Procedure named " + name);
				}
				PLI_Procedure proc = (PLI_Procedure) fn;

				// Count the parameters
				int paramCount = proc.@params.@params.getPrimaryCount();
				if (argCount != paramCount)
				{
					throw new Exception("Function " + name + ", expected params = " + paramCount + ", but actual args = " + argCount);
				}

				interpreter.callingFunction(name, proc);

				// Assign all the parameters
				List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
				for (int i = 0; i < argCount; i++)
				{
					PLI_Identifier_Reference param = proc.@params.@params.getPrimaryElement(i);
					PLI_Subscript.PLI_ExpressionOrStar arg = subscript.args.getPrimaryElement(i);
					PLI_Expression expr = (PLI_Expression) arg.getWhich();
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param, param.getValue(), val);
					argTypes.Add(val.getType());
				}

				// Evaluate the function
				long startTime = System.nanoTime();

				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				foreach (PLI_Procedure.PLI_StatementOrComment stmt in proc.statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}

				long elapsedTime = System.nanoTime() - startTime;
				proc._callMetrics.addCallFrom(this, elapsedTime);
				proc._argumentsMetrics.calledWith(argTypes);

				// Remove all the parameters
				interpreter.completedFunction(name, proc);
			}
			else
			{
				// Just a variable
				if (name.Equals("true", StringComparison.OrdinalIgnoreCase))
				{
					interpreter.pushBool(true);
				}
				else if (name.Equals("false", StringComparison.OrdinalIgnoreCase))
				{
					interpreter.pushBool(false);
				}
				else
				{
					EagleValue value = interpreter.findSymbol(name);
					interpreter.pushEagleValue(value);
				}
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = id.getValue();

			if (subscript == null || !subscript.isPresent())
			{
				// Case I: Just a variable with no subscript, and can't be a function call
				if (name.Equals("true", StringComparison.OrdinalIgnoreCase))
				{
					return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.TRUE, this);
				}
				if (name.Equals("false", StringComparison.OrdinalIgnoreCase))
				{
					return generator.newBuiltInExpression(EagleGenerator.BuiltInEnum.FALSE, this);
				}
				return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, id);
			}

			if (subscript.args != null)
			{
				int argCount = subscript.args.getPrimaryCount();

				// Case II: Calling a Procedure
				if (generator.isKnownMethod(name))
				{
					List<AbstractExpression> args = new List<AbstractExpression>();
					for (int i = 0; i < argCount; i++)
					{
						PLI_Subscript.PLI_ExpressionOrStar arg = subscript.args.getPrimaryElement(i);
						if (arg.getWhich() is PLI_Expression)
						{
							PLI_Expression expr = (PLI_Expression) arg.getWhich();
							AbstractExpression newArg = transformer.transformExpression(generator, expr);
							args.Add(newArg);
						}
					}

					AbstractVariable var = generator.newVariable(name);
					return generator.newMethodInvocation(var, args, id);
				}

				// Case III: an array variable, with a subscript
				// Dang. PL/I uses () for both arrays and function calls
				PLI_Subscript.PLI_ExpressionOrStar arg = subscript.args.first();
				if (arg.getWhich() is PLI_Expression)
				{
					PLI_Expression expr = (PLI_Expression) arg.getWhich();
					AbstractExpression index = transformer.transformExpression(generator, expr);
					return generator.newVariableExpression(name, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, index, this);
				}
			}

			return null;
		}
	}

}
