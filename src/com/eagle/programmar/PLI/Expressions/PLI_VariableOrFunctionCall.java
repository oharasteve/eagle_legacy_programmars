// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.PLI_Procedure;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.PLI_Subscript;
import com.eagle.programmar.PLI.PLI_Subscript.PLI_ExpressionOrStar;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BuiltInEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class PLI_VariableOrFunctionCall extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PLI_Identifier_Reference id;
	public @S(20) @OPT PLI_Subscript subscript;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = id.getValue();
		if (subscript != null && subscript.isPresent())
		{
			int argCount = subscript.args.getPrimaryCount();
			
			// First: search user variables
			EagleValue var = interpreter.findSymbol(name);
			if (var != null && var.isArray() && argCount == 1)
			{
				EagleArray array = (EagleArray) var;
				int subscr = interpreter.getIntValue(subscript.args.getPrimaryElement(0));
				EagleValue val = array.getValue(subscr);
				interpreter.pushEagleValue(val);
				return;
			}

			// Next: search for the Procedure definition
			AbstractFunction fn = interpreter.findFunction(name);
			if (fn == null)
			{
				throw new RuntimeException("Unable to find a Procedure named " + name);
			}
			PLI_Procedure proc = (PLI_Procedure) fn;

			// Count the parameters
			int paramCount = proc.params.params.getPrimaryCount();
			if (argCount != paramCount)
			{
				throw new RuntimeException(
						"Function " + name + ", expected params = " + paramCount + ", but actual args = " + argCount);
			}

			ArrayList<String> argTypes = new ArrayList<String>();

			// Assign all the parameters
			for (int i = 0; i < argCount; i++)
			{
				PLI_Identifier_Reference param = proc.params.params.getPrimaryElement(i);
				PLI_ExpressionOrStar arg = subscript.args.getPrimaryElement(i);
				PLI_Expression expr = (PLI_Expression) arg.getWhich();
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(param, param.getValue(), val);
				argTypes.add(val.typeName());
			}

			// Evaluate the function
			long startTime = System.nanoTime();

			interpreter.callingFunction(name, proc);
			Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
			for (PLI_StatementOrComment stmt : proc.statements._elements)
			{
				result = interpreter.tryToInterpret(stmt);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			
			long elapsedTime = System.nanoTime() - startTime;
			proc._callMetrics.addCallFrom(this, elapsedTime);
			proc._argumentsMetrics.calledWith(argTypes);

			// Remove all the parameters
			interpreter.completedFunction(name, proc);
		}
		else
		{
			// Just a variable
			if (name.equalsIgnoreCase("true"))
			{
				interpreter.pushBool(true);
			}
			else if (name.equalsIgnoreCase("false"))
			{
				interpreter.pushBool(false);
			}
			else
			{
				EagleValue value = interpreter.findSymbol(name);
				interpreter.pushEagleValue(value);
			}
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		String name = id.getValue();

		if (subscript == null || ! subscript.isPresent())
		{
			// Case I: Just a variable with no subscript, and can't be a function call
			if (name.equalsIgnoreCase("true"))
			{
				return generator.newBuiltInExpression(BuiltInEnum.TRUE, this);
			}
			if (name.equalsIgnoreCase("false"))
			{
				return generator.newBuiltInExpression(BuiltInEnum.FALSE, this);
			}
			return generator.newVariableExpression(name, SubscriptEnum.FIRST_IS_ZERO, null, id);
		}
		
		if (subscript.args != null)
		{
			int argCount = subscript.args.getPrimaryCount();
			
			// Case II: Calling a Procedure
			if (generator.isKnownMethod(name) || argCount > 1)
			{
				ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
				for (int i = 0; i < argCount; i++)
				{
					PLI_ExpressionOrStar arg = subscript.args.getPrimaryElement(i);
					if (arg.getWhich() instanceof PLI_Expression)
					{
						PLI_Expression expr = (PLI_Expression) arg.getWhich();
						AbstractExpression newArg = transformer.transformExpression(generator, expr);
						args.add(newArg);
					}
				}
		
				AbstractVariable var = generator.newVariable(name);
				return generator.newMethodInvocation(var, args, id);
			}
	
			// Case III: an array variable, with a subscript
			// Dang. PL/I uses () for both arrays and function calls
			PLI_ExpressionOrStar arg = subscript.args.first();
			if (arg.getWhich() instanceof PLI_Expression)
			{
				PLI_Expression expr = (PLI_Expression) arg.getWhich();
				AbstractExpression index = transformer.transformExpression(generator,
						expr);
				return generator.newVariableExpression(name, SubscriptEnum.FIRST_IS_ZERO,
						index, this);
			}
		}
		
		return null;
	}
}
