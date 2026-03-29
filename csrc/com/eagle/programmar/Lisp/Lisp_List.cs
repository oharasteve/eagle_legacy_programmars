// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

namespace com.eagle.programmar.Lisp
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Eagle_Statement_Result = com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
	using EagleValue = com.eagle.math.EagleValue;
	using Lisp_DefunFunction = com.eagle.programmar.Lisp.Functions.Lisp_DefunFunction;
	using Lisp_ParamDef = com.eagle.programmar.Lisp.Functions.Lisp_DefunFunction.Lisp_ParamDef;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Lisp_List : TokenSequence, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Lisp_Expression> exprs;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			Lisp_Expression first = exprs.first();
			string name = first.showText();

			// See if it is one of the user defun's
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new Exception("Please implement " + name);
			}
			Lisp_DefunFunction func = (Lisp_DefunFunction) fn;

			int argCount = exprs.size() - 1; // Minus 1 for the function name
			int paramCount = func.parameters.size();

			if (argCount != paramCount)
			{
				throw new Exception("Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			// Now assign all the parameters
			List<TypeEnum> argTypes = new List<TypeEnum>();
			if (argCount > 0)
			{
				for (int i = 0; i < argCount; i++)
				{
					Lisp_Expression expr = exprs._elements.get(i + 1);
					Lisp_DefunFunction.Lisp_ParamDef param = func.parameters._elements.get(i);
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(param, param.parameter.getValue(), val);
					argTypes.Add(val.getType());
				}
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the method
			interpreter.callingFunction(name, func);
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			foreach (Lisp_Expression stmt in func.body._elements)
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
	}

}
