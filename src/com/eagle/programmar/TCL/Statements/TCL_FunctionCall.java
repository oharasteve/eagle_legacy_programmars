// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 22, 2024

package com.eagle.programmar.TCL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.TCL_Procedure;
import com.eagle.programmar.TCL.Symbols.TCL_Function_Reference;
import com.eagle.programmar.TCL.Symbols.TCL_Variable_Definition;
import com.eagle.scope.EagleScope;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class TCL_FunctionCall extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) TCL_Function_Reference function;
	public @S(20) TokenList<TCL_Expression> callArguments;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = function.getValue();
		
		// Look up the function
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a procedure named " + name);
		}
		TCL_Procedure proc = (TCL_Procedure) fn;

		// Make sure the function args match up
		int argCount = callArguments.size();
		int paramCount = proc.vars.size();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Procedure " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		ArrayList<String> argTypes = new ArrayList<String>();
		for (int i = 0; i < argCount; i++)
		{
			TCL_Expression expr = callArguments._elements.get(i);
			TCL_Variable_Definition param = proc.vars._elements.get(i);
			EagleValue val = interpreter.getEagleValue(expr);
			
			// Make sure Scope is in the CALLED function, not the CALLER
			EagleScope saveScope = interpreter._symbolTable.getScope();
			interpreter._symbolTable.setScope(proc.getScope());
			interpreter.setSymbol(param, param.getValue(), val);
			interpreter._symbolTable.setScope(saveScope);
			
			argTypes.add(val.typeName());
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

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator generator)
	{
		String name = function.getValue();
		if (generator.isKnownMethod(name))
		{
			ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
			for (TCL_Expression arg : callArguments._elements)	
			{
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.add(newArg);
			}
	
			AbstractVariable var = generator.newVariable(name);
			AbstractExpression expr = generator.newMethodInvocation(var, args, function);
			return generator.newExpressionStatement(expr, callArguments);
		}
		
		throw new RuntimeException("Unknown function: " + name);
	}
}
