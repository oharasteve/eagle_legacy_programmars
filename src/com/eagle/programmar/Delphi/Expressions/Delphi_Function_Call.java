// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Delphi.Delphi_Argument_List;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Function;
import com.eagle.programmar.Delphi.Delphi_Parameter_List;
import com.eagle.programmar.Delphi.Delphi_Parameter_List.Delphi_Parameter;
import com.eagle.programmar.Delphi.Delphi_Procedure;
import com.eagle.programmar.Delphi.Delphi_Statement_List.Delphi_MoreStatements;
import com.eagle.programmar.Delphi.Delphi_Variable;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;

public class Delphi_Function_Call extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Delphi_Variable name;
	public @S(20) Delphi_Argument_List argList;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = name.var.getValue();
		Delphi_Procedure proc = null;
		Delphi_Function func = null;
		Delphi_Parameter_List paramList = null;
		CallMetrics metrics = null;
		Delphi_BeginEnd body = null;
		
		AbstractFunction fn = interpreter._functionList.get(fnName);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function or procedure named " + fnName);
		}
		if (fn instanceof Delphi_Procedure)
		{
			proc = (Delphi_Procedure) fn;
			paramList = proc.forward.args;
			metrics = proc._metrics;
			body = proc.body;
		}
		else if (fn instanceof Delphi_Function)
		{
			func = (Delphi_Function) fn;
			paramList = func.forward.args;
			metrics = func._metrics;
			body = func.body;
		}

		// Make sure the function args match up
		int argCount = argList.exprs.getPrimaryCount();

		int paramCount = 0;
		if (paramList.firstParam != null && paramList.firstParam.isPresent()) paramCount = 1;
		if (paramList.moreParams != null && paramList.moreParams.isPresent())
		{
			paramCount += paramList.moreParams.size();
		}
		
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		if (argCount > 0)
		{
			Delphi_Parameter param = paramList.firstParam;
			for (int i = 0; i < argCount; i++)
			{
				Delphi_Expression expr = argList.exprs.getPrimaryElement(i);
				if (i > 0)
				{
					param = paramList.moreParams._elements.get(i-1).param;
				}
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter._symbolTable.setSymbol(param.getFileName(), param.getStartLine(), param.getStartChar(),
						param.names.first().var.getValue(), val);
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
				for (Delphi_MoreStatements stmt : body.statements.stmts._elements)
				{
					result = interpreter.tryToInterpret(stmt.stmt);
					if (result != Eagle_Statement_Result.NORMAL) break;
				}
			}
		}

		// Need to put the result on the runtime stack
		if (func != null)
		{
			// Delphi uses the function name for the return value
			// Sort-of like this: function sqrt(x) { sqrt = x*x }
			EagleValue val = interpreter._symbolTable.findSymbol(fnName);
			if (val != null)
			{
				interpreter.pushEagleValue(val);
			}
		}
		
		long elapsedTime = System.nanoTime() - startTime;
		metrics.addCallFrom(this.getFileName(), this.getStartLine(), this.getStartChar(), elapsedTime);

		// Now remove all those parameters
		if (argCount > 0)
		{
			Delphi_Parameter param = paramList.firstParam;
			interpreter._symbolTable.removeSymbols(param.names.first().var.getValue());
			for (int i = 1; i < argCount; i++)
			{
				param = paramList.moreParams._elements.get(i-1).param;
				interpreter._symbolTable.removeSymbols(param.names.first().var.getValue());
			}
		}
	}
}
