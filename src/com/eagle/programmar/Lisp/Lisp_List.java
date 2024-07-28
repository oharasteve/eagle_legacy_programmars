// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

package com.eagle.programmar.Lisp;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Lisp.Functions.Lisp_DefunFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_DefunFunction.Lisp_ParamDef;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_List extends TokenSequence implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT TokenList<Lisp_SExpr> exprs;
	public @S(30) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Lisp_SExpr first = exprs.first();
		String name = first.showText();
		
		// See if it is one of the user defun's
		for (AbstractFunction function : interpreter._functionList)
		{
			Lisp_DefunFunction func = (Lisp_DefunFunction) function;
			if (func.name.getValue().equals(name))
			{
				if (interpreter._TRACE) System.err.println("**** Calling function " + name);

				int argCount = exprs.size() - 1;	// Minus 1 for the function name
				int paramCount = func.parameters.size();
				
				if (argCount != paramCount)
				{
					throw new RuntimeException(
							"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
				}

				// Now assign all the parameters
				if (argCount > 0)
				{
					for (int i = 0; i < argCount; i++)
					{
						Lisp_SExpr expr = exprs._elements.get(i + 1);
						Lisp_ParamDef param = func.parameters._elements.get(i);
						EagleValue val = interpreter.getEagleValue(expr);
						interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
								param.parameter.getValue(), val);
					}
				}

				// Prepare to evaluate the method
				long startTime = System.nanoTime();

				// And transfer control to the method
				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				for (Lisp_SExpr stmt : func.body._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}

				// The result was already put on the runtime stack
				long elapsedTime = System.nanoTime() - startTime;
				func._metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

				// Now remove all those parameters
				if (argCount > 0)
				{
					for (int i = 0; i < argCount; i++)
					{
						Lisp_ParamDef param = func.parameters._elements.get(i);
						interpreter._symbolTable.removeSymbols(param.parameter.getValue());
					}
				}

				return;
			}
		}
		
		throw new RuntimeException("Please implement function: " + name);
	}
}
