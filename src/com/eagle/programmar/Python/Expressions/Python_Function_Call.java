// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Python.Python_Argument_List;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Python_Params.Python_Parameter;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Statements.Python_Function;
import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
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

public class Python_Function_Call extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Variable fnName;
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE @OPT @SYNTAX(Python_Multiline_Syntax.class) SeparatedList<Python_Expression, PunctuationComma> argList;
	public @S(40) @OPT PunctuationComma extraComma;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = "unknown";
		if (fnName.var.getWhich() instanceof Python_Identifier_Reference)
		{
			Python_Identifier_Reference id = (Python_Identifier_Reference) fnName.var.getWhich();
			name = id.getValue();
		}

		// Look up the function in our function list
		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		Python_Function func = (Python_Function) fn;

		// Make sure the function args match up
		int argCount = 0;
		if (argList != null && argList.isPresent())
		{
			argCount = argList.getPrimaryCount();
		}
		int paramCount = 0;
		if (func.header.params.params != null && func.header.params.params.isPresent())
		{
			paramCount++;
			if (func.header.params.params.moreParams != null && func.header.params.params.moreParams.isPresent())
			{
				paramCount += func.header.params.params.moreParams.size();
			}
		}
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		interpreter.callingFunction(name, func.header);

		// Now assign all the parameters
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		if (func.header.params.params != null)
		{
			Python_Parameter param = func.header.params.params.param;
			for (int i = 0; i < argCount; i++)
			{
				Python_Expression expr = argList.getPrimaryElement(i);
				if (i > 0)
				{
					param = func.header.params.params.moreParams._elements.get(i - 1).param;
				}
				if (param.getWhich() instanceof Python_Variable_Definition)
				{
					Python_Variable_Definition def = (Python_Variable_Definition) param.getWhich();
					EagleValue val = interpreter.getEagleValue(expr);
					interpreter.setSymbol(def, def.getValue(), val);
					argTypes.add(val.getType());
				}
			}
		}

		// Prepare to evaluate the method
		long startTime = System.nanoTime();

		// And transfer control to the function
		interpreter.tryToInterpret(func.header.defBody);

		// The result was already put on the runtime stack
		long elapsedTime = System.nanoTime() - startTime;
		func._callMetrics.addCallFrom(this, elapsedTime);
		func._argumentsMetrics.calledWith(argTypes);

		// Now remove all those parameters
		interpreter.completedFunction(name, func.header);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (!(fnName.var.getWhich() instanceof Python_Identifier_Reference))
		{
			throw new RuntimeException("Must be a simple function call");
		}
		Python_Identifier_Reference id = (Python_Identifier_Reference) fnName.var.getWhich();
		String name = id.getValue();
		ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
		ArrayList<TypeEnum> types = transformer.findArgumentsMetricForFunction(name);
		int argCount = argList.getPrimaryCount();
		for (int i = 0; i < argCount; i++)
		{
			Python_Expression arg = argList.getPrimaryElement(i);
			AbstractExpression newArg = transformer.transformExpression(generator, arg);
			args.add(newArg);
		}

		AbstractVariable var = generator.newVariable(name);
		return generator.newMethodInvocation(var, args, types, id);
	}

	public static Python_Expression generateInvocation(Python_Variable var,
			ArrayList<Python_Expression> args, ArrayList<TypeEnum> types, AbstractToken source)
	{
		Python_Function_Call invoke = new Python_Function_Call();
		invoke.leftParen = new PunctuationLeftParen();
		invoke.leftParen.setPresent(true);
		invoke.rightParen = new PunctuationRightParen();
		invoke.rightParen.setPresent(true);
		AbstractToken which = var.var.getWhich();
		if (which instanceof Python_Identifier_Reference)
		{
			String id = ((Python_Identifier_Reference) which).getValue();
			// if (id.indexOf('.') < 0) id = "self." + id;
			invoke.fnName = Python_Variable.newVariable(id);
			invoke.argList = Python_Argument_List.createArgumentList(args);
		}
		else
			throw new RuntimeException("Expected an Identifier, not " + which);

		invoke.setTransformationSource(source);
		return Python_Generator.wrapExpression(invoke);
	}
}
