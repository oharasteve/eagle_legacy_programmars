// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
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

public class Fortran_CallStatement extends TokenSequence
		implements AbstractStatement, EagleRunnable, EagleTransformableStatement
{
	public @DOC("6j4m0vn7p/index.html") @S(10) Fortran_Keyword CALL = new Fortran_Keyword("CALL");
	public @S(20) Fortran_Function_Reference variable;
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) SeparatedList<Fortran_Expression, PunctuationComma> arguments;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) Fortran_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = variable.getValue();

		AbstractFunction fn = interpreter.findFunction(fnName);
		if (fn == null || !(fn instanceof Fortran_Subroutine))
		{
			throw new RuntimeException("Unable to find a subroutine named " + fnName);
		}
		Fortran_Subroutine sub = (Fortran_Subroutine) fn;

		// Make sure the function args match up
		int argCount = arguments.getPrimaryCount();
		int paramCount = sub.parameters.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Subroutine " + fnName + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		for (int i = 0; i < argCount; i++)
		{
			Fortran_Expression expr = arguments.getPrimaryElement(i);
			Fortran_Variable_Reference param = sub.parameters.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(param, param.getValue(), val);
			argTypes.add(val.getType());
		}

		// Prepare to evaluate the procedure or function
		long startTime = System.nanoTime();

		// And transfer control to the procedure or function
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (Fortran_Statement stmt : sub.statements._elements)
		{
			result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		long elapsedTime = System.nanoTime() - startTime;
		sub._callMetrics.addCallFrom(this, elapsedTime);
		sub._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		for (int i = 0; i < argCount; i++)
		{
			Fortran_Variable_Reference param = sub.parameters.getPrimaryElement(i);
			interpreter.removeSymbol(param.getValue());
		}
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String name = variable.getValue();
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);
		int argCount = arguments.getPrimaryCount();
		for (int i = 0; i < argCount; i++)
		{
			Fortran_Expression arg = arguments.getPrimaryElement(i);
			AbstractExpression newArg = transformer.transformExpression(generator, arg);
			args.add(newArg);
		}

		AbstractVariable var = generator.newVariable(name);
		AbstractExpression expr = generator.newMethodInvocation(var, args, types, variable);
		return generator.newExpressionStatement(expr, variable);
	}
}
