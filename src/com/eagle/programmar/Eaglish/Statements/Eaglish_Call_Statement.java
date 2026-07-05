// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

package com.eagle.programmar.Eaglish.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Call_Statement extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
{
	public @S(10) Eaglish_Keyword CALL = new Eaglish_Keyword("CALL");
	public @S(20) Eaglish_Identifier_Reference funcName;
	public @S(30) @OPT Eaglish_CallParameters callParams;
	public @S(40) Eaglish_EndOfLine eoln;

	public static class Eaglish_CallParameters extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParenn;
		public @S(20) SeparatedList<Eaglish_Expression, PunctuationComma> args;
		public @S(30) PunctuationRightParen rightParenn;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Have to search for the FUNCTION definition
		AbstractFunction fn = interpreter.findFunction(funcName.getValue());
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + funcName.getValue());
		}
		Eaglish_Function func = (Eaglish_Function) fn;

		// Count the parameters
		int expected = func.parameterStatements.size();
		int actual = callParams.args.getPrimaryCount();
		if (actual != expected)
		{
			throw new RuntimeException(
					"Function " + funcName + ", expected params = " + expected + ", but actual args = " + actual);
		}

		// Assign all the parameters
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		for (int i = 0; i < actual; i++)
		{
			Eaglish_Parameter_Statement param = func.parameterStatements._elements.get(i);
			Eaglish_Expression arg = callParams.args.getPrimaryElement(i);
			// interpreter.tryToInterpret(arg);
			EagleValue val = interpreter.getEagleValue(arg);
			interpreter.setSymbol(param, param.param.getValue(), val);
			argTypes.add(val.getType());
		}

		// Evaluate the function
		long startTime = System.nanoTime();
		interpreter.callingFunction(funcName.getValue(), func);
		for (Eaglish_Statement stmt : func.statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Remove all the parameters
		interpreter.completedFunction(funcName.getValue(), func);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String name = funcName.getValue();
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);
		int argCount = callParams.args.getPrimaryCount();
		for (int i = 0; i < argCount; i++)
		{
			Eaglish_Expression arg = callParams.args.getPrimaryElement(i);
			AbstractExpression newArg = transformer.transformExpression(generator, arg);
			args.add(newArg);
		}

		AbstractVariable var = generator.newVariable(name);
		AbstractExpression invocation = generator.newMethodInvocation(var, args, types, this);
		return generator.newExpressionStatement(invocation, CALL);
	}
}
