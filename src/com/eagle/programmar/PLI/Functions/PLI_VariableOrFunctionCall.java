// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Functions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Procedure;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.PLI_Subscript;
import com.eagle.programmar.PLI.PLI_Subscript.PLI_ExpressionOrStar;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;

public class PLI_VariableOrFunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PLI_Identifier_Reference id;
	public @S(20) @OPT PLI_Subscript subscript;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (subscript != null && subscript.isPresent())
		{
			String name = id.getValue();
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
				throw new RuntimeException("Unable to find a Procedure named " + name);
			}
			PLI_Procedure proc = (PLI_Procedure) fn;

			// Count the parameters
			int paramCount = proc.params.params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Function " + name + ", expected params = " + paramCount + ", but actual args = " + argCount);
			}

			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, id, name);
			}
			ArrayList<String> argTypes = new ArrayList<String>();

			// Assign all the parameters
			for (int i = 0; i < argCount; i++)
			{
				PLI_Identifier_Reference param = proc.params.params.getPrimaryElement(i);
				PLI_ExpressionOrStar arg = subscript.args.getPrimaryElement(i);
				PLI_Expression expr = (PLI_Expression) arg.getWhich();
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.getValue(), val);
				argTypes.add(val.typeName());
			}
			_metrics.called(argTypes);

			// Evaluate the function
			long startTime = System.nanoTime();

			interpreter.callingFunction(name, proc);
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (PLI_StatementOrComment stmt : proc.statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			
			long elapsedTime = System.nanoTime() - startTime;
			if (proc._metrics == null)
			{
				proc._metrics = new CallMetrics(interpreter._metrics, name, proc.id1);
			}
			proc._metrics.addCallFrom(this, elapsedTime);

			// Remove all the parameters
			interpreter.completedFunction(name, proc);
		}
		else
		{
			// Just a variable
			EagleValue value = interpreter.findSymbol(id.toString());
			interpreter.pushEagleValue(value);
		}
	}
}
