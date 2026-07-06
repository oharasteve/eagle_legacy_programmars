// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.SQL.Expressions;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_FunctionArg;
import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.SQL_Variable;
import com.eagle.programmar.SQL.Statements.SQL_CreateFunctionStatement;
import com.eagle.programmar.SQL.Statements.SQL_CreateFunctionStatement.SQL_FunctionParameter;
import com.eagle.programmar.SQL.Symbols.SQL_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class SQL_FunctionCall extends PrimaryOperator
		implements EagleRunnableWithResult, EagleTransformableExpression
{
	public @S(10) SQL_Variable funcName;
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE @OPT SeparatedList<SQL_FunctionArg, PunctuationComma> args;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		SQL_Identifier_Reference id = funcName.ids.first();
		String name = id.getValue();
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		SQL_CreateFunctionStatement func = (SQL_CreateFunctionStatement) fn;

		// Make sure the function args match up
		int argCount = 0;
		if (args != null && args.isPresent())
		{
			argCount = args.getPrimaryCount();
		}
		int paramCount = 0;
		if (func.params != null && func.params.isPresent())
		{
			paramCount = func.params.getPrimaryCount();
		}
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(name, func);

		// Now assign all the parameters
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		if (argCount > 0)
		{
			for (int i = 0; i < argCount; i++)
			{
				SQL_FunctionArg arg = args.getPrimaryElement(i);
				if (! (arg.getWhich() instanceof SQL_Expression))
				{
					throw new RuntimeException("Unable to handle " + arg.getWhich());
				}
				SQL_Expression expr = (SQL_Expression) arg.getWhich();
				SQL_FunctionParameter param = func.params.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param.id, param.id.getValue(), val);
				argTypes.add(val.getType());
			}
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the method
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (SQL_StatementOrComment stmtComm : func.statements._elements)
		{
			result = interpreter.tryToInterpret(stmtComm);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				break;
			}
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		interpreter.completedFunction(name, func);
		return result;
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		SQL_Identifier_Reference id = funcName.ids.first();
		String name = id.getValue();
		ArrayList<AbstractExpression> arguments = new ArrayList<AbstractExpression>();
		ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);
		if (args != null && args.isPresent())
		{
			int argCount = args.getPrimaryCount();
			for (int i = 0; i < argCount; i++)
			{
				SQL_FunctionArg arg = args.getPrimaryElement(i);
				if (! (arg.getWhich() instanceof SQL_Expression))
				{
					throw new RuntimeException("Unable to handle " + arg.getWhich());
				}
				SQL_Expression expr = (SQL_Expression) arg.getWhich();
				arguments.add(transformer.transformExpression(generator, expr));
				
			}
		}

		AbstractVariable var = generator.newVariable(name);
		return generator.newMethodInvocation(var, arguments, types, this);
	}
}
