// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Statements.Fortran_Function;
import com.eagle.programmar.Fortran.Symbols.Fortran_Identifier_Reference;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Fortran_FunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Fortran_Identifier_Reference variable;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Fortran_Expression, PunctuationComma> argList;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = variable.getValue().toUpperCase();
		int argCount = argList.getPrimaryCount();

		// Check for subscripts
		EagleValue var = interpreter.findSymbol(fnName);
		if (var != null && var.isArray() && argCount == 1)
		{
			EagleArray array = (EagleArray) var;
			int subscr = interpreter.getIntValue(argList.getPrimaryElement(0));
			EagleValue val = array.getValue(subscr - 1);
			interpreter.pushEagleValue(val);
			return;
		}

		// Check for user functions
		AbstractFunction fn = interpreter.findFunction(fnName);
		if (fn == null || !(fn instanceof Fortran_Function))
		{
			throw new RuntimeException("Unable to find a function named " + fnName);
		}
		Fortran_Function func = (Fortran_Function) fn;

		// Make sure the function args match up
		int paramCount = func.parameters.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + fnName + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		ArrayList<String> argTypes = new ArrayList<String>();
		for (int i = 0; i < argCount; i++)
		{
			Fortran_Expression expr = argList.getPrimaryElement(i);
			Fortran_Variable_Reference param = func.parameters.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.getValue(), val);
			argTypes.add(val.typeName());
		}

		// Prepare to evaluate the procedure or function
		long startTime = System.nanoTime();

		// And transfer control to the procedure or function
		interpreter.callingFunction(fnName, func);
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Fortran_Statement stmt : func.statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// Need to put the result on the runtime stack
		// Fortran uses the function name for the return value
		// Sort-of like this: function sqrt(x) { sqrt = x*x }
		EagleValue val = interpreter.findSymbol(fnName);
		if (val != null)
		{
			interpreter.pushEagleValue(val);
		}

		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		interpreter.completedFunction(fnName, func);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		String name = variable.getValue();
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();

		if (argList != null && argList.isPresent())
		{
			for (int i = 0; i < argList.getPrimaryCount(); i++)
			{
				Fortran_Expression expr = argList.getPrimaryElement(i);
				AbstractExpression newExpr = transformer.transformExpression(generator, expr);
				args.add(newExpr);
			}
		}

		AbstractVariable var = generator.newVariable(name);
		return generator.newMethodInvocation(var, args, this);
	}
}