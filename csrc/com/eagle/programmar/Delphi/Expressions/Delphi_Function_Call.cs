// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Delphi.Expressions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using Delphi_Argument_List = com.eagle.programmar.Delphi.Delphi_Argument_List;
	using Delphi_Expression = com.eagle.programmar.Delphi.Delphi_Expression;
	using Delphi_Function = com.eagle.programmar.Delphi.Delphi_Function;
	using Delphi_Parameter_List = com.eagle.programmar.Delphi.Delphi_Parameter_List;
	using Delphi_Parameter = com.eagle.programmar.Delphi.Delphi_Parameter_List.Delphi_Parameter;
	using Delphi_Procedure = com.eagle.programmar.Delphi.Delphi_Procedure;
	using Delphi_NextStatement = com.eagle.programmar.Delphi.Delphi_Statement_List.Delphi_NextStatement;
	using Delphi_Variable = com.eagle.programmar.Delphi.Delphi_Variable;
	using Delphi_BeginEnd = com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
	using Delphi_Identifier_Reference = com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_Function_Call : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Delphi_Variable name;
		public Delphi_Variable name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Delphi_Argument_List argList;
		public Delphi_Argument_List argList;

		public override void interpret(EagleInterpreter interpreter)
		{
			string fnName = name.var.getValue();
			Delphi_Procedure proc = null;
			Delphi_Function func = null;
			Delphi_Parameter_List paramList = null;
			CallMetrics callMetrics = null;
			ArgumentsMetrics argumentsMetrics = null;
			Delphi_BeginEnd body = null;

			AbstractFunction fn = interpreter.findFunction(fnName);
			if (fn == null)
			{
				throw new Exception("Unable to find a function or procedure named " + fnName);
			}
			if (fn is Delphi_Procedure)
			{
				proc = (Delphi_Procedure) fn;
				paramList = proc.forward.args;
				callMetrics = proc._callMetrics;
				argumentsMetrics = proc._argumentsMetrics;
				body = proc.body;
			}
			else if (fn is Delphi_Function)
			{
				func = (Delphi_Function) fn;
				paramList = func.forward.args;
				callMetrics = func._callMetrics;
				argumentsMetrics = func._argumentsMetrics;
				body = func.body;
			}

			// Make sure the function args match up
			int argCount = argList.exprs.getPrimaryCount();

			int paramCount = 0;
			if (paramList.firstParam != null && paramList.firstParam.isPresent())
			{
				paramCount = 1;
			}
			if (paramList.moreParams != null && paramList.moreParams.isPresent())
			{
				paramCount += paramList.moreParams.size();
			}

			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			if (func != null)
			{
				interpreter.callingFunction(fnName, func);
			}
			else // (proc != null)
			{
				interpreter.callingFunction(fnName, proc);
			}

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			if (argCount > 0)
			{
				Delphi_Parameter_List.Delphi_Parameter param = paramList.firstParam;
				for (int i = 0; i < argCount; i++)
				{
					Delphi_Expression expr = argList.exprs.getPrimaryElement(i);
					if (i > 0)
					{
						param = paramList.moreParams._elements.get(i - 1).param;
					}
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param, param.names.first().var.getValue(), val);
					argTypes.Add(val.getType());
				}
			}

			// Prepare to evaluate the procedure or function
			long startTime = System.nanoTime();

			// And transfer control to the procedure or function
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			result = interpreter.tryToInterpret(body.statements.stmt);
			if (result == Eagle_Statement_Result.NORMAL)
			{
				if (body.statements.stmts != null)
				{
					foreach (Delphi_NextStatement stmt in body.statements.stmts._elements)
					{
						result = interpreter.tryToInterpret(stmt.stmt);
						if (result != Eagle_Statement_Result.NORMAL)
						{
							break;
						}
					}
				}
			}

			// Need to put the result on the runtime stack
			if (func != null)
			{
				// Delphi uses the function name for the return value
				// Sort-of like this: function sqrt(x) { sqrt = x*x }
				EagleValue val = interpreter.findSymbol(fnName);
				if (val != null)
				{
					interpreter.pushEagleValue(val);
				}
			}

			long elapsedTime = System.nanoTime() - startTime;
			callMetrics.addCallFrom(this, elapsedTime);
			argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			if (func != null)
			{
				interpreter.completedFunction(fnName, func);
			}
			else // (proc != null)
			{
				interpreter.completedFunction(fnName, proc);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			Delphi_Variable variable = this.name;
			Delphi_Identifier_Reference id = variable.var;
			List<AbstractExpression> args = new List<AbstractExpression>();
			if (this.argList != null && this.argList.isPresent())
			{
				int nargs = this.argList.exprs.getPrimaryCount();
				for (int i = 0; i < nargs; i++)
				{
					Delphi_Expression expr = this.argList.exprs.getPrimaryElement(i);
					args.Add(transformer.transformExpression(generator, expr));
				}
			}

			AbstractVariable var = generator.newVariable(id.getValue());
			return generator.newMethodInvocation(var, args, this);
		}
	}

}
