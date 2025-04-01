// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.SQL_Variable;
import com.eagle.programmar.SQL.Functions.SQL_BuiltinFunction;
import com.eagle.programmar.SQL.Statements.SQL_CreateProcedureStatement.SQL_ProcedureParameter;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;

public class SQL_CallStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) SQL_Keyword CALL = new SQL_Keyword("CALL");
	public @S(20) SQL_BuiltinFunction func;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken token = func.funcName.getWhich();
		if (token instanceof SQL_Variable)
		{
			// Look it up
			String name = ((SQL_Variable) token).ids.first().getValue();
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new RuntimeException("Unable to find a Stored Procedure named " + name);
			}
			SQL_CreateProcedureStatement proc = (SQL_CreateProcedureStatement) fn;
			
			// Make sure the function args match up
			int argCount = 0;
			if (func.args != null)
			{
				argCount = func.args.getPrimaryCount();
			}

			int paramCount = 0;
			if (proc.params != null)
			{
				paramCount = proc.params.getPrimaryCount();
			}
			
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Stored Procoedure " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}

			interpreter.callingFunction(name, proc);

			// Now assign all the parameters
			if (argCount > 0)
			{
				for (int i = 0; i < argCount; i++)
				{
					EagleValue val = interpreter.getEagleValue(func.args.getPrimaryElement(i));
					SQL_ProcedureParameter param = proc.params.getPrimaryElement(i);
					interpreter.setSymbol(param.param, param.param.getValue(), val);
				}
			}

			// Prepare to evaluate the method
			long startTime = System.nanoTime();

			// And transfer control to the Stored Procedure
			for (SQL_StatementOrComment stmt : proc.stmts._elements)
			{
				Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}

			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			proc._metrics.addCallFrom(this, elapsedTime);

			// Now remove all those parameters
			interpreter.completedFunction(name, proc);
		}
		else
		{
			throw new RuntimeException("Unable to call Stored Procedure " + token);
		}
	}
}
