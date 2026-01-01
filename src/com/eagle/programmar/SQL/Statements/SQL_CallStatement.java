// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleValue;
import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.programmar.SQL.SQL_FunctionArg;
import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.SQL_Variable;
import com.eagle.programmar.SQL.Expressions.SQL_VariableExpression;
import com.eagle.programmar.SQL.Statements.SQL_CreateProcedureStatement.SQL_ProcedureParameter;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class SQL_CallStatement extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) SQL_Keyword CALL = new SQL_Keyword("CALL");
	public @S(20) SQL_Variable procName;
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT SeparatedList<SQL_FunctionArg, PunctuationComma> args;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = procName.ids.first().getValue();
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a Stored Procedure named " + name);
		}
		SQL_CreateProcedureStatement proc = (SQL_CreateProcedureStatement) fn;

		// Make sure the function args match up
		int argCount = 0;
		if (args != null)
		{
			argCount = args.getPrimaryCount();
		}

		int paramCount = 0;
		if (proc.params != null)
		{
			paramCount = proc.params.getPrimaryCount();
		}

		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Stored Procoedure " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(name, proc);

		// Now assign all the parameters
		ArrayList<String> argTypes = new ArrayList<String>();
		if (argCount > 0)
		{
			for (int i = 0; i < argCount; i++)
			{
				SQL_ProcedureParameter param = proc.params.getPrimaryElement(i);
				EagleValue val = interpreter.getEagleValue(args.getPrimaryElement(i));
				interpreter.setSymbol(param.param, param.param.getValue(), val);
				argTypes.add(val.typeName());
			}
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the Stored Procedure
		for (SQL_StatementOrComment stmt : proc.statements._elements)
		{
			Eagle_Statement_Result result = interpreter.tryToInterpret(stmt);
			if (result != Eagle_Statement_Result.NORMAL) break;
		}

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		proc._callMetrics.addCallFrom(this, elapsedTime);
		proc._argumentsMetrics.calledWith(argTypes);

		for (int i = 0; i < argCount; i++)
		{
			// Set values for any OUT parameters
			SQL_ProcedureParameter param = proc.params.getPrimaryElement(i);
			if (param.OUT != null && param.OUT.isPresent() &&
					param.OUT.getValue().toUpperCase().equals("OUT"))
			{
				EagleValue val = interpreter.findSymbol(param.param.getValue());
				SQL_FunctionArg arg = args.getPrimaryElement(i);
				if (!(arg.getWhich() instanceof SQL_Expression))
				{
					throw new RuntimeException("OUT parameter must be an Expression");
				}
				SQL_Expression expr = (SQL_Expression) arg.getWhich();
				if (!(expr.getWhich() instanceof SQL_VariableExpression))
				{
					throw new RuntimeException("OUT parameter must be a Variable");
				}
				SQL_VariableExpression var = (SQL_VariableExpression) expr.getWhich();
				String varName = var.variable.ids.first().getValue();
				// System.out.println("******** Setting OUT param " + varName + " to " + val);
				interpreter.setSymbol(var, varName, val);
			}
		}

		// Now remove all those parameters
		interpreter.completedFunction(name, proc);
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		String name = procName.ids.first().getValue();

		int argCount = 0;
		if (args != null)
		{
			argCount = args.getPrimaryCount();
		}

//		// Are any parameters defined as OUT? Can only be one, and it must be returned not passed in
//		SQL_ProcedureParameter outParam = null;
//		for (int i = 0; i < argCount; i++)
//		{
//			SQL_ProcedureParameter param = proc.params.getPrimaryElement(i);
//			if (param.OUT != null && param.OUT.isPresent())
//			{
//				if (param.OUT.toString().toUpperCase().equals("OUT"))
//				{
//					if (outParam != null)
//					{
//						throw new RuntimeException("Can only handle one OUT parameter at a time");
//					}
//					outParam = param;
//				}
//			}
//		}

		ArrayList<AbstractExpression> arguments = new ArrayList<AbstractExpression>();
		for (int i = 0; i < argCount; i++)
		{
			SQL_FunctionArg arg = args.getPrimaryElement(i);
			if (arg.getWhich() instanceof SQL_Expression)
			{
				SQL_Expression expr = (SQL_Expression) arg.getWhich();
				AbstractExpression newArg = transformer.transformExpression(generator, expr);
				arguments.add(newArg);
			}
		}

		AbstractVariable newName = generator.newVariable(name);
		AbstractExpression newExpr = generator.newMethodInvocation(newName, arguments, this);
		return generator.newExpressionStatement(newExpr, CALL);
	}
}
