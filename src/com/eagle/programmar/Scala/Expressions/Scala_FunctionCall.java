// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Scala_Variable;
import com.eagle.programmar.Scala.Statements.Scala_Function;
import com.eagle.programmar.Scala.Statements.Scala_Function.Scala_FunctionParameter;
import com.eagle.programmar.Scala.Symbols.Scala_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Scala_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Scala_Variable methodName;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Scala_Expression, PunctuationComma> argList;
	public @S(40) PunctuationRightParen rightParen;

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Scala_Identifier_Reference id = methodName.vars.first();
		String name = id.getValue();
		
		// See if it is a subscript reference first
		EagleValue symb = interpreter.findSymbol(name);
		if (symb != null)
		{
			// Look up the variable
			if (! symb.isArray())
			{
				throw new RuntimeException("Can only use subscripts on arrays");
			}
			EagleArray array = (EagleArray) symb;
			int subscr = interpreter.getIntValue(argList.first());
			interpreter.pushEagleValue(array.getValue(subscr));
		}
		else
		{
			// Look up the function
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new RuntimeException("Unable to find a function named " + name);
			}
			Scala_Function func = (Scala_Function) fn;
	
			// Make sure the function args match up
			int argCount = argList.getPrimaryCount();
			int paramCount = func.params.parameters.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Method " + name + " expects #args = " + paramCount + ", but was given " + argCount);
			}
	
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, id, name);
			}
			ArrayList<String> argTypes = new ArrayList<String>();

			// Now assign all the parameters
			for (int i = 0; i < argCount; i++)
			{
				Scala_Expression expr = argList.getPrimaryElement(i);
				Scala_FunctionParameter param = func.params.parameters.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.var.getValue(), val);
				argTypes.add(val.typeName());
			}
			_metrics.called(argTypes);
	
			// Prepare to evaluate the method
			long startTime = System.nanoTime();
	
			// And transfer control to the method
			interpreter.callingFunction(name, func);
			interpreter.tryToInterpret(func.stmt);
	
			// The result was already put on the runtime stack
			long elapsedTime = System.nanoTime() - startTime;
			func._metrics.addCallFrom(this, elapsedTime);
	
			// Now remove all those parameters
			interpreter.completedFunction(name, func);
		}
	}
}
