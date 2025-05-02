// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
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
import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Delphi_Function_Call extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Delphi_Variable name;
	public @S(20) Delphi_Argument_List argList;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = name.var.getValue();
		Delphi_Procedure proc = null;
		Delphi_Function func = null;
		Delphi_Parameter_List paramList = null;
		CallMetrics metrics = null;
		Delphi_BeginEnd body = null;
		String name = null;
		
		AbstractFunction fn = interpreter.findFunction(fnName);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function or procedure named " + fnName);
		}
		if (fn instanceof Delphi_Procedure)
		{
			proc = (Delphi_Procedure) fn;
			name = proc.forward.name.var.getValue();
			paramList = proc.forward.args;
			metrics = proc._metrics;
			body = proc.body;
		}
		else if (fn instanceof Delphi_Function)
		{
			func = (Delphi_Function) fn;
			name = func.forward.name.var.getValue();
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

		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, this, name);
		}
		ArrayList<String> argTypes = new ArrayList<String>();

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
				interpreter.setSymbol(param, param.names.first().var.getValue(), val);
				argTypes.add(val.typeName());
			}
		}

		// Prepare to evaluate the procedure or function
		_metrics.called(argTypes);
		long startTime = System.nanoTime();

		// And transfer control to the procedure or function
		interpreter.callingFunction(fnName, func);
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
			EagleValue val = interpreter.findSymbol(fnName);
			if (val != null)
			{
				interpreter.pushEagleValue(val);
			}
		}
		
		long elapsedTime = System.nanoTime() - startTime;
		metrics.addCallFrom(this, elapsedTime);

		// Now remove all those parameters
		interpreter.completedFunction(fnName, func);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		Delphi_Variable variable = this.name;
		Delphi_Identifier_Reference id = variable.var;
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		if (this.argList != null && this.argList.isPresent())
		{
			int nargs = this.argList.exprs.getPrimaryCount();
			for (int i = 0; i < nargs; i++)
			{
				Delphi_Expression expr = this.argList.exprs.getPrimaryElement(i);
				args.add(transformer.transformExpression(generator, expr));
			}
		}
		
		AbstractVariable var = generator.newVariable(id.getValue());
		return generator.newMethodInvocation(var, args, this);
	}
}
