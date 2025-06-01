// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Functions;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.VB.VB_Element;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Statements.VB_Function;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Symbols.VB_Variable_Definition;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class VB_FunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) VB_Identifier_Reference fnName;
	public @S(20) VB_FnCallArguments callArguments;

	public static class VB_FnCallArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<VB_Expression, PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;
	}

	private @SKIP ArgumentsMetrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = fnName.getValue();
		
		// See if it is a subscripted variable first
		EagleValue value = interpreter.findSymbol(name);
		if (value != null && value.isArray())
		{
			EagleArray array = (EagleArray) value;
			int index = interpreter.getIntValue(callArguments.arguments.first());
			interpreter.pushEagleValue(array.getValue(index));
			return;
		}
		
		// Look up the function
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null || !(fn instanceof VB_Function))
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		VB_Function func = (VB_Function) fn;

		// Make sure the function args match up
		int argCount = callArguments.arguments.getPrimaryCount();
		int paramCount = func.params.params.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(name, func);

		if (_metrics == null)
		{
			_metrics = new ArgumentsMetrics(interpreter._metrics, fnName, fnName.getValue());
		}
		ArrayList<String> argTypes = new ArrayList<String>();

		// Now assign all the parameters
		for (int i = 0; i < argCount; i++)
		{
			VB_Expression expr = callArguments.arguments.getPrimaryElement(i);
			VB_Variable_Definition param = func.params.params.getPrimaryElement(i).var;

			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.getValue(), val);
			argTypes.add(val.typeName());
		}
		_metrics.called(argTypes);

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (VB_Element stmt : func.stmts._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break; 
		}
		
		// Need to put the result on the runtime stack
		// VB uses the function name for the return value
		// Sort-of like this: Function sqrt(x) ; sqrt = x*x ; End Function
		EagleValue val = interpreter.findSymbol(name);
		if (val != null)
		{
			interpreter.pushEagleValue(val);
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._metrics.addCallFrom(this, elapsedTime);

		// Now remove all those parameters
		interpreter.completedFunction(name, func);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractVariable var = generator.newVariable(fnName.getValue());
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		return generator.newMethodInvocation(var, args, fnName);
	}
}
