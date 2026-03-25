// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Rust.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Function;
import com.eagle.programmar.Rust.Rust_Function.Rust_Parameter;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Rust_MethodInvocation extends PrimaryOperator
		implements EagleRunnableWithResult, EagleTransformableExpression
{
	public @S(10) Rust_MethodWhat what;
	public @S(20) @OPT @NOSPACE Rust_Punctuation bang = new Rust_Punctuation("!");
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @OPT @NOSPACE SeparatedList<Rust_Expression, PunctuationComma> argList;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
	
	public static class Rust_MethodWhat extends TokenChooser
	{
		public @FIRST Rust_MethodClass XXmethodClass;
		public @CHOICE Rust_Variable XXmethodName;
	}

	public static class Rust_MethodClass extends TokenSequence
	{
		public @S(10) Rust_Identifier_Reference clsName;
		public @S(20) @NOSPACE Rust_Punctuation colonColon = new Rust_Punctuation("::");
		public @S(30) @NOSPACE Rust_Variable methodName;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		String name;
		AbstractToken which = what.getWhich();
		if (which instanceof Rust_MethodClass)
		{
			Rust_MethodClass mthCls = (Rust_MethodClass) which;
			name = mthCls.methodName.var.getValue();
		}
		else if (which instanceof Rust_Variable)
		{
			Rust_Variable var = (Rust_Variable) which;
			name = var.var.getValue();
		}
		else throw new RuntimeException("Unexpected method name: " + which);

		AbstractFunction fn = interpreter.findFunction(name);
		if (fn == null)
		{
			throw new RuntimeException("Unable to find a function named " + name);
		}
		Rust_Function func = (Rust_Function) fn;

		// Make sure the function args match up
		int argCount = argList.getPrimaryCount();
		int paramCount = func.funcParamDefs.getPrimaryCount();
		if (argCount != paramCount)
		{
			throw new RuntimeException(
					"Function " + name + " expects #args = " + paramCount + ", but was given " + argCount);
		}

		// Now assign all the parameters
		ArrayList<TypeEnum> argTypes = new ArrayList<TypeEnum>();
		for (int i = 0; i < argCount; i++)
		{
			Rust_Expression arg = argList.getPrimaryElement(i);
			Rust_Parameter param = func.funcParamDefs.getPrimaryElement(i);
			EagleValue val = interpreter.getEagleValue(arg);
			interpreter.setSymbol(param, param.var.getValue(), val);
			argTypes.add(val.getType());
		}

		// Prepare to evaluate the function
		long startTime = System.nanoTime();

		// And transfer control to the function
		interpreter.callingFunction(name, func);
		Eagle_Statement_Result result = interpreter.tryToInterpret(func.block);

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
		Rust_Identifier_Reference id;
		AbstractToken which = what.getWhich();
		if (which instanceof Rust_MethodClass)
		{
			Rust_MethodClass mthCls = (Rust_MethodClass) which;
			id = mthCls.methodName.var;
		}
		else if (which instanceof Rust_Variable)
		{
			Rust_Variable var = (Rust_Variable) which;
			id = var.var;
		}
		else throw new RuntimeException("Unexpected method name: " + which);
		String name = id.getValue();

		if (generator.isKnownMethod(name))
		{
			ArrayList<AbstractExpression> args = new ArrayList<AbstractExpression>();
			int argCount = argList.getPrimaryCount();
			for (int i = 0; i < argCount; i++)
			{
				Rust_Expression arg = argList.getPrimaryElement(i);
				AbstractExpression newArg = transformer.transformExpression(generator, arg);
				args.add(newArg);
			}

			AbstractVariable var = generator.newVariable(name);
			return generator.newMethodInvocation(var, args, id);
		}

		// Dang. Scale uses () for both arrays and function calls
		// It is not a function, so must be an array
		AbstractExpression index = transformer.transformExpression(generator,
				argList.first());
		return generator.newVariableExpression(name, SubscriptEnum.FIRST_IS_ZERO, index, this);
	}
	
	public static Rust_Expression generateInvocation(Rust_Identifier_Reference clsName,
			Rust_Variable var, ArrayList<Rust_Expression> args, AbstractToken source)
	{
		Rust_MethodInvocation invoke = new Rust_MethodInvocation();
		invoke.what = new Rust_MethodWhat();

		if (clsName == null)
		{
			invoke.what.setWhich(var);
		}
		else
		{
			Rust_MethodClass methCls = new Rust_MethodClass();
			methCls.clsName = clsName;
			methCls.methodName = var;
			methCls.setPresent(true);
			invoke.what.setWhich(methCls);
		}

		invoke.leftParen = new PunctuationLeftParen();
		invoke.argList = new SeparatedList<Rust_Expression, PunctuationComma>();
		invoke.rightParen = new PunctuationRightParen();
		
		boolean first = true;
		for (Rust_Expression arg : args)
		{
			if (first)
			{
				first = false;
			}
			else
			{
				invoke.argList.addSecondaryElement(new PunctuationComma());
			}
			
			invoke.argList.addPrimaryElement(arg);
		}
		
		return Rust_Generator.wrapExpression(invoke);
	}
}
