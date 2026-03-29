// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

namespace com.eagle.programmar.TCL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using TCL_Expression = com.eagle.programmar.TCL.TCL_Expression;
	using TCL_Procedure = com.eagle.programmar.TCL.TCL_Procedure;
	using TCL_Function_Reference = com.eagle.programmar.TCL.Symbols.TCL_Function_Reference;
	using TCL_Variable_Definition = com.eagle.programmar.TCL.Symbols.TCL_Variable_Definition;
	using EagleScope = com.eagle.scope.EagleScope;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_FunctionCall : TokenSequence, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.TCL.Symbols.TCL_Function_Reference function;
		public TCL_Function_Reference function;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.TCL.TCL_Expression> callArguments;
		public TokenList<TCL_Expression> callArguments;

		public override void interpret(EagleInterpreter interpreter)
		{
			string name = function.getValue();

			// Look up the function
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Unable to find a procedure named " + name);
			}
			TCL_Procedure proc = (TCL_Procedure) fn;

			// Make sure the function args match up
			int argCount = callArguments.size();
			int paramCount = proc.vars.size();
			if (argCount != paramCount)
			{
				throw new Exception("Procedure " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();
			for (int i = 0; i < argCount; i++)
			{
				TCL_Expression expr = callArguments._elements.get(i);
				TCL_Variable_Definition param = proc.vars._elements.get(i);
				EagleValue val = interpreter.getEagleValue(expr);

				// Make sure Scope is in the CALLED function, not the CALLER
				EagleScope saveScope = interpreter._symbolTable.getScope();
				interpreter._symbolTable.setScope(proc.Scope);
				interpreter.setSymbol(param, param.getValue(), val);
				interpreter._symbolTable.setScope(saveScope);

				argTypes.Add(val.getType());
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			interpreter.callingFunction(name, proc);
			interpreter.tryToInterpret(proc.body);

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			proc._callMetrics.addCallFrom(this, elapsedTime);
			proc._argumentsMetrics.calledWith(argTypes);

			// Now remove all those parameters
			interpreter.completedFunction(name, proc);
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string name = function.getValue();
			if (generator.isKnownMethod(name))
			{
				List<AbstractExpression> args = new List<AbstractExpression>();
				foreach (TCL_Expression arg in callArguments._elements)
				{
					AbstractExpression newArg = transformer.transformExpression(generator, arg);
					args.Add(newArg);
				}

				AbstractVariable var = generator.newVariable(name);
				AbstractExpression expr = generator.newMethodInvocation(var, args, function);
				return generator.newExpressionStatement(expr, callArguments);
			}

			throw new Exception("Unknown function: " + name);
		}
	}

}
