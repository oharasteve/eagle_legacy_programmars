// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Statement;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Statements.Julia_Function;
import com.eagle.programmar.Julia.Symbols.Julia_Identifier_Reference;
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

public class Julia_FunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Julia_Variable variable;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Julia_Expression, PunctuationComma> argList;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Julia_Identifier_Reference id = variable.vars.first();
		String name = id.getValue();
		
		// See if it is builtin function
		if (name.equals("div") && argList.getPrimaryCount() == 2)
		{
			Julia_Expression numerExpr = argList.getPrimaryElement(0);
			Julia_Expression denomExpr = argList.getPrimaryElement(1);
			int numer = interpreter.getIntValue(numerExpr);
			int denom = interpreter.getIntValue(denomExpr);
			interpreter.pushInt(numer / denom);
			return;
		}
		if (name.equals("string"))
		{
			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < argList.getPrimaryCount(); i++)
			{
				Julia_Expression expr = argList.getPrimaryElement(i);
				String val = interpreter.getStrValue(expr);
				buff.append(val);
			}
			interpreter.pushStr(buff.toString());
			return;
		}
		if (name.equals("startswith") && argList.getPrimaryCount() == 2)
		{
			Julia_Expression strExpr = argList.getPrimaryElement(0);
			Julia_Expression pattExpr = argList.getPrimaryElement(1);
			String str = interpreter.getStrValue(strExpr);
			String patt = interpreter.getStrValue(pattExpr);
			interpreter.pushBool(str.startsWith(patt));
			return;
		}
		if (name.equals("length") && argList.getPrimaryCount() == 1)
		{
			Julia_Expression strExpr = argList.getPrimaryElement(0);
			String str = interpreter.getStrValue(strExpr);
			interpreter.pushInt(str.length());
			return;
		}

		// Look up the function in our function list
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		Julia_Function func = (Julia_Function) fn;

		// Make sure the function args match up
		int argCount = argList.getPrimaryCount();
		int paramCount = func.params.parameters.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		ArrayList<String> argTypes = new ArrayList<String>();
		for (int i = 0; i < argCount; i++)
		{
			Julia_Expression expr = argList.getPrimaryElement(i);
			Julia_Variable param = func.params.parameters.getPrimaryElement(i);

			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.vars.first().getValue(), val);
			argTypes.add(val.typeName());
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		interpreter.callingFunction(name, func);
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Julia_Statement stmt : func.stmts._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		interpreter.completedFunction(name, func);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		Julia_Identifier_Reference id = variable.vars.first();
		String name = id.getValue();
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		int argCount = argList.getPrimaryCount();
		for (int i = 0; i < argCount; i++)
		{
			Julia_Expression arg = argList.getPrimaryElement(i);
			AbstractExpression newArg = transformer.transformExpression(generator, arg);
			args.add(newArg);
		}

		AbstractVariable var = generator.newVariable(name);
		return generator.newMethodInvocation(var, args, id);
	}
}
