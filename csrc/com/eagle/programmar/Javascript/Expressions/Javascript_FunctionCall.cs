// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Javascript.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using Javascript_StatementOrComment = com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
	using Javascript_Expression = com.eagle.programmar.Javascript.Javascript_Expression;
	using Javascript_Function = com.eagle.programmar.Javascript.Javascript_Function;
	using Javascript_FunctionBody = com.eagle.programmar.Javascript.Javascript_FunctionBody;
	using Javascript_FunctionParameters = com.eagle.programmar.Javascript.Javascript_FunctionParameters;
	using Javascript_FunctionParameter = com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_FunctionParameter;
	using Javascript_ParenthesizedExpression = com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression;
	using Javascript_Variable = com.eagle.programmar.Javascript.Javascript_Variable;
	using Javascript_Identifier_Reference = com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
	using Javascript_Variable_Definition = com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Javascript_FunctionCall : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Javascript_Variable functionName;
		public Javascript_Variable functionName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Javascript_ParenthesizedExpression arguments;
		public Javascript_ParenthesizedExpression arguments;

		public override void interpret(EagleInterpreter interpreter)
		{
			string name = "unknown";
			AbstractToken first = functionName.firstId.getWhich();
			if (first is Javascript_Identifier_Reference)
			{
				// Look it up
				name = ((Javascript_Identifier_Reference) first).getValue();
			}

			// Make sure the function args match up
			int argCount = 0;
			if (arguments.expressions != null && arguments.expressions.isPresent())
			{
				argCount = arguments.expressions.getPrimaryCount();
			}

			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a function named " + name);
			}
			Javascript_Function func = (Javascript_Function) fn;

			Javascript_FunctionParameters parameters = func.implementation.@params;
			int paramCount = 0;
			if (parameters.param != null)
			{
				paramCount = 1;
			}
			if (parameters.moreParams != null && parameters.moreParams.isPresent())
			{
				paramCount += parameters.moreParams.size();
			}

			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			if (argCount > 0)
			{
				Javascript_FunctionParameters.Javascript_FunctionParameter param = parameters.param;
				for (int i = 0; i < argCount; i++)
				{
					Javascript_Expression expr = arguments.expressions.getPrimaryElement(i);
					if (i > 0)
					{
						param = parameters.moreParams._elements.get(i - 1).param;
					}
					EagleValue val = interpreter.getEagleValue(expr);
					AbstractToken which = param.paramName.getWhich();
					if (which is Javascript_Variable_Definition)
					{
						Javascript_Variable_Definition id = (Javascript_Variable_Definition) which;
						interpreter.setSymbol(param, id.getValue(), val);
						argTypes.Add(val.getType());
					}
				}
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			interpreter.callingFunction(name, func);
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			Javascript_FunctionBody body = func.implementation.body;
			foreach (Javascript_StatementOrComment stmt in body.statements._elements)
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

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Javascript_Variable variable = this.functionName;
			if (variable.firstId.getWhich() is Javascript_Identifier_Reference)
			{
				Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) variable.firstId.getWhich();
				List<AbstractExpression> args = new List<AbstractExpression>();
				int numArgs = arguments.expressions.getPrimaryCount();
				for (int i = 0; i < numArgs; i++)
				{
					Javascript_Expression expr = arguments.expressions.getPrimaryElement(i);
					args.Add(transformer.transformExpression(generator, expr));
				}

				AbstractVariable var = generator.newVariable(id.getValue());
				return generator.newMethodInvocation(var, args, this);
			}
			throw new Exception("Can't handle: " + this);
		}
	}

}
